package com.dxc.decoder.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.decoder.service.EncoderServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:5173")
public class EncoderController {
    
    private final EncoderServiceImpl encoderServiceImpl;

    public EncoderController(EncoderServiceImpl encoderServiceImpl){
        this.encoderServiceImpl = encoderServiceImpl;
    }

    @PostMapping("/encode")
    public ResponseEntity<Map<String,String>> encode(@RequestBody Map<String, String> message) {
        //TODO: process POST request
        String offsetSetCharacter = message.get("offsetCharacter");
        String plainText = message.get("plainText");

        String encodedString = encoderServiceImpl.encode(plainText, offsetSetCharacter);

        Map<String,String> response = new HashMap<>();
        response.put("result",encodedString);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/decode")
    public ResponseEntity<Map<String,String>> decode(@RequestBody Map<String, String> message) {
        //TODO: process POST request
         String encodedText = message.get("encodedText");

         String decodedString = encoderServiceImpl.decode(encodedText);
         Map<String,String> response = new HashMap<>();
         response.put("result", decodedString);
         
        return ResponseEntity.ok().body(response);
    }
    
    
}
