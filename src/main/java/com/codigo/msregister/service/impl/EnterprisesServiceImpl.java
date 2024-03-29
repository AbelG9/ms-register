package com.codigo.msregister.service.impl;

import com.codigo.msregister.aggregates.request.RequestEnterprises;
import com.codigo.msregister.aggregates.response.ResponseBase;
import com.codigo.msregister.constants.Constants;
import com.codigo.msregister.entity.DocumentsTypeEntity;
import com.codigo.msregister.entity.EnterprisesEntity;
import com.codigo.msregister.entity.EnterprisesTypeEntity;
import com.codigo.msregister.repository.EnterprisesRepository;
import com.codigo.msregister.service.EnterprisesService;
import com.codigo.msregister.util.EnterprisesValidations;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class EnterprisesServiceImpl implements EnterprisesService {
    private final EnterprisesRepository enterprisesRepository;
    private final EnterprisesValidations enterprisesValidations;

    public EnterprisesServiceImpl(EnterprisesRepository enterprisesRepository, EnterprisesValidations enterprisesValidations) {
        this.enterprisesRepository = enterprisesRepository;
        this.enterprisesValidations = enterprisesValidations;
    }

    @Override
    public ResponseBase createEnterprise(RequestEnterprises requestEnterprises) {
        boolean validate = enterprisesValidations.validateInput(requestEnterprises);
        if (validate) {
            EnterprisesEntity enterprises = getEntity(requestEnterprises);
            enterprisesRepository.save(enterprises);
            return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS, Optional.of(enterprises));
        } else {
            return new ResponseBase(Constants.CODE_ERROR, Constants.MESSAGE_ERROR_DATA_NOT_VALID, null);
        }
    }

    @Override
    public ResponseBase findOneEnterprise(Integer id) {
        Optional enterprises = enterprisesRepository.findById(id);
        if (enterprises.isPresent()) {
            return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS, enterprises);
        }
        return new ResponseBase(Constants.CODE_ERROR, Constants.MESSAGE_ERROR_NON_DATA, Optional.empty());
    }

    @Override
    public ResponseBase findAllEnterprises() {
        Optional allEnterprises = Optional.of(enterprisesRepository.findAll());
        if (allEnterprises.isPresent()) {
            return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS, allEnterprises);
        }
        return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESSAGE_ZERO_ROWS, Optional.empty());
    }

    @Override
    public ResponseBase updateEnterprise(Integer id, RequestEnterprises requestEnterprises) {
        boolean existsEnterprise = enterprisesRepository.existsById(id);
        if (existsEnterprise) {
            Optional<EnterprisesEntity> enterprises = enterprisesRepository.findById(id);
            boolean validationEntity = enterprisesValidations.validateInput(requestEnterprises);
            if (validationEntity) {
                EnterprisesEntity enterprisesUpdate = getEntityUpdate(requestEnterprises, enterprises.get());
                enterprisesRepository.save(enterprisesUpdate);
                return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS, Optional.of(enterprisesUpdate));
            } else {
                return new ResponseBase(Constants.CODE_ERROR, Constants.MESSAGE_ERROR_DATA_NOT_VALID, Optional.empty());
            }
        } else {
            return new ResponseBase(Constants.CODE_ERROR, Constants.MESSAGE_ERROR_NOT_UPDATE_ENTERPRISES, Optional.empty());
        }
    }

    private EnterprisesEntity getEntity(RequestEnterprises requestEnterprises) {
        EnterprisesEntity entity = new EnterprisesEntity();
        entity.setNumDocument(requestEnterprises.getNumDocument());
        entity.setBusinessName(requestEnterprises.getBusinessName());
        entity.setTradeName(enterprisesValidations.isNullOrEmpty(requestEnterprises.getTradeName()) ? requestEnterprises.getBusinessName() : requestEnterprises.getTradeName());
        entity.setStatus(Constants.STATUS_ACTIVE);

        //Añadiendo FK
        entity.setEnterprisesTypeEntity(getEnterprisesType(requestEnterprises));
        entity.setDocumentsTypeEntity(getDocumentType(requestEnterprises));

        //Auditoria
        entity.setUserCreate(Constants.AUDIT_ADMIN);
        entity.setDateCreate(getTimeStamp());
        return entity;
    }

    private EnterprisesEntity getEntityUpdate(RequestEnterprises requestEnterprises, EnterprisesEntity enterprisesEntity) {
        enterprisesEntity.setNumDocument(requestEnterprises.getNumDocument());
        enterprisesEntity.setBusinessName(requestEnterprises.getBusinessName());
        enterprisesEntity.setTradeName(enterprisesValidations.isNullOrEmpty(requestEnterprises.getTradeName()) ? requestEnterprises.getBusinessName() : requestEnterprises.getTradeName());
        enterprisesEntity.setEnterprisesTypeEntity(getEnterprisesType(requestEnterprises));
        enterprisesEntity.setDocumentsTypeEntity(getDocumentType(requestEnterprises));
        enterprisesEntity.setUserModif(Constants.AUDIT_ADMIN);
        enterprisesEntity.setDateModif(getTimeStamp());
        return enterprisesEntity;
    }

    private EnterprisesTypeEntity getEnterprisesType(RequestEnterprises requestEnterprises) {
        EnterprisesTypeEntity typeEntity = new EnterprisesTypeEntity();
        typeEntity.setIdEnterprisesType(requestEnterprises.getEnterprisesTypeEntity());
        return typeEntity;
    }

    private DocumentsTypeEntity getDocumentType(RequestEnterprises requestEnterprises) {
        DocumentsTypeEntity typeEntity = new DocumentsTypeEntity();
        typeEntity.setIdDocumentsType(requestEnterprises.getDocumentsTypeEntity());
        return typeEntity;
    }
    
    private Timestamp getTimeStamp() {
        long currentTime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTime);
        return timestamp;
    }
}
