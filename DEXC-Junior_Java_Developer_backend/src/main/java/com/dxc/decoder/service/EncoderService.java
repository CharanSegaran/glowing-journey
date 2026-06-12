package com.dxc.decoder.service;

public interface EncoderService {
    public String encode (String plainText, String offsetCharacter);
    public String decode (String encodedText);
}
