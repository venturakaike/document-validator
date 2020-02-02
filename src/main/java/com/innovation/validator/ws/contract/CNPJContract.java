package com.innovation.validator.ws.contract;

import com.innovation.validator.core.model.mongo.CNPJ;
import com.innovation.validator.ws.dto.RespostaPadrao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CNPJContract {

    @GetMapping(value = "${validator.request.mapping.cnpj.validaNumero}")
    RespostaPadrao<String> validadarCNPJ(String numeroCNPJ);

    @PostMapping(value = "${validator.request.mapping.cnpj.cadastroCNPJ}")
    RespostaPadrao<CNPJ> cadastrarCNPJ(String numeroCNPJ);

    @GetMapping(value = "${validator.request.mapping.cnpj.listarCNPJ}")
    RespostaPadrao<List<CNPJ>> listarCNPJ();
}
