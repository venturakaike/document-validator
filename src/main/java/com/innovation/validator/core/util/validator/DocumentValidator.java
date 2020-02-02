package com.innovation.validator.core.util.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DocumentValidator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String removeLettersAndSpecialCharactersFromDocument(String document) {
        logger.info("Removing all characters except numbers");
        return document.replaceAll("[^0-9]", "");
    }
}