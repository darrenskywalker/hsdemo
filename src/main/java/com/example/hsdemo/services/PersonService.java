package com.example.hsdemo.services;

import com.example.hsdemo.entities.PersonEntity;
import com.example.hsdemo.repositories.ClubRepository;
import com.example.hsdemo.repositories.PersonRepository;
import com.example.hsdemo.views.person.PersonView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final ClubRepository clubRepository;

    @Transactional
    public PersonView createPerson(final PersonView personView) {
        var clubs = personView.getClubs()
                                            .stream()
                                            .map(clubView -> clubRepository.findClubEntityByName(clubView.getName())).collect(Collectors.toSet());

        var person = personRepository.save(new PersonEntity(personView, clubs));
        return new PersonView(person);
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
