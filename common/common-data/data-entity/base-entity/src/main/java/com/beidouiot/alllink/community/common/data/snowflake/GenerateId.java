package com.beidouiot.alllink.community.common.data.snowflake;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import com.beidouiot.alllink.community.common.base.exception.ServiceException;


/**
 * 
 *
 * @Description 生成id的方法
 * @author longww
 * @date 2021年4月11日
 */
@Transactional
public class GenerateId implements IdentifierGenerator, Configurable {
	
	/** 工作机器ID(0~7) */
	public String workid;
	
	/** 数据中心ID(0~7) */
	public String datacenterId;

	public SnowFlakeIdWorker snowFlakeIdWorker;


	/**
	 * hibernate自定义主键生成规则必须实现 IdentifierGenerator generate 为默认方法
	 */
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Long id = snowFlakeIdWorker.nextId();
		return id;
	}

    /**
     * 加载配置文件中的数据初始化snowflakeworker类
     */
//	@Override
    public void configure(Type type, Properties properties, ServiceRegistry d)
            throws MappingException {
        //加载配置文件
        InputStream is = GenerateId.class.getClassLoader().getResourceAsStream("snowflake.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("加载snowflake文件异常" + e.getMessage());
        }
        workid = properties.getProperty("workid"); 
        datacenterId = properties.getProperty("datacenterId"); 
        if (StringUtils.isNotBlank(datacenterId) && StringUtils.isNotBlank(workid)) {
            snowFlakeIdWorker = new SnowFlakeIdWorker(Long.valueOf(workid), Long.valueOf(datacenterId));
        }
    }


	public String getWorkid() {
		return workid;
	}

	public String getDataid() {
		return datacenterId;
	}
}