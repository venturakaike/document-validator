package com.innovation.validator.core.repository;

import com.innovation.validator.core.model.mongo.CNPJ;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CNPJRepository extends MongoRepository<CNPJ, UUID> {

    CNPJ findCNPJById(UUID id);

    CNPJ findCNPJByNumero(String numeroCNPJ);

}