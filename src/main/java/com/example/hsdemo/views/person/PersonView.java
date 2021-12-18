package com.example.hsdemo.views.person;

import com.example.hsdemo.entities.AddressEntity;
import com.example.hsdemo.entities.ClubEntity;
import com.example.hsdemo.entities.PersonEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PersonView {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Valid
    private PersonalInfoView personalInfo;
    @Valid
    private Set<AddressView> addresses;
    @Valid
    private Set<ClubView> clubs;

    public PersonView(final PersonEntity personEntity) {
        this.firstName = personEntity.getFirstName();
        this.lastName = personEntity.getLastName();

        this.personalInfo = new PersonalInfoView(personEntity.getPersonalInfo());
        this.addresses = convertAddresses(personEntity.getAddresses());
        this.clubs = convertClubs(personEntity.getClubs());
    }

    private Set<AddressView> convertAddresses(final Set<AddressEntity> addressEntities) {
        return addressEntities.stream().map(AddressView::new).collect(Collectors.toSet());
    }

    private Set<ClubView> convertClubs(final Set<ClubEntity> clubEntities) {
        return clubEntities.stream().map(ClubView::new).collect(Collectors.toSet());
    }
}
