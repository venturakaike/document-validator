package com.innovation.validator.core.service.impl;

import com.innovation.validator.core.model.mongo.CPF;
import com.innovation.validator.core.repository.CPFRepository;
import com.innovation.validator.core.service.CPFService;
import com.innovation.validator.core.util.Message;
import com.innovation.validator.core.util.converter.StringToCPFConverter;
import com.innovation.validator.core.util.helper.MessageHelper;
import com.innovation.validator.core.util.validator.CPFValidator;
import com.innovation.validator.ws.exception.ValidatorDocumentException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class CPFServiceImpl implements CPFService {

    private Message mensagem;
    private CPFValidator cpfValidator;
    private CPFRepository cpfRepository;
    private StringToCPFConverter cpfConverter;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CPFServiceImpl(Message mensagem, CPFValidator cpfValidator, CPFRepository cpfRepository, StringToCPFConverter cpfConverter) {
        this.mensagem = mensagem;
        this.cpfValidator = cpfValidator;
        this.cpfRepository = cpfRepository;
        this.cpfConverter = cpfConverter;
    }

    @Override
    public String validateCPF(String numeroCPF) {
        if (ObjectUtils.isEmpty(numeroCPF)) {
            String mensagemErroI18n = mensagem.getMessage(MessageHelper.CPF_VAZIO, numeroCPF);
            logger.error(mensagem.getMessage(MessageHelper.CPF_VALIDAR_ERRO, mensagemErroI18n));
            throw new ValidatorDocumentException(mensagem.getMessage(MessageHelper.CPF_VALIDAR_ERRO, mensagemErroI18n));
        }
        if (cpfValidator.validateCPF(numeroCPF)) {
            logger.debug(mensagem.getMessage(MessageHelper.CPF_VALIDO, numeroCPF));
            return mensagem.getMessage(MessageHelper.CPF_VALIDO, numeroCPF);
        } else {
            logger.debug(mensagem.getMessage(MessageHelper.CPF_INVALIDO, numeroCPF));
            return mensagem.getMessage(MessageHelper.CPF_INVALIDO, numeroCPF);
        }
    }

    @Override
    public CPF saveCPF(String numeroCPF) {
        numeroCPF = numeroCPF.replaceAll("[^0-9]", "");
        if (!cpfValidator.validateCPF(numeroCPF)) {
            String mensagemErroI18n = mensagem.getMessage(MessageHelper.CPF_INVALIDO, numeroCPF);
            logger.error(mensagem.getMessage(MessageHelper.CPF_CADASTRAR_ERRO, mensagemErroI18n));
            throw new ValidatorDocumentException(mensagem.getMessage(MessageHelper.CPF_CADASTRAR_ERRO, mensagemErroI18n));
        }
        if (!verificaSeCPFPossuiCadastro(numeroCPF)) {
            String mensagemErroI18n = mensagem.getMessage(MessageHelper.CPF_DUPLICADO, numeroCPF);
            logger.error(mensagem.getMessage(MessageHelper.CPF_CADASTRAR_ERRO, mensagemErroI18n));
            throw new ValidatorDocumentException(mensagem.getMessage(MessageHelper.CPF_CADASTRAR_ERRO, mensagemErroI18n));
        } else {
            return cpfRepository.insert(cpfConverter.convert(numeroCPF));
        }
    }

    @Override
    public List<CPF> getAllCPFs() {
        return cpfRepository.findAll();
    }

    @Override
    public List<CPF> createValidDocuments() {
        Random random = new Random();
        String document = null;
        for (int i = 1; i >= 11; i++)
            System.out.println();
            return null;
    }

    private Boolean verificaSeCPFPossuiCadastro(String numeroCPF) {
        CPF cpfCadastrado = cpfRepository.findCPFByNumero(numeroCPF);
        return ObjectUtils.isEmpty(cpfCadastrado);
    }

}