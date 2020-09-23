package com.innovation.validator.core.service;

import com.innovation.validator.core.model.mongo.CNPJ;

import java.util.List;

public interface CNPJService {

    String validarCNPJ(String numeroCNPJ);

    CNPJ cadastrarCNPJ(String numeroCNPJ);

    List<CNPJ> listarCNPJs();

}