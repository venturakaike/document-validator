package com.innovation.validator.core.service;

import com.innovation.validator.core.model.mongo.CPF;

import java.util.List;

public interface CPFService {

    String validateCPF(String numeroCPF);
    CPF saveCPF(String numeroCPF);
    List<CPF> getAllCPFs();
    List<CPF> createValidDocuments();

}