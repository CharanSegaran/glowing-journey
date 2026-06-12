package com.dxc.decoder.service;

import org.springframework.stereotype.Service;

import com.dxc.decoder.entity.EncoderLog;
import com.dxc.decoder.repository.EncoderRepository;

@Service
public class EncoderServiceImpl implements EncoderService{

    private EncoderRepository encoderRepository;
    public EncoderServiceImpl(EncoderRepository encoderRepository){
        this.encoderRepository = encoderRepository;
    }
    
    public static final String Characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

    @Override
    public String encode (String plainText, String offsetCharacter) {

        int offsetIndex = Characters.indexOf(offsetCharacter);
        StringBuilder sb = new StringBuilder();
        sb.append(offsetCharacter);
        
        for (int i=0; i < plainText.length(); i++) {
            char currentPlainTextChar = plainText.charAt(i);
            
            int currentPlainTextCharIndex = Characters.indexOf(currentPlainTextChar);
            if(currentPlainTextCharIndex == -1){
                sb.append(currentPlainTextChar);
            }else{
                char encodedChar = Characters.charAt((currentPlainTextCharIndex - offsetIndex + Characters.length())%Characters.length());
                
                sb.append(encodedChar);
            }
        }
        saveLog(plainText, offsetCharacter, sb.toString(), "null");
        return sb.toString();

    }

    @Override
    public String decode (String encodedText){
        char offsetCharacter = encodedText.charAt(0);
        int offsetIndex = Characters.indexOf(offsetCharacter);
        StringBuilder sb = new StringBuilder();

        for (int i=1; i < encodedText.length(); i++) {
            char currentEncodedTextChar = encodedText.charAt(i);
            
            int currentEncodedTextCharIndex = Characters.indexOf(currentEncodedTextChar);
         
            if(currentEncodedTextCharIndex == -1){
                sb.append(currentEncodedTextChar);
            }else{
                char encodedChar = Characters.charAt((currentEncodedTextCharIndex + offsetIndex)%Characters.length());
    
                sb.append(encodedChar);
            }

        }
        saveLog(null, String.valueOf(offsetCharacter), encodedText, sb.toString());
        return sb.toString();

    }

    //for persistence
    public void saveLog(String plainText, String offsetCharacter, String encodedText, String decodedText){
        EncoderLog newLog = new EncoderLog();
        newLog.setDecodedText(decodedText);
        newLog.setEncodedText(encodedText);
        newLog.setOffsetCharacter(offsetCharacter);
        
        encoderRepository.save(newLog);
        System.out.println(encoderRepository.findAll());
    }
}
