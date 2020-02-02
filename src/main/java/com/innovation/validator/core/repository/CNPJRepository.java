package com.innovation.validator.core.repository;

import com.innovation.validator.core.model.mongo.CNPJ;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CNPJRepository extends MongoRepository<CNPJ, String> {

    CNPJ findCNPJById(String id);
    CNPJ findCNPJByNumero(String numeroCNPJ);

}