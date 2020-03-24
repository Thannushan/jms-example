package com.example.subscriber;

import com.example.config.AppConfig;
import com.example.config.JmsConnectionConfig;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class Subscriber implements SessionAwareMessageListener<Message> {

    public void onMessage(Message message, Session session) {
        String correlationId = "";
        String msg = "";
        try {
            correlationId = message.getJMSCorrelationID();
            msg = ((TextMessage) message).getText();
        } catch (JMSException e1) {
            e1.printStackTrace();
        }
        TextMessage responseMessage = new ActiveMQTextMessage();
        try {
            responseMessage.setJMSCorrelationID(message.getJMSCorrelationID());
            responseMessage.setText("Received Message: " + message.getJMSCorrelationID());
            MessageProducer messageProducer = session.createProducer(message.getJMSReplyTo());
            messageProducer.send(responseMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
