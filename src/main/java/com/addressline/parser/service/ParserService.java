package com.addressline.parser.service;

import dto.ParsedAddressLine;

import java.util.Optional;

public interface ParserService {
    Optional<ParsedAddressLine> transformAddressLine(String address);
}
