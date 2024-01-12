package com.codigo.msregister.service;

import com.codigo.msregister.aggregates.response.ResponseBase;

public interface PersonsService {
    ResponseBase getInfoReniec(String numero);
}
