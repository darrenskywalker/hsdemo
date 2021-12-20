package com.example.hsdemo.services;

import com.example.hsdemo.entities.PersonEntity;
import com.example.hsdemo.exceptions.ResourceNotFoundException;
import com.example.hsdemo.repositories.ClubRepository;
import com.example.hsdemo.repositories.PersonRepository;
import com.example.hsdemo.views.person.PersonView;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final ClubRepository clubRepository;

    public PersonView createPerson(final PersonView personView) {
        var clubs =
                personView.getClubs().stream()
                        .map(clubView -> clubRepository.findClubEntityByName(clubView.getName()))
                        .filter(clubEntity -> Objects.nonNull(clubEntity))
                        .collect(Collectors.toSet());

        var person = personRepository.save(new PersonEntity(personView, clubs));
        return new PersonView(person);
    }

    public PersonView getPerson(final Long personId) {
        try {
            var personEntity = personRepository.getById(personId);
            return new PersonView(personEntity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }

    public void deletePerson(final Long personId) {
        try {
            personRepository.deleteById(personId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Resource Not Found");
        }
    }

}
