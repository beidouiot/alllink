package com.beidouiot.alllink.community.mqtt.listener;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MqttSubscriptionOnMethod {

    String topic();

    String expression() default "";
}
