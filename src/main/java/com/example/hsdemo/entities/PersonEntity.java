package com.example.hsdemo.entities;

import com.example.hsdemo.views.person.AddressView;
import com.example.hsdemo.views.person.PersonView;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "person")
@NoArgsConstructor
@Getter
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personalinfo_id", referencedColumnName = "id")
    private PersonalInfoEntity personalInfo;

    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    private Set<AddressEntity> addresses;

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "club_membership",
               joinColumns = @JoinColumn(name = "person_id"),
               inverseJoinColumns = @JoinColumn(name = "club_id"))
    private Set<ClubEntity> clubs;

    public PersonEntity(final PersonView personView, final Set<ClubEntity> clubEntities) {
        this.firstName = personView.getFirstName();
        this.lastName = personView.getLastName();

        this.personalInfo = new PersonalInfoEntity(personView.getPersonalInfo());
        this.addresses = convertAddresses(personView.getAddresses());
        this.clubs = filterNewClubs(personView, clubEntities);
        this.clubs.addAll(clubEntities);
    }

    private Set<AddressEntity> convertAddresses(final Set<AddressView> addresses) {
        return addresses.stream().map(AddressEntity::new).collect(Collectors.toSet());
    }

    private Set<ClubEntity> filterNewClubs(final PersonView personView, final Set<ClubEntity> clubEntities) {
        Set<String> clubEntityNames = clubEntities.stream().filter(Objects::nonNull).map(ClubEntity::getName).collect(Collectors.toSet());
        return personView.getClubs().stream()
                .filter(clubView -> !clubEntityNames.contains(clubView.getName()))
                .map(ClubEntity::new)
                .collect(Collectors.toSet());
    }
}
