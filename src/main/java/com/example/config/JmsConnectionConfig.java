package com.example.config;

import com.example.subscriber.Subscriber;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
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
    public Queue inputQueue(){
        Queue queue = new ActiveMQQueue(appConfig.getInputQueueName());
        return queue;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setDefaultDestination(inputQueue());
        return  jmsTemplate;
    }

    @Bean
    public Queue outputQueue(){
        Queue queue = new ActiveMQQueue(appConfig.getOutputQueueName());
        return queue;
    }


    @Bean(name = "messageListenerOne")
    public MessageListenerContainer messageListenerContainer(){
        DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
        messageListenerContainer.setDestination(inputQueue());
        messageListenerContainer.setMessageListener(subscriber);
        messageListenerContainer.setConnectionFactory(connectionFactory());
        return messageListenerContainer;
    }
}
