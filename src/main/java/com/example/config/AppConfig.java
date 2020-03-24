package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {


    @Value("${jms.output.queue.name}")
    private static String outputQueueName;

    @Value("${jms.input.queue.name}")
    private static String inputQueueName;

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Value("${input.filepath}")
    private String inputFilepath;

    public String getOutputQueueName() {
        return outputQueueName;
    }

    public void setOutputQueueName(String outputQueueName) {
        this.outputQueueName = outputQueueName;
    }

    public String getInputQueueName() {
        return inputQueueName;
    }

    public void setInputQueueName(String inputQueueName) {
        this.inputQueueName = inputQueueName;
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
