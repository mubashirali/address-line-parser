package com.addressline.parser.service;

import dto.ParsedAddressLine;

import java.util.Optional;

public interface AddressParser {
    Optional<ParsedAddressLine> parse(String address);
}
