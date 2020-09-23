package com.innovation.validator.core.service;

import com.innovation.validator.core.model.mongo.CPF;

import java.util.List;

public interface CPFService {

    String validarCPF(String numeroCPF);

    CPF cadastrarCPF(String numeroCPF);

    List<CPF> listarCPFs();

}