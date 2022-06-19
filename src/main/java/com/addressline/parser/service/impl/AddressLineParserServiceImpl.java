package com.addressline.parser.service.impl;

import com.addressline.parser.service.AddressParser;
import com.addressline.parser.service.ParserService;
import dto.ParsedAddressLine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressLineParserServiceImpl implements ParserService {

    private final List<AddressParser> addressParser;

    @Override
    public Optional<ParsedAddressLine> transformAddressLine(String address) {
        log.info("Parsing address: {}", address);

        return addressParser.stream()
                .map(p -> p.parse(address.trim()))
                .filter(Optional::isPresent)
                .findFirst()
                .orElse(empty());
    }
}
