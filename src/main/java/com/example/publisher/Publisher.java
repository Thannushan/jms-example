package com.example.publisher;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class Publisher {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(String checkMessage,String correlationId) throws JMSException{
        jmsTemplate.sendAndReceive(new MessageCreator(){
            @Override
            public Message createMessage(Session session) throws JMSException {
                Destination tempDest = session.createTemporaryQueue();
                MessageConsumer responseConsumer = session.createConsumer(tempDest);
                Message message = session.createTextMessage(checkMessage);
                message.setJMSCorrelationID(correlationId);
                message.setJMSReplyTo(tempDest);
                return message;
            }
        });
    }

}
