package com.example.hsdemo.controllers;

import com.example.hsdemo.services.PersonService;
import com.example.hsdemo.views.person.PersonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping(path = "post-person", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonView createPerson(@Valid @RequestBody final PersonView personView) {
        return personService.createPerson(personView);
    }

    @GetMapping(path = "get-person/{personid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonView getPerson(@PathVariable("personid") final long personId) {
        return personService.getPerson(personId);
    }

    @DeleteMapping(path = "delete-person/{personid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deletePerson(@PathVariable("personid") final long personId) {
        return personService.deletePerson(personId);
    }
}
