package com.innovation.validator.ws.contract;

import com.innovation.validator.core.model.mongo.CPF;
import com.innovation.validator.ws.dto.RespostaPadrao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CPFContract {

    @GetMapping(value = "${validator.request.mapping.cpf.validaNumero}")
    RespostaPadrao<String> validadarCPF(@RequestParam String numeroCPF);

    @PostMapping(value = "${validator.request.mapping.cpf.cadastroCPF}")
    RespostaPadrao<CPF> cadastrarCPF(@RequestParam String numeroCPF);

    @GetMapping(value = "${validator.request.mapping.cpf.listarCPF}")
    RespostaPadrao<List<CPF>> listarCPF();

}
