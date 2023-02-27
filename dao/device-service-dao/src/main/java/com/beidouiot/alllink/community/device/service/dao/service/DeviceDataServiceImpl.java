package com.beidouiot.alllink.community.device.service.dao.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.device.DeviceData;
import com.beidouiot.alllink.community.common.data.entity.device.DeviceInfo;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductCommandModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductPropertyModelDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.device.dao.service.api.DeviceDataService;
import com.beidouiot.alllink.community.device.service.dao.repository.DeviceDataRepository;
import com.beidouiot.alllink.community.device.service.dao.repository.DeviceInfoRepository;
import com.beidouiot.alllink.community.device.service.dao.repository.InfluxDBIniti;
import com.beidouiot.alllink.community.device.service.dao.repository.interceptor.JPAInterceptor;
import com.beidouiot.alllink.community.feign.product.ProductModelFeignClient;
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

	@Override
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
	public List<?> findDeviceData(String deviceId, Date date) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveDeviceSNTableStructure(String deviceSN) throws ServiceException {
		StringBuilder sb = new StringBuilder();
//		sb.append("create table if not exists '")
//		  .append("sn_")
//		  .append(deviceSN)
//		  .append("'")
//		  .append(" ('id' bigint NOT NULL COMMENT '主键id',")
//		  .append("'device_id' bigint NOT NULL COMMENT '设备id',")
//		  .append("'device_sn' varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备唯一标识号',")
//		  .append("'model_code' varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '物模型标识',")
//		  .append("'model_value' varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '模型标识值', ")
//		  .append("'collect_time' varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据采集时间',")
//		  .append("'model_type' int NOT NULL COMMENT '物模型类型(属性模型：1，事件模型：2，指令模型：3)',")
//		  .append("'create_date' datetime NOT NULL COMMENT '创建时间',")
//		  .append("'created_by' varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',")
//		  .append("'updated_by' varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',")
//		  .append("'delete_flag' bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',")
//		  .append("'opt_lock_version' int DEFAULT NULL COMMENT '乐观锁版本号',")
//		  .append("PRIMARY KEY ('id')) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci, nativeQuery = true");
		
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

}
