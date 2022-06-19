package com.addressline.parser.service.impl;

import com.addressline.parser.service.AbstractAddressTransformer;
import com.addressline.parser.service.AddressParser;
import dto.ParsedAddressLine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;

import static java.util.Optional.empty;
import static java.util.Optional.of;


@Slf4j
@Service
@Order(1)
public class AddressWithNOAddressParser extends AbstractAddressTransformer implements AddressParser {

    @Value("${address.parser.with-no}")
    private String matcher;

    @Value("${address.parser.break-pattern.with-no}")
    private String breakPattern;

    @Override
    public Optional<ParsedAddressLine> parse(String address) {
        if (address.toLowerCase().contains(matcher) ||
                address.toLowerCase().contains(matcher.replaceFirst(" ", ""))) {
            log.info("Parsing Address with NO address parser.");

            return transformAddressToParsedAddressLine(address, breakPattern);
        }
        log.debug("Unable to parse with NO address parser.");
        return empty();
    }

    @Override
    protected Optional<ParsedAddressLine> getParsedAddressLine(String address, Matcher localMatcher) {
        String pattern = matcher.replaceFirst(" ", "");
        if (address.toLowerCase().startsWith(pattern)) {
            return of(toParsedAddressLine(address));
        }
        return super.getParsedAddressLine(address, localMatcher);
    }

    private ParsedAddressLine toParsedAddressLine(String address) {
        final String[] addressLine = address.split(" ");
        final String houseNumber = addressLine[0] + " " + addressLine[1];
        return new ParsedAddressLine(address.replace(houseNumber, "").trim(), houseNumber);
    }
}
