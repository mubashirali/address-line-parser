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
@Order(3)
public class AddressWithCommaAddressParser extends AbstractAddressTransformer implements AddressParser {

    @Value("${address.parser.address-with-comma}")
    private String matcher;

    @Value("${address.parser.break-pattern.comma}")
    private String breakPattern;

    @Override
    public Optional<ParsedAddressLine> parse(String address) {
        if (address.matches(matcher)) {
            log.info("Parsing Address with comma parser.");

            return transformAddressToParsedAddressLine(address, breakPattern);
        }
        log.debug("Unable to parse with comma parser.");
        return empty();
    }


    @Override
    protected Optional<ParsedAddressLine> getParsedAddressLine(String address, Matcher localMatcher) {
        if (localMatcher.find()) {
            String[] addressLine = new String[2];
            addressLine[0] = address.substring(0, localMatcher.start());
            addressLine[1] = address.substring(localMatcher.start() + 1);

            return of(toParsedAddressLine(addressLine));
        }
        log.debug("Unable to parse with comma parser.");
        return empty();
    }

    private ParsedAddressLine toParsedAddressLine(String[] addressLine) {
        return isDigits(addressLine[0]) ?
                new ParsedAddressLine(addressLine[1].trim(), addressLine[0]) :
                new ParsedAddressLine(addressLine[0], addressLine[1].trim());
    }
}
