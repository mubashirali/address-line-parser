package com.addressline.parser.service;

import dto.ParsedAddressLine;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Optional.empty;
import static java.util.Optional.of;

@Slf4j
public abstract class AbstractAddressTransformer {

    protected Optional<ParsedAddressLine> transformAddressToParsedAddressLine(String address, String breakPattern) {
        Pattern pattern = Pattern.compile(breakPattern);
        Matcher localMatcher = pattern.matcher(address);

        return getParsedAddressLine(address, localMatcher);
    }

    protected Optional<ParsedAddressLine> getParsedAddressLine(String address, Matcher localMatcher) {
        if (localMatcher.find()) {
            String[] addressLine = new String[2];
            addressLine[0] = address.substring(0, localMatcher.start());
            addressLine[1] = address.substring(localMatcher.start() + 1);
            return of(new ParsedAddressLine(addressLine[0], addressLine[1]));
        }

        log.debug("Unable to parse with base parser.");
        return empty();
    }
}
