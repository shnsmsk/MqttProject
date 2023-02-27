package com.example.MqttProject.controller;

import com.example.MqttProject.MqttGateway;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/message")
public class MqttController {

    @Autowired
    MqttGateway mqtGateway;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> publish(@RequestBody String mqttMessage){
        try {
            JsonObject convertObject = new Gson().fromJson(mqttMessage,JsonObject.class);
            mqtGateway.senToMqtt(convertObject.get("message").toString().substring(1,convertObject.get("message").toString().length()-1),convertObject.get("topic").toString().substring(1,convertObject.get("topic").toString().length()-1));
            return ResponseEntity.ok("Success");
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.ok("fail");
        }
    }
}
