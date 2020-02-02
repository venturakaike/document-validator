package com.innovation.validator.core.repository;

import com.innovation.validator.core.model.mongo.CPF;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CPFRepository extends MongoRepository<CPF, String> {

    CPF findCPFById();
    CPF findCPFByNumero(String numeroCPF);

}