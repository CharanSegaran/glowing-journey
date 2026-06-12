package com.dxc.decoder.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EncoderLog {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String plainText;
    private String offsetCharacter;
    private String encodedText;
    private String decodedText;
    private LocalDateTime localDateTime;
    
    public EncoderLog(){};

    public EncoderLog(String plainText, String offsetCharacter, String encodedText, String decodedText){
        this.plainText = plainText;
        this.offsetCharacter = offsetCharacter;
        this.encodedText = encodedText;
        this.decodedText = decodedText;
        this.localDateTime = LocalDateTime.now();
    }
}
