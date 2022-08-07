package com.beidouiot.alllink.community.device.service.dao.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.data.entity.device.DeviceInfo;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductCommandModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductPropertyModelDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.device.dao.service.api.DeviceDataService;
import com.beidouiot.alllink.community.device.service.dao.repository.DeviceInfoRepository;
import com.beidouiot.alllink.community.device.service.dao.repository.InfluxDBIniti;
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
	
	@Autowired
	private DeviceInfoRepository deviceInfoRepository;
	
	@Autowired
	private ProductModelFeignClient productModelFeignClient;

	@Override
	public void saveData(String deviceId, Map<String, String> data) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("deviceId = [ {} ], data = [ {} ]", deviceId, data);
		}
		Optional<DeviceInfo> o = deviceInfoRepository.findById(Long.valueOf(deviceId));
		if(null != o) {
			DeviceInfo deviceInfo = o.get();
			ResultDataRro<ProductModelDto> rdr = productModelFeignClient.findPubProductModelByProductId(new ID(deviceInfo.getProductId()));
			ProductModelDto productModelDto = rdr.getData();
			List<ProductPropertyModelDto>  productPropertyModelDtos = productModelDto.getProductPropertyModelList();
			List<ProductEventModelDto>  productEventModelDtos = productModelDto.getProductEventModelList();
			List<ProductCommandModelDto>  productCommandModelDtos = productModelDto.getProductCommandModelList();
			
			WriteOptions writeOptions = WriteOptions.builder().batchSize(5000).bufferLimit(10000).flushInterval(1000)
					.jitterInterval(1000).retryInterval(5000).build();
			try(WriteApi writeApi = influxDBIniti.influxDBClient().getWriteApi(writeOptions)) {
				Point point = Point.measurement(deviceId);
				for (ProductPropertyModelDto productPropertyModelDto : productPropertyModelDtos) {
					String code = productPropertyModelDto.getCode();
					String value = data.get(code);
					point.addField(code, value);
				}
				for (ProductEventModelDto productEventModelDto : productEventModelDtos) {
					String code = productEventModelDto.getCode();
					String value = data.get(code);
					point.addField(code, value);
				}
				for (ProductCommandModelDto productCommandModelDto : productCommandModelDtos) {
					String code = productCommandModelDto.getCode();
					String value = data.get(code);
					point.addField(code, value);
				}
				point.time(Instant.now(), WritePrecision.NS);
				
				writeApi.writePoint(bucket,org,point);
			}
		}

	}

	@Override
	public List<?> findDeviceData(String deviceId, Date date) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
