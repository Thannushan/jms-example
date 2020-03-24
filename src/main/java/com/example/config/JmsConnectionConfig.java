package com.example.config;

import com.example.subscriber.Subscriber;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class JmsConnectionConfig {

    @Autowired
    AppConfig appConfig;

    @Autowired
    Subscriber subscriber;

    @Bean
    public ConnectionFactory connectionFactory(){
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(appConfig.getBrokerUrl());
        return connectionFactory;
    }

    @Bean
    public Topic inputTopic(){
        Topic topic = new ActiveMQTopic(appConfig.getInputTopicName());
        return topic;
    }

    @Bean
    public JmsTemplate inputJmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setDefaultDestination(inputTopic());
        jmsTemplate.setPubSubDomain(true);
        return  jmsTemplate;
    }

    @Bean
    public Topic outputTopic(){
        Topic topic = new ActiveMQTopic(appConfig.getOutputTopicName());
        return topic;
    }


    @Bean(name = "messageListenerOne")
    public MessageListenerContainer messageListenerContainer(){
        DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
        messageListenerContainer.setDestination(outputTopic());
        messageListenerContainer.setMessageListener(subscriber);
        messageListenerContainer.setConnectionFactory(connectionFactory());
        return messageListenerContainer;
    }
}
