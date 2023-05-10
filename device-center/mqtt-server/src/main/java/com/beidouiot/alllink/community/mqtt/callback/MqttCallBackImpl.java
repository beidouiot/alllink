package com.beidouiot.alllink.community.mqtt.callback;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.beidouiot.alllink.community.mqtt.listener.MqttBatchListener;
import com.beidouiot.alllink.community.mqtt.listener.MqttListener;
import com.beidouiot.alllink.community.mqtt.listener.MqttSubscription;
import com.beidouiot.alllink.community.mqtt.listener.MqttSubscriptionOnMethod;
import com.beidouiot.alllink.community.mqtt.listener.RegularMqttListener;
import com.beidouiot.alllink.community.mqtt.listener.RegularMqttSubscription;
import com.beidouiot.alllink.community.mqtt.listener.RegularMqttSubscriptionOnMethod;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class MqttCallBackImpl implements MqttCallback {

    public final static String KEY_VALUE_SEPARATOR = ":";

    @Resource
    private Map<String, MqttListener> consumers;
    @Resource
    private Map<String, MqttBatchListener> batchConsumers;
    @Resource
    private Map<String, RegularMqttListener> regularConsumers;

    @Override
    public void connectionLost(Throwable throwable) {
        log.error("Mqtt 连接断开", throwable);
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {

        try {
            // 单topic消费者
            consumers.values().forEach(consumer -> {
                MqttSubscription mqttSubscription = consumer.getClass().getAnnotation(MqttSubscription.class);
                //主题或者表达式匹配的消息
                if (mqttSubscription != null && Objects.equals(topic, mqttSubscription.topic())
                        && containsExpression(mqttMessage, mqttSubscription.expression())) {
                    consumer.consume(mqttMessage);
                }
            });
            // 通配符单topic消费者
            regularConsumers.values().forEach(consumer -> {
                RegularMqttSubscription regularMqttSubscription = consumer.getClass().getAnnotation(RegularMqttSubscription.class);
                //主题或者表达式匹配的消息
                if (regularMqttSubscription != null && regularTopicMatch(regularMqttSubscription.topic(), topic)
                        && containsExpression(mqttMessage, regularMqttSubscription.expression())) {
                    consumer.consume(topic, mqttMessage);
                }
            });
            // 批量topic消费者
            batchConsumers.values().forEach(consumer -> {
                Method[] methods = consumer.getClass().getMethods();
                for (Method method : methods) {
                    MqttSubscriptionOnMethod mqttSubscriptionOnMethod = method.getAnnotation(MqttSubscriptionOnMethod.class);
                    if (mqttSubscriptionOnMethod != null && Objects.equals(topic, mqttSubscriptionOnMethod.topic())
                            && containsExpression(mqttMessage, mqttSubscriptionOnMethod.expression())) {
                        consumeMsg(mqttMessage, consumer, method);
                        continue;
                    }
                    RegularMqttSubscriptionOnMethod regularMqttSubscriptionOnMethod = method.getAnnotation(RegularMqttSubscriptionOnMethod.class);
                    if (regularMqttSubscriptionOnMethod != null && regularTopicMatch(regularMqttSubscriptionOnMethod.topic(), topic)
                            && containsExpression(mqttMessage, regularMqttSubscriptionOnMethod.expression())) {
                        consumeMsg(topic, mqttMessage, consumer, method);
                    }
                }
            });
        } catch (Exception e) {
            log.error("mqtt消息回调出错", e);
        }
    }

    /**
     * 处理消息 批量通配符订阅
     *
     * @param topic       主题
     * @param mqttMessage 消息内容
     * @param consumer    消费者
     * @param method      方法
     */
    private void consumeMsg(String topic, MqttMessage mqttMessage, MqttBatchListener consumer, Method method) {
        try {
            method.invoke(consumer, topic, mqttMessage);
        } catch (Exception e) {
            log.error("MqttCallBackImpl consumeMsg error methodName={}", method.getName(), e);
        }
    }

    /**
     * 通配符topic是否匹配
     * 通配符需要满足的条件： /+/+/action/forward 匹配 /product/device/action/forward
     *
     * @param regularTopic 通配符topic
     * @param topic        订阅topic
     * @return 通配符topic是否匹配
     */
    private boolean regularTopicMatch(String regularTopic, String topic) {

        if (regularTopic == null || topic == null) {
            return false;
        }
        String[] regularTopicStr = regularTopic.split("/");
        String[] topicStr = topic.split("/");
        if (regularTopicStr.length > topicStr.length) {
            return false;
        }
        for (int i = 0; i < regularTopicStr.length; i++) {
            if (Objects.equals(regularTopicStr[i], "+")) {
                continue;
            }
            if (!Objects.equals(regularTopicStr[i], topicStr[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断表达式是否匹配
     *
     * @param mqttMessage 消息
     * @param expression  注解表达式
     * @return 表达式是否匹配
     */
    private boolean containsExpression(MqttMessage mqttMessage, String expression) {

        if (mqttMessage == null) {
            return false;
        }
        String content = new String(mqttMessage.getPayload());
        if ("".equals(expression)) {
            return true;
        }
        if (expression.contains(KEY_VALUE_SEPARATOR)) {
            String[] kv = expression.split(KEY_VALUE_SEPARATOR);
            JSONObject jsonObject = JSON.parseObject(content);
            if (Objects.equals(jsonObject.get(kv[0]), kv[1])) {
                return true;
            }
        }
        return content.contains(expression);
    }

    /**
     * 处理消息
     *
     * @param mqttMessage 消息
     * @param consumer    消费者
     * @param method      方法
     */
    private void consumeMsg(MqttMessage mqttMessage, MqttBatchListener consumer, Method method) {
        try {
            method.invoke(consumer, mqttMessage);
        } catch (Exception e) {
            log.error("MqttCallBackImpl consumeMsg error methodName={}", method.getName(), e);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

        String[] topics = iMqttDeliveryToken.getTopics();
        if (topics == null) {
            return;
        }
        for (String topic : topics) {
            log.info("topics: " + topic + " clientId: " + iMqttDeliveryToken.getClient().getClientId() + " " + "消息发布成功");
        }
    }
}
