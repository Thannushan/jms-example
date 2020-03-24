package com.example.shell;

import com.example.config.AppConfig;
import com.example.config.JmsConnectionConfig;
import com.example.publisher.Publisher;
import com.example.subscriber.Subscriber;
import com.example.util.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.jms.JMSException;

@ShellComponent
public class ActiveMQDemoShell {

    @Autowired
    JsonHelper jsonHelper;

    @Autowired
    Publisher publisher;

    @Autowired
    AppConfig appConfig;

    @Autowired
    JmsConnectionConfig jmsConnectionConfig;

    @ShellMethod(value="Send JSON Message")
    public void send(String filename){
        try {
            publisher.sendMessage(jsonHelper.convertJsonToString(filename),jsonHelper.getCorrelationID(filename));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}
