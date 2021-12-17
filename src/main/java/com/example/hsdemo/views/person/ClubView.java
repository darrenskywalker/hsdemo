package com.example.hsdemo.views.person;

import com.example.hsdemo.entities.ClubEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NonNull
@NoArgsConstructor
public class ClubView {
    private String name;

    ClubView(final ClubEntity clubEntity) {
        this.name = clubEntity.getName();
    }
}
