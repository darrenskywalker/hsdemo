package com.example.hsdemo.views.person;

import com.example.hsdemo.entities.AddressEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class AddressView implements Serializable {
    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private String zipcode;

    public AddressView(final AddressEntity addressEntity) {
        this.street = addressEntity.getStreet();
        this.city = addressEntity.getCity();
        this.state = addressEntity.getState();
        this.zipcode = addressEntity.getZipcode();
    }
}
