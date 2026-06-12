package com.dxc.decoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.dxc.decoder.service.EncoderServiceImpl;

@SpringBootTest
class DecoderApplicationTests {

	@Autowired
	private EncoderServiceImpl encoderServiceImpl;

	@Test
	void testEncodeHELLOWORLDOffsetB() {

		String encodedText = encoderServiceImpl.encode("HELLO WORLD", "B");
		assertEquals("BGDKKN VNQKC", encodedText);
	}

	@Test
	void testDecodeBGDKKNVNQKCOffsetB(){
		String decodedText = encoderServiceImpl.decode("BGDKKN VNQKC");
		assertEquals("HELLO WORLD", decodedText);
	}

	@Test
	void unknownCharactersRemainUnchanged(){
		String encodedText = encoderServiceImpl.encode("HELLO WORLD", "B");
		assertTrue(encodedText.contains(" "));
	}

	@Test
	void retrnOriginalTextAfterEncodingDecoding(){
		String plaintext = "HELLO WORLD";
		String encodedText = encoderServiceImpl.encode(plaintext, "B");
		String decodedText = encoderServiceImpl.decode(encodedText);

		assertEquals(decodedText, plaintext);
	}

}
