package com.addressline.parser.controller;

import com.addressline.parser.exception.AddressParserException;
import com.addressline.parser.service.ParserService;
import dto.AddressRequestDTO;
import dto.ParsedAddressLine;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/address")
public class AddressParserController {

    private final ParserService parserService;

    @PostMapping("/parse")
    public ParsedAddressLine getParsedAddress(@RequestBody @Valid AddressRequestDTO address) {
        return parserService.transformAddressLine(address.getAddress())
                .orElseThrow(() -> new AddressParserException("Unable to parse Address: %s", address.getAddress()));
    }
}
