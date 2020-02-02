package com.innovation.validator.ws.controller;

import com.innovation.validator.core.model.mongo.CPF;
import com.innovation.validator.core.service.CPFService;
import com.innovation.validator.ws.contract.CPFContract;
import com.innovation.validator.ws.dto.RespostaPadrao;
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
@Api(value = "Cadastro e valida\u00E7\u00F5es de CPF", tags = "CPF")
public class CPFController implements CPFContract {

    private final CPFService cpfService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public RespostaPadrao<String> validadarCPF(String numeroCPF) {
        logger.debug("-----------> INICIO DA VALIDAÇÃO DO CPF");
        RespostaPadrao<String> response = RespostaPadrao.<String>builder()
                .conteudo(cpfService.validateCPF(numeroCPF))
                .valido(Boolean.TRUE)
                .build();
        logger.debug("-----------> FIM DA VALIDAÇÃO DO CPF");
        return response;
    }

    @Override
    public RespostaPadrao<CPF> cadastrarCPF(String numeroCPF) {
        return RespostaPadrao.<CPF>builder()
                .conteudo(cpfService.saveCPF(numeroCPF))
                .valido(Boolean.TRUE)
                .build();
    }

    @Override
    public RespostaPadrao<List<CPF>> listarCPF() {
        return RespostaPadrao.<List<CPF>>builder()
                .conteudo(cpfService.getAllCPFs())
                .valido(Boolean.TRUE)
                .build();
    }

}
