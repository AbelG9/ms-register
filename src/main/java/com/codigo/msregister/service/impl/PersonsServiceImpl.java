package com.codigo.msregister.service.impl;

import com.codigo.msregister.aggregates.response.ResponseBase;
import com.codigo.msregister.aggregates.response.ResponseReniec;
import com.codigo.msregister.constants.Constants;
import com.codigo.msregister.feignClient.ReniecClient;
import com.codigo.msregister.service.PersonsService;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

public class PersonsServiceImpl implements PersonsService {
    private final ReniecClient reniecClient;

    public PersonsServiceImpl(ReniecClient reniecClient) {
        this.reniecClient = reniecClient;
    }

    @Value("${token.api.reniec}")
    private String tokenReniec;

    @Override
    public ResponseBase getInfoReniec(String numero) {
        String authorization = "Bearer "+tokenReniec;
        ResponseReniec reniec = reniecClient.getInfoReniec(numero, authorization);
        if (reniec != null) {
            return new ResponseBase(Constants.CODE_SUCCESS, Constants.MESSAGE_SUCCESS, Optional.of(reniec));
        } else {
            return new ResponseBase(Constants.CODE_ERROR, Constants.MESSAGE_NON_DATA_RENIEC, Optional.empty());
        }
    }
}
