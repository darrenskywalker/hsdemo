package com.example.hsdemo.controllers;

import com.example.hsdemo.exceptions.ResourceNotFoundException;
import com.example.hsdemo.security.jwt.services.JwtUtils;
import com.example.hsdemo.services.PersonService;
import com.example.hsdemo.views.person.PersonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final JwtUtils jwtUtils;

    @PostMapping(path = "post-person", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonView createPerson(@Valid @RequestBody final PersonView personView) {
        return personService.createPerson(personView);
    }

    @GetMapping(path = "get-person/{personid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonView getPerson(@PathVariable("personid") final long personId, final HttpServletResponse response) {
        try {
            return personService.getPerson(personId);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getLocalizedMessage(), e);
        }
    }

    @DeleteMapping(path = "delete-person/{personid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deletePerson(@PathVariable("personid") final long personId, final HttpServletResponse response) {
        try {
            personService.deletePerson(personId);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getLocalizedMessage(), e);
        }
    }

    @GetMapping(path = "get-public-user-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createJwtToken() {
        return jwtUtils.generateJwtToken();
    }
}
