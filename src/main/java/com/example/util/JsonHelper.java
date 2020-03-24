package com.example.util;


import com.example.config.AppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


@Component
public class JsonHelper {

    private static final Logger logger = LogManager.getFormatterLogger();

    @Autowired
    AppConfig appConfig;


    public String getFullPath(String filename) {
        String inputPath = "";
        if (!appConfig.getInputFilepath().endsWith("/")) {
            inputPath = appConfig.getInputFilepath() + "/";
        } else {
            inputPath = appConfig.getInputFilepath();
        }
        logger.info("[INFO] - Input Filename: " + inputPath + filename);
        return inputPath + filename;
    }

    public JSONObject getJsonObject(String filename){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(new FileReader(getFullPath(filename)));
        } catch (FileNotFoundException e) {
            logger.error("[ERROR] - File: " + filename + " not found");
            logger.error(e);
            e.printStackTrace();
        } catch (ParseException e) {
            logger.error("[ERROR] - File: " + filename + " parse failure");
            logger.error(e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("[ERROR] - File: " + filename + " error while reading");
            logger.error(e);
            e.printStackTrace();
        }
        logger.info("[INFO] - JSON Object created successfully");
        return jsonObject;
    }

    public String convertJsonToString(String filename) {
        String s = "";
        try {
            String text = new String(Files.readAllBytes(Paths.get(getFullPath(filename))));
            s = text;
        } catch (IOException e) {
            logger.error("[ERROR] - JSON file" + filename + " failed to be converted to String");
            logger.error(e);
            e.printStackTrace();
        }
        logger.info("[INFO] - Successfully converted JSON file to string");
        return s;
    }

    public String getCorrelationID(String filename){
        String correlationId = (String) getJsonObject(filename).get("correlationId");
        logger.info("[Info] - Correlation ID: " + correlationId);
        return correlationId;
    }
}
