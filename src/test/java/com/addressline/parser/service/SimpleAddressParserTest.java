package com.addressline.parser.service;

import com.addressline.parser.service.impl.SimpleAddressParser;
import dto.ParsedAddressLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class SimpleAddressParserTest {

    @InjectMocks
    private SimpleAddressParser parser;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(parser, "matcher", "^[0-9A-Za-z" +
                "\\u00C0-\\u00D6\\u00D8-\\u00f6\\u00f8-\\u00ff\\s]*");
        ReflectionTestUtils.setField(parser, "breakPattern",
                "\\s\\d");

    }

    @Test
    void initWithHouseNumberTest() {
        //Given
        String address = "200 Broadway ß Ä à";

        //When
        final Optional<ParsedAddressLine> parse = parser.parse(address);

        //Then
        assertTrue(parse.isPresent());
        assertEquals("Broadway ß Ä à", parse.get().getStreet());
        assertEquals("200", parse.get().getHouseNumber());
    }


    @Test
    void endsWithHouseNumberTest() {
        //Given
        String address = "Am Bächle 123 ß Ä à";

        //When
        final Optional<ParsedAddressLine> parse = parser.parse(address);

        //Then
        assertTrue(parse.isPresent());
        assertEquals("Am Bächle", parse.get().getStreet());
        assertEquals("123 ß Ä à", parse.get().getHouseNumber());
    }
}
