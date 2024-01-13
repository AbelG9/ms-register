package com.codigo.msregister.util;

import com.codigo.msregister.aggregates.enums.ETypeDocuments;
import com.codigo.msregister.aggregates.request.RequestPersons;
import com.codigo.msregister.constants.Constants;
import com.codigo.msregister.entity.DocumentsTypeEntity;
import com.codigo.msregister.repository.DocumentsTypeRepository;
import com.codigo.msregister.repository.EnterprisesRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Slf4j
public class PersonsValidations {
    private final DocumentsTypeRepository documentsTypeRepository;
    private final EnterprisesRepository enterprisesRepository;

    public PersonsValidations(DocumentsTypeRepository documentsTypeRepository, EnterprisesRepository enterprisesRepository) {
        this.documentsTypeRepository = documentsTypeRepository;
        this.enterprisesRepository = enterprisesRepository;
    }

    public boolean validateInput(RequestPersons requestPersons) {
        if (requestPersons == null) {
            return false;
        }

        DocumentsTypeEntity documentType = documentsTypeRepository.findByCodType("01");
        if (requestPersons.getDocuments_type_id_documents_type() != Integer.valueOf(documentType.getCodType())
                || requestPersons.getNumDocument().length() != Constants.LENGTH_DNI) {
            return false;
        }

        if (isNullOrEmpty(requestPersons.getEmail()) || isNullOrEmpty(requestPersons.getNumDocument())
                || isNullOrEmpty(requestPersons.getTelephone())) {
            return false;
        }
        boolean existsEnterprise = enterprisesRepository.existsById(requestPersons.getEnterprises_id_enterprises());

        return existsEnterprise ? true:false;
    }
    public boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
