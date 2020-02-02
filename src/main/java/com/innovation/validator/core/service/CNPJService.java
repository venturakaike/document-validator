package com.innovation.validator.core.service;

import com.innovation.validator.core.model.mongo.CNPJ;

import java.util.List;

public interface CNPJService {

    String validateCNPJ(String numeroCNPJ);
    CNPJ saveCNPJ(String numeroCNPJ);
    List<CNPJ> getAllCNPJ();

}