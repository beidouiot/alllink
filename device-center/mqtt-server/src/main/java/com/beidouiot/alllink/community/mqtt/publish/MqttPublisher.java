package com.beidouiot.alllink.community.mqtt.publish;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * mqtt消息发布
 *
 */
@Component
@Slf4j
@Data
public class MqttPublisher {

    private Integer qos = 1;
    private Boolean retained = false;
    private String topic;
    private String message;

    @Resource
    private MqttClient mqttClient;

    /**
     * mqtt 发送消息
     *
     * @param qos      消息服务质量
     * @param retained 是否保留消息
     * @param topic    主题
     * @param message  消息内容
     * @return 发布结果
     */
    public boolean publish(Integer qos, Boolean retained, String topic, String message) {

        try {
            if (qos == null) {
                qos = this.qos;
            }
            if (retained == null) {
                retained = this.retained;
            }
            if (topic == null || message == null) {
                throw new RuntimeException("topic and message can not been null");
            }
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(qos);
            mqttMessage.setRetained(retained);
            mqttMessage.setPayload(message.getBytes());
            MqttTopic mqttTopic = mqttClient.getTopic(topic);
            log.info("MqttPublisher publish topic={} message={}", topic, message);
            MqttDeliveryToken mqttDeliveryToken = mqttTopic.publish(mqttMessage);
            mqttDeliveryToken.waitForCompletion();
            log.info("MqttPublisher publish success");
            return true;
        } catch (Exception e) {
            log.error("MqttPublisher publish error topic={} message={}", topic, message, e);
            return false;
        }
    }

    /**
     * 构建器
     *
     * @return this
     */
    public MqttPublisherBuilder builder() {

        return new MqttPublisherBuilder(this);
    }

    public static class MqttPublisherBuilder {

        private MqttPublisher mqttPublisher;

        public MqttPublisherBuilder(MqttPublisher mqttPublisher) {

            this.mqttPublisher = mqttPublisher;
        }

        /**
         * 设置qos
         *
         * @param qos 消息服务质量
         * @return this
         */
        public MqttPublisherBuilder qos(int qos) {

            this.mqttPublisher.setQos(qos);
            return this;
        }

        /**
         * 设置retained
         *
         * @param retained 是否保留消息
         * @return this
         */
        public MqttPublisherBuilder retained(boolean retained) {

            this.mqttPublisher.setRetained(retained);
            return this;
        }

        /**
         * 设置topic
         *
         * @param topic 主題
         * @return this
         */
        public MqttPublisherBuilder topic(String topic) {

            this.mqttPublisher.setTopic(topic);
            return this;
        }

        /**
         * 设置message
         *
         * @param message 消息内容
         * @return this
         */
        public MqttPublisherBuilder message(String message) {

            this.mqttPublisher.setMessage(message);
            return this;
        }

        /**
         * 发布消息
         *
         * @return 发布结果
         */
        public boolean publish() {

            return this.mqttPublisher.publish(
                    this.mqttPublisher.getQos(),
                    this.mqttPublisher.getRetained(),
                    this.mqttPublisher.getTopic(),
                    this.mqttPublisher.getMessage()
            );
        }
    }
}
