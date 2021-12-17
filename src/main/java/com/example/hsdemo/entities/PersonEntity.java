package com.example.hsdemo.entities;

import com.example.hsdemo.views.person.AddressView;
import com.example.hsdemo.views.person.ClubView;
import com.example.hsdemo.views.person.PersonView;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personalinfo_id", referencedColumnName = "id")
    private PersonalInfoEntity personalInfo;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<AddressEntity> addresses;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "club_membership",
               joinColumns = @JoinColumn(name = "person_id"),
               inverseJoinColumns = @JoinColumn(name = "club_id"))
    private Set<ClubEntity> clubs;

    public PersonEntity(final PersonView personView) {
        this.firstName = personView.getFirstName();
        this.lastName = personView.getLastName();

        this.personalInfo = new PersonalInfoEntity(personView.getPersonalInfo());
        this.addresses = convertAddresses(personView.getAddress());
        this.clubs = convertClubs(personView.getClubs());
    }

    private Set<AddressEntity> convertAddresses(final Set<AddressView> addresses) {
        return addresses.stream().map(AddressEntity::new).collect(Collectors.toSet());
    }

    private Set<ClubEntity> convertClubs(final Set<ClubView> clubs) {
        return clubs.stream().map(ClubEntity::new).collect(Collectors.toSet());
    }
}
