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
import static org.apache.commons.lang3.math.NumberUtils.isDigits;

@Slf4j
@Service
@Order(2)
public class SimpleAddressParser extends AbstractAddressTransformer implements AddressParser {

    @Value("${address.parser.simple}")
    private String matcher;

    @Value("${address.parser.break-pattern.default}")
    private String breakPattern;

    @Override
    public Optional<ParsedAddressLine> parse(String address) {
        if (address.matches(matcher)) {
            log.info("Parsing Address with simple address parser.");

            return transformAddressToParsedAddressLine(address, breakPattern);
        }
        log.debug("Unable to parse with Simple address parser.");

        return empty();
    }


    @Override
    protected Optional<ParsedAddressLine> getParsedAddressLine(String address, Matcher localMatcher) {
        if (isDigits(address.split(" ")[0])) {
            int index = address.indexOf(' ');
            return of(new ParsedAddressLine(address.substring(index + 1), address.substring(0, index)));
        }
        return super.getParsedAddressLine(address, localMatcher);
    }
}
