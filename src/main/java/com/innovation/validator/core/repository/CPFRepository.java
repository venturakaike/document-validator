package com.innovation.validator.core.repository;

import com.innovation.validator.core.model.mongo.CPF;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CPFRepository extends MongoRepository<CPF, UUID> {

    CPF findCPFById(UUID id);

    CPF findCPFByNumero(String numeroCPF);

}