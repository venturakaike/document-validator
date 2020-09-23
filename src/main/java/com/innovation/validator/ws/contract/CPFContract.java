package com.innovation.validator.ws.contract;

import com.innovation.validator.core.model.mongo.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CPFContract {

    @GetMapping(value = "${validator.request.mapping.cpf.validaNumero}")
    ResponseEntity<String> validadarCPF(@RequestParam String numeroCPF);

    @PostMapping(value = "${validator.request.mapping.cpf.cadastroCPF}")
    ResponseEntity<CPF> cadastrarCPF(@RequestParam String numeroCPF);

    @GetMapping(value = "${validator.request.mapping.cpf.listarCPF}")
    ResponseEntity<List<CPF>> listarCPF();

}