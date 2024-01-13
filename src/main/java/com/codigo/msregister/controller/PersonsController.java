package com.codigo.msregister.controller;

import com.codigo.msregister.aggregates.request.RequestPersons;
import com.codigo.msregister.aggregates.response.ResponseBase;
import com.codigo.msregister.service.PersonsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/persons")
public class PersonsController {
    private final PersonsService personsService;

    public PersonsController(PersonsService personsService) {
        this.personsService = personsService;
    }

    @GetMapping("{numero}")
    public ResponseBase getInfoReniec(@PathVariable String numero){
        return personsService.getInfoReniec(numero);
    }

    @PostMapping
    public ResponseBase createPerson(@RequestBody RequestPersons requestPersons){
        return personsService.createPersons(requestPersons);
    }

    @PostMapping("{id}")
    public ResponseBase findOnePerson(@PathVariable int id) {
        return personsService.findOnePerson(id);
    }

    @GetMapping
    public ResponseBase findAll() {
        return personsService.findAllPersons();
    }

    @PatchMapping("{id}")
    public ResponseBase updatePerson(@PathVariable int id, @RequestBody RequestPersons requestPersons) {
        return personsService.updatePerson(id, requestPersons);
    }
}
