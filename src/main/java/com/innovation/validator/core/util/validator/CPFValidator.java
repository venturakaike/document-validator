package com.innovation.validator.core.util.validator;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CPFValidator {

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final List<String> cpfFalhos = Arrays
            .asList("00000000000", "11111111111", "22222222222", "33333333333", "44444444444",
                    "55555555555", "66666666666", "77777777777", "88888888888", "99999999999");

    public Boolean validarCPF(String cpf) {
        String cpfLimpo = removeCaracteresDoCPF(cpf);
        if ((cpfLimpo.length() != 11) || cpfFalhos.contains(cpfLimpo)) {
            return false;
        }
        Integer digito1 = calculaDigitoCPF(cpfLimpo.substring(0, 9), pesoCPF);
        Integer digito2 = calculaDigitoCPF(cpfLimpo.substring(0, 9) + digito1, pesoCPF);
        return cpfLimpo.equals(cpfLimpo.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    public Integer calculaDigitoCPF(String cpf, int[] peso) {
        int somaValorCPF = 0;
        for (int indice = cpf.length() - 1; indice >= 0; indice--) {
            int digito = Integer.parseInt(cpf.substring(indice, indice + 1));
            somaValorCPF += digito * peso[peso.length - cpf.length() + indice];
        }
        somaValorCPF = 11 - somaValorCPF % 11;
        return somaValorCPF > 9 ? 0 : somaValorCPF;
    }

    public String removeCaracteresDoCPF(String document) {
        return document.replaceAll("[^0-9]", "");
    }
}