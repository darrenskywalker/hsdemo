package com.example.hsdemo.views.person;

import com.example.hsdemo.entities.PersonalInfoEntity;
import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class PersonalInfoView {
    private String email;
    private String mobile;

    public PersonalInfoView(final PersonalInfoEntity personalInfoEntity) {
        this.email = personalInfoEntity.getEmail();
        this.mobile = personalInfoEntity.getMobile();
    }
}
