package com.example.hsdemo.entities;

import com.example.hsdemo.views.person.PersonalInfoView;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "personal_info")
@NoArgsConstructor
@Getter
public class PersonalInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    public PersonalInfoEntity(final PersonalInfoView personalInfoView) {
        this.email = personalInfoView.getEmail();
        this.mobile = personalInfoView.getMobile();
    }
}
