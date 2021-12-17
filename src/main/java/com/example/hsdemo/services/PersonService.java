package com.example.hsdemo.services;

import com.example.hsdemo.entities.PersonEntity;
import com.example.hsdemo.repositories.PersonRepository;
import com.example.hsdemo.views.person.PersonView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public PersonView createPerson(final PersonView personView) {
        personRepository.save(new PersonEntity(personView));
        return personView;
    }

    public PersonView getPerson(final Long personId) {
        var personEntity = personRepository.getById(personId);
        return new PersonView(personEntity);
    }

    public boolean deletePerson(final Long personId) {
        try {
            personRepository.deleteById(personId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
