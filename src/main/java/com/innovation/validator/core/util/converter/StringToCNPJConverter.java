package com.innovation.validator.core.util.converter;

import com.innovation.validator.core.model.mongo.CNPJ;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class StringToCNPJConverter implements Converter<String, CNPJ> {

    @Override
    public CNPJ convert(String numeroCNPJ) {
        if (!ObjectUtils.isEmpty(numeroCNPJ)) {
            return CNPJ.builder()
                    .numero(numeroCNPJ)
                    .build();
        }
        return null;
    }
}
