package com.addressline.parser.service;

import com.addressline.parser.service.impl.AddressWithNOAddressParser;
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
class AddressWithNOAddressParserTest {

    @InjectMocks
    private AddressWithNOAddressParser parser;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(parser, "matcher", " no ");
        ReflectionTestUtils.setField(parser, "breakPattern",
                "\\s(No)\\s");

    }

    @Test
    void initWithHouseNumberTest() {
        //Given
        String address = "No 1540 Calle 39";

        //When
        final Optional<ParsedAddressLine> parse = parser.parse(address);

        //Then
        assertTrue(parse.isPresent());
        assertEquals("Calle 39", parse.get().getStreet());
        assertEquals("No 1540", parse.get().getHouseNumber());
    }

    @Test
    void endsWithHouseNumberTest() {
        //Given
        String address = "Calle 39 No 154ß Ä à";

        //When
        final Optional<ParsedAddressLine> parse = parser.parse(address);

        //Then
        assertTrue(parse.isPresent());
        assertEquals("Calle 39", parse.get().getStreet());
        assertEquals("No 154ß Ä à", parse.get().getHouseNumber());
    }
}
