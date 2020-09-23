package com.innovation.validator.ws.controller;

import com.innovation.validator.core.model.mongo.CNPJ;
import com.innovation.validator.core.service.CNPJService;
import com.innovation.validator.ws.contract.CNPJContract;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "Cadastro e valida\u00E7\u00F5es de CNPJ", tags = "CNPJ")
public class CNPJController implements CNPJContract {

    private final CNPJService cnpjService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ResponseEntity<String> validadarCNPJ(String numeroCNPJ) {
        logger.debug("-----------> INICIO DA VALIDA\u00C7\u00C3O DO CNPJ", numeroCNPJ);
        return ResponseEntity.status(HttpStatus.OK).body(cnpjService.validarCNPJ(numeroCNPJ));
    }

    @Override
    public ResponseEntity<CNPJ> cadastrarCNPJ(String numeroCNPJ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cnpjService.cadastrarCNPJ(numeroCNPJ));
    }

    @Override
    public ResponseEntity<List<CNPJ>> listarCNPJ() {
        return ResponseEntity.status(HttpStatus.OK).body(cnpjService.listarCNPJs());
    }
}