package com.beidouiot.alllink.community.device.service.dao.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.device.DeviceData;
import com.beidouiot.alllink.community.common.data.entity.device.DeviceInfo;
import com.beidouiot.alllink.community.common.data.mapping.DeviceDataDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceDataDto;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceNewDataDto;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductCommandModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductPropertyModelDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.device.dao.service.api.DeviceDataService;
import com.beidouiot.alllink.community.device.dao.service.api.DeviceNewDataService;
import com.beidouiot.alllink.community.device.service.dao.repository.DeviceDataRepository;
import com.beidouiot.alllink.community.device.service.dao.repository.DeviceInfoRepository;
import com.beidouiot.alllink.community.device.service.dao.repository.InfluxDBIniti;
import com.beidouiot.alllink.community.device.service.dao.repository.interceptor.JPAInterceptor;
import com.beidouiot.alllink.community.feign.product.ProductFeignClient;
import com.beidouiot.alllink.community.feign.product.ProductModelFeignClient;
import com.beidouiot.alllink.community.feign.product.StandardPropertyModelFeignClient;
import com.influxdb.client.WriteApi;
import com.influxdb.client.WriteOptions;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;

@Service
public class DeviceDataServiceImpl implements DeviceDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceDataServiceImpl.class);

	@Autowired
	private InfluxDBIniti influxDBIniti;

	@Value("${influx.bucket}")
	private String bucket;

	@Value("${influx.org}")
	private String org;
	
	@Value("${device.collection.data.storage}")
	private String dataStorage;
	
	@Autowired
	private DeviceInfoRepository deviceInfoRepository;
	
	@Autowired
	private ProductModelFeignClient productModelFeignClient;
	
	@Autowired
	private DeviceDataRepository deviceDataRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private DeviceDataDtoMapping deviceDataDtoMapping;
	
	@Autowired
	private DeviceNewDataService deviceNewDataService;
	
	@Autowired
	private ProductFeignClient productFeignClient;
	
	@Autowired
	private StandardPropertyModelFeignClient standardPropertyModelFeignClient;

	@Override
	@Transactional
	public void saveData(String deviceId, String deviceSN, Map<String, String> data) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("deviceId = [ {} ], data = [ {} ]", deviceId, data);
		}
		Optional<DeviceInfo> o = null;
		if (deviceId == null || "".equals(deviceId)) {
			List<DeviceInfo> list = deviceInfoRepository.findByDeviceSnAndDeleteFlagAndEnableFlag(deviceSN, Constants.FALSE,Constants.TRUE);
			o = Optional.of(list.get(0));
		} else {
			o = deviceInfoRepository.findById(Long.valueOf(deviceId));
		}
		
		
		if(null != o) {
			DeviceInfo deviceInfo = o.get();
			ResultDataRro<ProductModelDto> rdr = productModelFeignClient.findPubProductModelByProductId(new ID(deviceInfo.getProductId()));
			ProductModelDto productModelDto = rdr.getData();
			List<ProductPropertyModelDto>  productPropertyModelDtos = productModelDto.getProductPropertyModelList();
			List<ProductEventModelDto>  productEventModelDtos = productModelDto.getProductEventModelList();
			List<ProductCommandModelDto>  productCommandModelDtos = productModelDto.getProductCommandModelList();
			
			if (dataStorage == null || dataStorage.equals("")) {
				throw new ServiceException("存储介质不能为空！");
			}
			
			if (dataStorage.equals("influxDB")) {
				WriteOptions writeOptions = WriteOptions.builder().batchSize(5000).bufferLimit(10000).flushInterval(1000)
						.jitterInterval(1000).retryInterval(5000).build();
				try(WriteApi writeApi = influxDBIniti.influxDBClient().getWriteApi(writeOptions)) {
					Point point = Point.measurement(deviceId);
					for (ProductPropertyModelDto productPropertyModelDto : productPropertyModelDtos) {
						String code = productPropertyModelDto.getCode();
						String value = data.get(code);
						if (value != null || !"".equals(value)) {
							point.addField(code, value);
						}
					}
					for (ProductEventModelDto productEventModelDto : productEventModelDtos) {
						String code = productEventModelDto.getCode();
						String value = data.get(code);
						if (value != null || !"".equals(value)) {
							point.addField(code, value);
						}
					}
					for (ProductCommandModelDto productCommandModelDto : productCommandModelDtos) {
						String code = productCommandModelDto.getCode();
						String value = data.get(code);
						if (value != null || !"".equals(value)) {
							point.addField(code, value);
						}
					}
					point.addField("collectTime", data.get("collectTime"));
					point.time(Instant.now(), WritePrecision.NS);
					
					writeApi.writePoint(bucket,org,point);
				}
			} else if (dataStorage.equals("mysql")) {
				List<DeviceData> list = new ArrayList<DeviceData>();
				
				for (ProductPropertyModelDto productPropertyModelDto : productPropertyModelDtos) {
					String code = productPropertyModelDto.getCode();
					String value = data.get(code);
					if (value != null && !"".equals(value)) {
						DeviceData dd = new DeviceData();
						
						dd.setCollectTime(data.get("collectTime"));
						dd.setCreatedBy("system");
						dd.setDeviceId(deviceInfo.getId());
						dd.setDeviceSn(deviceInfo.getDeviceSn());
						dd.setModelCode(code);
						dd.setModelType(1);
						dd.setModelValue(value);
						dd.setUpdatedBy("system");
						list.add(dd);
						DeviceNewDataDto dnd = new DeviceNewDataDto();
						dnd.setCreatedBy("system");
						dnd.setDeviceId(dd.getDeviceId());
						dnd.setModelCode(dd.getModelCode());
						dnd.setModelType(dd.getModelType());
						dnd.setReceivedData(dd.getModelValue());
						deviceNewDataService.saveEntity(dnd);
					}
				}
				for (ProductEventModelDto productEventModelDto : productEventModelDtos) {
					String code = productEventModelDto.getCode();
					String value = data.get(code);
					if (value != null && !"".equals(value)) {
						DeviceData dd = new DeviceData();
						dd.setCollectTime(data.get("collectTime"));
						dd.setCreatedBy("system");
						dd.setDeviceId(deviceInfo.getId());
						dd.setDeviceSn(deviceInfo.getDeviceSn());
						dd.setModelCode(code);
						dd.setModelType(2);
						dd.setModelValue(value);
						dd.setUpdatedBy("system");
						list.add(dd);
						DeviceNewDataDto dnd = new DeviceNewDataDto();
						dnd.setCreatedBy("system");
						dnd.setDeviceId(dd.getDeviceId());
						dnd.setModelCode(dd.getModelCode());
						dnd.setModelType(dd.getModelType());
						dnd.setReceivedData(dd.getModelValue());
						deviceNewDataService.saveEntity(dnd);
					}
				}
				for (ProductCommandModelDto productCommandModelDto : productCommandModelDtos) {
					String code = productCommandModelDto.getCode();
					String value = data.get(code);
					if (value != null && !"".equals(value)) {
						DeviceData dd = new DeviceData();
						dd.setCollectTime(data.get("collectTime"));
						dd.setCreatedBy("system");
						dd.setDeviceId(deviceInfo.getId());
						dd.setDeviceSn(deviceInfo.getDeviceSn());
						dd.setModelCode(code);
						dd.setModelType(3);
						dd.setModelValue(value);
						dd.setUpdatedBy("system");
						list.add(dd);
						DeviceNewDataDto dnd = new DeviceNewDataDto();
						dnd.setCreatedBy("system");
						dnd.setDeviceId(dd.getDeviceId());
						dnd.setModelCode(dd.getModelCode());
						dnd.setModelType(dd.getModelType());
						dnd.setReceivedData(dd.getModelValue());
						deviceNewDataService.saveEntity(dnd);
					}
				}
				setTable("device_data","sn_"+deviceInfo.getDeviceSn());
				deviceDataRepository.saveAll(list);
			} else {
				throw new ServiceException("暂不支持的存储介质！");
			}
		}

	}

	@Override
	public void saveDeviceSNTableStructure(String deviceSN) throws ServiceException {
		StringBuilder sb = new StringBuilder();
		sb.append("create table if not exists ")
		  .append("sn_")
		  .append(deviceSN)
		  .append("(id bigint PRIMARY KEY COMMENT '主键id', ")
		  .append("device_id bigint NOT NULL COMMENT '设备id',")
		  .append("device_sn varchar(100)  COLLATE utf8mb4_general_ci NOT NULL COMMENT '物模型标识', ")
		  .append("model_code varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '物模型标识',")
		  .append("model_value varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '模型标识值', ")
		  .append("collect_time varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据采集时间',")
		  .append("model_type int NOT NULL COMMENT '物模型类型（属性模型：1，事件模型：2，指令模型：3）',")
		  .append("create_date datetime NOT NULL COMMENT '创建时间',")
		  .append("updated_date datetime NOT NULL COMMENT '修改时间',")
		  .append("created_by varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',")
		  .append("updated_by varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',")
		  .append("delete_flag bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',")
		  .append("opt_lock_version int DEFAULT NULL COMMENT '乐观锁版本号' ")
		  .append("  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci");
		
		jdbcTemplate.execute(sb.toString());
		
	}
	
	private void setTable(String oldName, String newName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("oldName", oldName);
		map.put("newName", newName);
		JPAInterceptor.dynamicTable.set(map);
	}

	@Override
	public Page<DeviceDataDto> findDeviceData(Long deviceId, Date start, Date end, Integer pageNum, Integer pageSize)
			throws ServiceException {
		if( deviceId == null || deviceId.longValue() == 0) {
			throw new ServiceException("设备id不能为空！");
		}
		if (start == null || end == null) {
			throw new ServiceException("开始时间或结束时间不能为空！");
		}
		
		Specification<DeviceData> specification = new Specification<DeviceData>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -8132471834021819629L;

			@Override
			public Predicate toPredicate(Root<DeviceData> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(criteriaBuilder.equal(root.get("id"), deviceId));
				list.add(criteriaBuilder.between(root.get("collectTime"), start, end));
				
				
				return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
			}
			
		};
		
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "collectTime"));
		Optional<DeviceInfo> o = deviceInfoRepository.findById(deviceId);
		if (!o.isPresent()) {
			throw new ServiceException("设备id不存在！");
		}
		DeviceInfo deviceInfo = o.get();
		
		setTable("device_data","sn_"+deviceInfo.getDeviceSn());
		Page<DeviceData> page = deviceDataRepository.findAll(specification, pageable);
		
		List<DeviceDataDto> listDto = new ArrayList<DeviceDataDto>();
		for (DeviceData e : page) {
			DeviceDataDto t = deviceDataDtoMapping.sourceToTarget(e);
			listDto.add(t);
		}
		
		Page<DeviceDataDto> pageDto = new PageImpl<DeviceDataDto>(listDto, page.getPageable(), page.getTotalElements());
		return pageDto;
	}

	@Override
	public Page<DeviceDataDto> findDeviceData(Long deviceId, Integer hours, Integer pageNum, Integer pageSize)
			throws ServiceException {
		if( deviceId == null || deviceId.longValue() == 0) {
			throw new ServiceException("设备id不能为空！");
		}
		
		if( hours == null || hours.intValue() == 0 ) {
			throw new ServiceException("最近时长不能为空！");
		}
		
		Specification<DeviceData> specification = new Specification<DeviceData>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -926865447015524979L;

			@Override
			public Predicate toPredicate(Root<DeviceData> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(criteriaBuilder.equal(root.get("id"), deviceId));
				list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("collectTime"), LocalDateTime.now().minusHours(hours)));
				
				return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
			}
			
		};
		
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "collectTime"));
		Optional<DeviceInfo> o = deviceInfoRepository.findById(deviceId);
		if (!o.isPresent()) {
			throw new ServiceException("设备id不存在！");
		}
		DeviceInfo deviceInfo = o.get();
		
		setTable("device_data","sn_"+deviceInfo.getDeviceSn());
		Page<DeviceData> page = deviceDataRepository.findAll(specification, pageable);
		
		List<DeviceDataDto> listDto = new ArrayList<DeviceDataDto>();
		for (DeviceData e : page) {
			DeviceDataDto t = deviceDataDtoMapping.sourceToTarget(e);
			listDto.add(t);
		}
		
		Page<DeviceDataDto> pageDto = new PageImpl<DeviceDataDto>(listDto, page.getPageable(), page.getTotalElements());
		return pageDto;
		
	}

	@Override
	public Page<DeviceDataDto> findDeviceData(String deviceSN, Date start, Date end, Integer pageNum, Integer pageSize)
			throws ServiceException {
		if( deviceSN == null || deviceSN.equals("")) {
			throw new ServiceException("设备SN不能为空！");
		}
		if (start == null || end == null) {
			throw new ServiceException("开始时间或结束时间不能为空！");
		}
		
		Specification<DeviceData> specification = new Specification<DeviceData>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -2903838654778582578L;

			@Override
			public Predicate toPredicate(Root<DeviceData> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(criteriaBuilder.equal(root.get("deviceSn"), deviceSN));
				list.add(criteriaBuilder.between(root.get("collectTime"), start, end));
				
				
				return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
			}
			
		};
		
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "collectTime"));
		List<DeviceInfo> list = deviceInfoRepository.findByDeviceSnAndDeleteFlagAndEnableFlag(deviceSN, Constants.FALSE, Constants.TRUE);
		if (list == null || list.size() == 0) {
			throw new ServiceException("设备sn不存在！");
		}
		
		setTable("device_data","sn_"+deviceSN);
		Page<DeviceData> page = deviceDataRepository.findAll(specification, pageable);
		
		List<DeviceDataDto> listDto = new ArrayList<DeviceDataDto>();
		for (DeviceData e : page) {
			DeviceDataDto t = deviceDataDtoMapping.sourceToTarget(e);
			listDto.add(t);
		}
		
		Page<DeviceDataDto> pageDto = new PageImpl<DeviceDataDto>(listDto, page.getPageable(), page.getTotalElements());
		return pageDto;
	}

	@Override
	public Page<DeviceDataDto> findDeviceData(String deviceSN, Integer hours, Integer pageNum, Integer pageSize)
			throws ServiceException {
		if( deviceSN == null || deviceSN.equals("")) {
			throw new ServiceException("设备SN不能为空！");
		}
		if( hours == null || hours.intValue() == 0 ) {
			throw new ServiceException("最近时长不能为空！");
		}
		
		Specification<DeviceData> specification = new Specification<DeviceData>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -1831854785858585697L;

			@Override
			public Predicate toPredicate(Root<DeviceData> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(criteriaBuilder.equal(root.get("deviceSn"), deviceSN));
				list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("collectTime"), LocalDateTime.now().minusHours(hours)));
				
				
				return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
			}
			
		};
		
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "collectTime"));
		List<DeviceInfo> list = deviceInfoRepository.findByDeviceSnAndDeleteFlagAndEnableFlag(deviceSN, Constants.FALSE, Constants.TRUE);
		if (list == null || list.size() == 0) {
			throw new ServiceException("设备sn不存在！");
		}
		
		setTable("device_data","sn_"+deviceSN);
		Page<DeviceData> page = deviceDataRepository.findAll(specification, pageable);
		
		List<DeviceDataDto> listDto = new ArrayList<DeviceDataDto>();
		for (DeviceData e : page) {
			DeviceDataDto t = deviceDataDtoMapping.sourceToTarget(e);
			listDto.add(t);
		}
		
		Page<DeviceDataDto> pageDto = new PageImpl<DeviceDataDto>(listDto, page.getPageable(), page.getTotalElements());
		return pageDto;
	}

	@Override
	public Page<DeviceDataDto> findDeviceData(Long deviceId, String modelCode, Date start, Date end, Integer pageNum,
			Integer pageSize) throws ServiceException {
		if( deviceId == null || deviceId.longValue() == 0) {
			throw new ServiceException("设备id不能为空！");
		}
		if (start == null || end == null) {
			throw new ServiceException("开始时间或结束时间不能为空！");
		}
		
		Specification<DeviceData> specification = new Specification<DeviceData>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -8132471834021819629L;

			@Override
			public Predicate toPredicate(Root<DeviceData> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(criteriaBuilder.equal(root.get("id"), deviceId));
				list.add(criteriaBuilder.equal(root.get("modelCode"), modelCode));
				list.add(criteriaBuilder.between(root.get("collectTime"), start, end));
				
				
				return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
			}
			
		};
		
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "collectTime"));
		Optional<DeviceInfo> o = deviceInfoRepository.findById(deviceId);
		if (!o.isPresent()) {
			throw new ServiceException("设备id不存在！");
		}
		DeviceInfo deviceInfo = o.get();
		
		setTable("device_data","sn_"+deviceInfo.getDeviceSn());
		Page<DeviceData> page = deviceDataRepository.findAll(specification, pageable);
		
		List<DeviceDataDto> listDto = new ArrayList<DeviceDataDto>();
		for (DeviceData e : page) {
			DeviceDataDto t = deviceDataDtoMapping.sourceToTarget(e);
			listDto.add(t);
		}
		
		Page<DeviceDataDto> pageDto = new PageImpl<DeviceDataDto>(listDto, page.getPageable(), page.getTotalElements());
		return pageDto;
	}

	@Override
	public Page<DeviceDataDto> findDeviceData(Long deviceId, String modelCode, Integer hours, Integer pageNum,
			Integer pageSize) throws ServiceException {
		if( deviceId == null || deviceId.longValue() == 0) {
			throw new ServiceException("设备id不能为空！");
		}
		
		if( hours == null || hours.intValue() == 0 ) {
			throw new ServiceException("最近时长不能为空！");
		}
		
		Specification<DeviceData> specification = new Specification<DeviceData>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -926865447015524979L;

			@Override
			public Predicate toPredicate(Root<DeviceData> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(criteriaBuilder.equal(root.get("id"), deviceId));
				list.add(criteriaBuilder.equal(root.get("modelCode"), modelCode));
				list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("collectTime"), LocalDateTime.now().minusHours(hours)));
				
				return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
			}
			
		};
		
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "collectTime"));
		Optional<DeviceInfo> o = deviceInfoRepository.findById(deviceId);
		if (!o.isPresent()) {
			throw new ServiceException("设备id不存在！");
		}
		DeviceInfo deviceInfo = o.get();
		
		setTable("device_data","sn_"+deviceInfo.getDeviceSn());
		Page<DeviceData> page = deviceDataRepository.findAll(specification, pageable);
		
		List<DeviceDataDto> listDto = new ArrayList<DeviceDataDto>();
		for (DeviceData e : page) {
			DeviceDataDto t = deviceDataDtoMapping.sourceToTarget(e);
			listDto.add(t);
		}
		
		Page<DeviceDataDto> pageDto = new PageImpl<DeviceDataDto>(listDto, page.getPageable(), page.getTotalElements());
		return pageDto;
	}

	@Override
	public Page<DeviceDataDto> findDeviceData(String deviceSN, String modelCode, Date start, Date end, Integer pageNum,
			Integer pageSize) throws ServiceException {
		if( deviceSN == null || deviceSN.equals("")) {
			throw new ServiceException("设备SN不能为空！");
		}
		if (start == null || end == null) {
			throw new ServiceException("开始时间或结束时间不能为空！");
		}
		
		Specification<DeviceData> specification = new Specification<DeviceData>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -2903838654778582578L;

			@Override
			public Predicate toPredicate(Root<DeviceData> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(criteriaBuilder.equal(root.get("deviceSn"), deviceSN));
				list.add(criteriaBuilder.equal(root.get("modelCode"), modelCode));
				list.add(criteriaBuilder.between(root.get("collectTime"), start, end));
				
				
				return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
			}
			
		};
		
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "collectTime"));
		List<DeviceInfo> list = deviceInfoRepository.findByDeviceSnAndDeleteFlagAndEnableFlag(deviceSN, Constants.FALSE, Constants.TRUE);
		if (list == null || list.size() == 0) {
			throw new ServiceException("设备sn不存在！");
		}
		
		setTable("device_data","sn_"+deviceSN);
		Page<DeviceData> page = deviceDataRepository.findAll(specification, pageable);
		
		List<DeviceDataDto> listDto = new ArrayList<DeviceDataDto>();
		for (DeviceData e : page) {
			DeviceDataDto t = deviceDataDtoMapping.sourceToTarget(e);
			listDto.add(t);
		}
		
		Page<DeviceDataDto> pageDto = new PageImpl<DeviceDataDto>(listDto, page.getPageable(), page.getTotalElements());
		return pageDto;
	}

	@Override
	public Page<DeviceDataDto> findDeviceData(String deviceSN, String modelCode, Integer hours, Integer pageNum,
			Integer pageSize) throws ServiceException {
		if( deviceSN == null || deviceSN.equals("")) {
			throw new ServiceException("设备SN不能为空！");
		}
		if( hours == null || hours.intValue() == 0 ) {
			throw new ServiceException("最近时长不能为空！");
		}
		
		Specification<DeviceData> specification = new Specification<DeviceData>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -1831854785858585697L;

			@Override
			public Predicate toPredicate(Root<DeviceData> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				list.add(criteriaBuilder.equal(root.get("deviceSn"), deviceSN));
				list.add(criteriaBuilder.equal(root.get("modelCode"), modelCode));
				list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("collectTime"), LocalDateTime.now().minusHours(hours)));
				
				
				return query.where(list.toArray(new Predicate[list.size()])).getRestriction();
			}
			
		};
		
		Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC, "collectTime"));
		List<DeviceInfo> list = deviceInfoRepository.findByDeviceSnAndDeleteFlagAndEnableFlag(deviceSN, Constants.FALSE, Constants.TRUE);
		if (list == null || list.size() == 0) {
			throw new ServiceException("设备sn不存在！");
		}
		
		setTable("device_data","sn_"+deviceSN);
		Page<DeviceData> page = deviceDataRepository.findAll(specification, pageable);
		
		List<DeviceDataDto> listDto = new ArrayList<DeviceDataDto>();
		for (DeviceData e : page) {
			DeviceDataDto t = deviceDataDtoMapping.sourceToTarget(e);
			listDto.add(t);
		}
		
		Page<DeviceDataDto> pageDto = new PageImpl<DeviceDataDto>(listDto, page.getPageable(), page.getTotalElements());
		return pageDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doPropertiesUp(JSONObject jsonObj) {
		JSONObject jsonData = jsonObj.getJSONObject("data");
		Map<String, String> map = jsonObj.getObject("data", Map.class);
		
		List<DeviceInfo> deviceInfos = deviceInfoRepository.findByNameAndDeviceSnAndDeleteFlagAndEnableFlag(jsonData.getString("deviceName"), jsonData.getString("sn"), Boolean.FALSE, Boolean.TRUE);
		DeviceInfo deviceInfo = deviceInfos.get(0);
		saveData(String.valueOf(deviceInfo.getId()), deviceInfo.getDeviceSn(), map);
	}

	@Override
	public void doEventsUp(JSONObject jsonObj) {
		JSONObject jsonData = jsonObj.getJSONObject("data");
		Map<String, String> map = jsonObj.getObject("data", Map.class);
		
		List<DeviceInfo> deviceInfos = deviceInfoRepository.findByNameAndDeviceSnAndDeleteFlagAndEnableFlag(jsonData.getString("deviceName"), jsonData.getString("sn"), Boolean.FALSE, Boolean.TRUE);
		DeviceInfo deviceInfo = deviceInfos.get(0);
		saveData(String.valueOf(deviceInfo.getId()), deviceInfo.getDeviceSn(), map);
	}

	@Override
	public void doCommandsUp(JSONObject jsonObj) {
		JSONObject jsonData = jsonObj.getJSONObject("data");
		Map<String, String> map = jsonObj.getObject("data", Map.class);
		
		List<DeviceInfo> deviceInfos = deviceInfoRepository.findByNameAndDeviceSnAndDeleteFlagAndEnableFlag(jsonData.getString("deviceName"), jsonData.getString("sn"), Boolean.FALSE, Boolean.TRUE);
		DeviceInfo deviceInfo = deviceInfos.get(0);
		saveData(String.valueOf(deviceInfo.getId()), deviceInfo.getDeviceSn(), map);
	}

}
