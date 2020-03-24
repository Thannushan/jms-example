package com.example.subscriber;

import com.example.config.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class Subscriber {

    private final Logger logger = LogManager.getFormatterLogger();

    @Autowired
    JmsTemplate jmsTemplate;

    MessageConverter messageConverter;


    public void onMessage(Message message) {
        String correlationId = "";
        String msg = "";
        try {
            correlationId = message.getJMSCorrelationID();
            msg = ((TextMessage) message).getText();
            messageProducer.send(message.getJMSReplyTo(), new MessageCreator(){
                @Override
                public Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage();
                }
            });
        } catch (JMSException e1) {
            e1.printStackTrace();
        } catch (JMSException e){
            e.printStackTrace();
        }

}
