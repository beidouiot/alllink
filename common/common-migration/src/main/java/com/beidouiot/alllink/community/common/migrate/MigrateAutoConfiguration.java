package com.beidouiot.alllink.community.common.migrate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beidouiot.alllink.community.common.migrate.config.DbConfigration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class MigrateAutoConfiguration {

	@Bean
	public DbConfigration dbConfigration() {
		log.info("AutoConfig init migrate DbConfigration[{}]", DbConfigration.class.getName());
	    return new DbConfigration();
	}

	@Bean
	public MigrateInitializer migrateInitializer() {
	    log.info(" AutoConfig init migrate MigrateInitializer[{}]", MigrateInitializer.class.getName());
	    return new MigrateInitializer();
	}

}
