package com.innovation.validator.ws.contract;

import com.innovation.validator.core.model.mongo.CNPJ;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface CNPJContract {

    @GetMapping(value = "${validator.request.mapping.cnpj.validaNumero}")
    ResponseEntity<String> validadarCNPJ(String numeroCNPJ);

    @PostMapping(value = "${validator.request.mapping.cnpj.cadastroCNPJ}")
    ResponseEntity<CNPJ> cadastrarCNPJ(String numeroCNPJ);

    @GetMapping(value = "${validator.request.mapping.cnpj.listarCNPJ}")
    ResponseEntity<List<CNPJ>> listarCNPJ();
}
