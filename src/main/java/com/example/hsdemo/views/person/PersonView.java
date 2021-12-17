package com.example.hsdemo.views.person;

import com.example.hsdemo.entities.AddressEntity;
import com.example.hsdemo.entities.ClubEntity;
import com.example.hsdemo.entities.PersonEntity;
import lombok.Data;
import lombok.NonNull;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NonNull
public class PersonView {
    private String firstName;
    private String lastName;
    private PersonalInfoView personalInfo;
    private Set<AddressView> address;
    private Set<ClubView> clubs;

    public PersonView(final PersonEntity personEntity) {
        this.firstName = personEntity.getFirstName();
        this.lastName = personEntity.getLastName();

        this.personalInfo = new PersonalInfoView(personEntity.getPersonalInfo());
        this.address = convertAddresses(personEntity.getAddresses());
        this.clubs = convertClubs(personEntity.getClubs());
    }

    private Set<AddressView> convertAddresses(final Set<AddressEntity> addressEntities) {
        return addressEntities.stream().map(AddressView::new).collect(Collectors.toSet());
    }

    private Set<ClubView> convertClubs(final Set<ClubEntity> clubEntities) {
        return clubEntities.stream().map(ClubView::new).collect(Collectors.toSet());
    }
}
