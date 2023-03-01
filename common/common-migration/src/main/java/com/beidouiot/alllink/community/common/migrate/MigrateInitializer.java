package com.beidouiot.alllink.community.common.migrate;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.beidouiot.alllink.community.common.migrate.config.DbConfigration;
import com.beidouiot.alllink.community.common.migrate.core.MigrationHelper;
import com.beidouiot.alllink.community.common.migrate.util.JdbcUrlUtils;

public class MigrateInitializer implements InitializingBean{

    @Autowired
    private DbConfigration dbConfigration;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        String dbName = JdbcUrlUtils.getDbName(dbConfigration.mysqlUrl);
        MigrationHelper.updateDataBase(dbConfigration, dbName);
        
    }
    
}
