package com.innovation.validator.ws.controller;

import com.innovation.validator.core.model.mongo.CNPJ;
import com.innovation.validator.core.service.CNPJService;
import com.innovation.validator.ws.contract.CNPJContract;
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
@Api(value = "Cadastro e valida\u00E7\u00F5es de CNPJ", tags = "CNPJ")
public class CNPJController implements CNPJContract {

    private final CNPJService cnpjService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public RespostaPadrao<String> validadarCNPJ(String numeroCNPJ) {
        logger.debug("-----------> INICIO DA VALIDA\u00C7\u00C3O DO CNPJ");
        RespostaPadrao<String> response = RespostaPadrao.<String>builder()
                .conteudo(cnpjService.validateCNPJ(numeroCNPJ))
                .valido(Boolean.TRUE)
                .build();
        logger.debug("-----------> FIM DA VALIDA\u00C7\u00C3O DO CNPJ");
        return response;
    }

    @Override
    public RespostaPadrao<CNPJ> cadastrarCNPJ(String numeroCNPJ) {
        return RespostaPadrao.<CNPJ>builder()
                .conteudo(cnpjService.saveCNPJ(numeroCNPJ))
                .valido(Boolean.TRUE)
                .build();
    }

    @Override
    public RespostaPadrao<List<CNPJ>> listarCNPJ() {
        return RespostaPadrao.<List<CNPJ>>builder()
                .conteudo(cnpjService.getAllCNPJ())
                .valido(Boolean.TRUE)
                .build();
    }
}
