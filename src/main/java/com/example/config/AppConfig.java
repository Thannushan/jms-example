package com.example.config;

import com.example.subscriber.Subscriber;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.Topic;

@Component
public class AppConfig {


    @Value("${jms.output.topic.name}")
    private String outputTopicName;

    @Value("${jms.input.topic.name}")
    private String inputTopicName;

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Value("${input.filepath}")
    private String inputFilepath;

    public String getOutputTopicName() {
        return outputTopicName;
    }

    public void setOutputTopicName(String outputTopicName) {
        this.outputTopicName = outputTopicName;
    }

    public String getInputTopicName() {
        return inputTopicName;
    }

    public void setInputTopicName(String inputTopicName) {
        this.inputTopicName = inputTopicName;
    }

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public String getInputFilepath() {
        return inputFilepath;
    }

    public void setInputFilepath(String inputFilepath) {
        this.inputFilepath = inputFilepath;
    }
}
