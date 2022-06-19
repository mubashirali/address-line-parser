package com.addressline.parser.service;

import com.addressline.parser.service.impl.AddressWithCommaAddressParser;
import dto.ParsedAddressLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddressWithCommaAddressParserTest {

    @InjectMocks
    private AddressWithCommaAddressParser parser;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(parser, "matcher",
                "^[A-Za-z0-9\\u00C0-\\u00D6\\u00D8-\\u00f6\\u00f8-\\u00ff\\s]" +
                        "+[,]+[A-Za-z0-9\\u00C0-\\u00D6\\u00D8-\\u00f6\\u00f8-\\u00ff\\s]*");
        ReflectionTestUtils.setField(parser, "breakPattern",
                "\\,");
    }

    @Test
    void initWithHouseNumberTest() {
        //Given
        String address = "4, rue de la revolution";

        //When
        final Optional<ParsedAddressLine> parse = parser.parse(address);

        //Then
        assertTrue(parse.isPresent());
        assertEquals("4", parse.get().getHouseNumber());
        assertEquals("rue de la revolution", parse.get().getStreet());
    }

    @Test
    void endsWithHouseNumberTest() {
        //Given
        String address = "Calle Aduana, 29";

        //When
        final Optional<ParsedAddressLine> parse = parser.parse(address);

        //Then
        assertTrue(parse.isPresent());
        assertEquals("29", parse.get().getHouseNumber());
        assertEquals("Calle Aduana", parse.get().getStreet());
    }

    @Test
    void endsWithHouseNumberWithCharsTest() {
        //Given
        String address = "Calle Aduana, 29 XYZ";

        //When
        final Optional<ParsedAddressLine> parse = parser.parse(address);

        //Then
        assertTrue(parse.isPresent());
        assertEquals("29 XYZ", parse.get().getHouseNumber());
        assertEquals("Calle Aduana", parse.get().getStreet());
    }
}
