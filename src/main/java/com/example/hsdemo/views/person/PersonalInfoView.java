package com.example.hsdemo.views.person;

import com.example.hsdemo.entities.PersonalInfoEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class PersonalInfoView implements Serializable {
    @NotNull
    private String email;
    @NotNull
    private String mobile;

    public PersonalInfoView(final PersonalInfoEntity personalInfoEntity) {
        this.email = personalInfoEntity.getEmail();
        this.mobile = personalInfoEntity.getMobile();
    }
}
