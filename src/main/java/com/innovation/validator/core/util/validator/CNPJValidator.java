package com.innovation.validator.core.util.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CNPJValidator {

    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final List<String> cnpjFalhos = Arrays
            .asList("00000000000000", "11111111111111", "22222222222222", "33333333333333", "44444444444444",
                    "55555555555555", "66666666666666", "77777777777777", "88888888888888", "99999999999999");

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public boolean validarCNPJ(String cnpj) {
        String cnpjLimpo = removeCaracteresDoCNPJ(cnpj);
        if (cnpjFalhos.contains(cnpjLimpo) || (cnpjLimpo.length() != 14)) {
            return false;
        }
        Integer digito1 = calculaDigitoCNPJ(cnpjLimpo.substring(0, 12), pesoCNPJ);
        Integer digito2 = calculaDigitoCNPJ(cnpjLimpo.substring(0, 12) + digito1, pesoCNPJ);

        return cnpjLimpo.equals(cnpjLimpo.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    private Integer calculaDigitoCNPJ(String cnpj, int[] peso) {
        logger.info("Iniciando calculo dos dígitos verificadores");
        int somaValorCNPJ = 0;
        for (int indice = cnpj.length() - 1; indice >= 0; indice--) {
            int digito = Integer.parseInt(cnpj.substring(indice, indice + 1));
            somaValorCNPJ += digito * peso[peso.length - cnpj.length() + indice];
        }
        somaValorCNPJ = 11 - somaValorCNPJ % 11;
        return somaValorCNPJ > 9 ? 0 : somaValorCNPJ;
    }

    private String removeCaracteresDoCNPJ(String cnpj) {
        logger.info("Removing all characters except numbers");
        return cnpj.replaceAll("[^0-9]", "");
    }
}