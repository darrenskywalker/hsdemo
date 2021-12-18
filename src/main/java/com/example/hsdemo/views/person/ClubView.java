package com.example.hsdemo.views.person;

import com.example.hsdemo.entities.ClubEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ClubView {
    @NotNull
    private String name;

    ClubView(final ClubEntity clubEntity) {
        this.name = clubEntity.getName();
    }
}
