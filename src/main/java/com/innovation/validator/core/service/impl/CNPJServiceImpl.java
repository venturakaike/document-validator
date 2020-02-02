package com.innovation.validator.core.service.impl;

import com.innovation.validator.core.model.mongo.CNPJ;
import com.innovation.validator.core.repository.CNPJRepository;
import com.innovation.validator.core.service.CNPJService;
import com.innovation.validator.core.util.Message;
import com.innovation.validator.core.util.converter.StringToCNPJConverter;
import com.innovation.validator.core.util.helper.MessageHelper;
import com.innovation.validator.core.util.validator.CNPJValidator;
import com.innovation.validator.ws.exception.ValidatorDocumentException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
public class CNPJServiceImpl implements CNPJService {

    private Message mensagem;
    private CNPJValidator cnpjValidator;
    private CNPJRepository cnpjRepository;
    private StringToCNPJConverter cnpjConverter;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CNPJServiceImpl(Message mensagem, CNPJValidator cnpjValidator, CNPJRepository cnpjRepository, StringToCNPJConverter cnpjConverter) {
        this.mensagem = mensagem;
        this.cnpjValidator = cnpjValidator;
        this.cnpjRepository = cnpjRepository;
        this.cnpjConverter = cnpjConverter;
    }

    @Override
    public String validateCNPJ(String numeroCNPJ) {
        if (ObjectUtils.isEmpty(numeroCNPJ)) {
            String mensagemErroI18n = mensagem.getMessage(MessageHelper.CNPJ_VAZIO,numeroCNPJ);
            logger.error(mensagem.getMessage(MessageHelper.CNPJ_VALIDAR_ERRO,mensagemErroI18n));
            throw new ValidatorDocumentException(mensagem.getMessage(MessageHelper.CNPJ_VALIDAR_ERRO, mensagemErroI18n));
        }
        if (cnpjValidator.validarCNPJ(numeroCNPJ)) {
            logger.debug(mensagem.getMessage(MessageHelper.CNPJ_VALIDO, numeroCNPJ));
            return mensagem.getMessage(MessageHelper.CNPJ_VALIDO, numeroCNPJ);
        } else {
            logger.debug(mensagem.getMessage(MessageHelper.CNPJ_INVALIDO, numeroCNPJ));
            return mensagem.getMessage(MessageHelper.CNPJ_INVALIDO, numeroCNPJ);
        }
    }

    @Override
    public CNPJ saveCNPJ(String numeroCNPJ) {
        numeroCNPJ = numeroCNPJ.replaceAll("[^0-9]", "");
        if (!cnpjValidator.validarCNPJ(numeroCNPJ)) {
            String mensagemErroI18n = mensagem.getMessage(MessageHelper.CNPJ_INVALIDO,numeroCNPJ);
            logger.error(mensagem.getMessage(MessageHelper.CNPJ_CADASTRAR_ERRO, mensagemErroI18n));
            throw new ValidatorDocumentException(mensagem.getMessage(MessageHelper.CNPJ_CADASTRAR_ERRO, mensagemErroI18n));
        }
        if (!verificaSeCNPJPossuiCadastro(numeroCNPJ)){
            String mensagemErroI18n = mensagem.getMessage(MessageHelper.CNPJ_DUPLICADO, numeroCNPJ);
            logger.error(mensagem.getMessage(MessageHelper.CNPJ_CADASTRAR_ERRO, mensagemErroI18n));
            throw new ValidatorDocumentException(mensagem.getMessage(MessageHelper.CNPJ_CADASTRAR_ERRO, mensagemErroI18n));
        }else{
            return cnpjRepository.insert(cnpjConverter.convert(numeroCNPJ));
        }
    }

    @Override
    public List<CNPJ> getAllCNPJ() {
        return cnpjRepository.findAll();
    }

    private Boolean verificaSeCNPJPossuiCadastro(String numeroCNPJ){
        CNPJ cnpjCadastrado = cnpjRepository.findCNPJByNumero(numeroCNPJ);
        return ObjectUtils.isEmpty(cnpjCadastrado) ;
    }

}