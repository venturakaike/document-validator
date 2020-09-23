package com.innovation.validator.core.service.impl;

import com.innovation.validator.core.model.mongo.CPF;
import com.innovation.validator.core.repository.CPFRepository;
import com.innovation.validator.core.service.CPFService;
import com.innovation.validator.core.util.Message;
import com.innovation.validator.core.util.converter.StringToCPFConverter;
import com.innovation.validator.core.util.helper.MessageHelper;
import com.innovation.validator.core.util.validator.CPFValidator;
import com.innovation.validator.ws.exception.ValidatorDocumentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CPFServiceImpl implements CPFService {

    private final Message mensagem;
    private final CPFValidator cpfValidator;
    private final CPFRepository cpfRepository;
    private final StringToCPFConverter cpfConverter;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String validarCPF(String numeroCPF) {
        if (ObjectUtils.isEmpty(numeroCPF)) {
            String mensagemErroI18n = mensagem.getMessage(MessageHelper.CPF_VAZIO, numeroCPF);
            logger.error(mensagem.getMessage(MessageHelper.CPF_VALIDAR_ERRO, mensagemErroI18n));
            throw new ValidatorDocumentException(mensagem.getMessage(MessageHelper.CPF_VALIDAR_ERRO, mensagemErroI18n));
        }
        if (cpfValidator.validarCPF(numeroCPF)) {
            logger.debug(mensagem.getMessage(MessageHelper.CPF_VALIDO, numeroCPF));
            return mensagem.getMessage(MessageHelper.CPF_VALIDO, numeroCPF);
        } else {
            logger.debug(mensagem.getMessage(MessageHelper.CPF_INVALIDO, numeroCPF));
            return mensagem.getMessage(MessageHelper.CPF_INVALIDO, numeroCPF);
        }
    }

    @Override
    public CPF cadastrarCPF(String numeroCPF) {
        numeroCPF = numeroCPF.replaceAll("[^0-9]", "");
        if (!cpfValidator.validarCPF(numeroCPF)) {
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
    public List<CPF> listarCPFs() {
        return cpfRepository.findAll();
    }

    public Boolean verificaSeCPFPossuiCadastro(String numeroCPF) {
        CPF cpfCadastrado = cpfRepository.findCPFByNumero(numeroCPF);
        return ObjectUtils.isEmpty(cpfCadastrado);
    }

}