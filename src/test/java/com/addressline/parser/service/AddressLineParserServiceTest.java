package com.addressline.parser.service;

import com.addressline.parser.service.impl.AddressLineParserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressLineParserServiceTest {

    @InjectMocks
    private AddressLineParserServiceImpl parserService;

    @Mock
    private List<AddressParser> parsers;

    @Test
    void simpleAddressEmptyResponseTest() {
        //Given
        String address = "4 rue de";

        //When
        when(parsers.stream()).thenReturn(Stream.of());

        //Then
        assertFalse(parserService.transformAddressLine(address).isPresent());
    }
}
