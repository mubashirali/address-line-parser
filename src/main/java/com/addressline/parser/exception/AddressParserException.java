package com.addressline.parser.exception;

import static java.lang.String.format;

public class AddressParserException extends RuntimeException {

    private static final long serialVersionUID = -4000860105234244985L;

    public AddressParserException(String message, Object... args) {
        super(format(message, args));
    }
}
