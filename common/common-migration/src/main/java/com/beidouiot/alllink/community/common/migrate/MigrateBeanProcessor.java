package com.beidouiot.alllink.community.common.migrate;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import com.beidouiot.alllink.community.common.migrate.config.DbConfigration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MigrateBeanProcessor extends InstantiationAwareBeanPostProcessorAdapter implements BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;
    
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (!(beanFactory instanceof ConfigurableListableBeanFactory)) {
            throw new IllegalArgumentException(
                    "AutowiredAnnotationBeanPostProcessor requires a ConfigurableListableBeanFactory: " + beanFactory);
        }

        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
        // 通过主动调用beanFactory#getBean来显示实例化目标bean
        DbConfigration dbConfigration = this.beanFactory.getBean(DbConfigration.class);
        log.info("BeanProcessor migrate init bean [{}]", dbConfigration.getClass().getName());
        MigrateInitializer migrateInitializer = this.beanFactory.getBean(MigrateInitializer.class);
        log.info("BeanProcessor migrate init bean [{}]", migrateInitializer.getClass().getName());
    }

}
