package com.example.hsdemo.views.person;

import com.example.hsdemo.entities.ClubEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClubView implements Serializable {
    @NotNull
    private String name;

    public ClubView(final ClubEntity clubEntity) {
        this.name = clubEntity.getName();
    }
}
