package com.example.hsdemo.views.person;

import com.example.hsdemo.entities.AddressEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NonNull
@NoArgsConstructor
public class AddressView {
    private String street;
    private String city;
    private String state;
    private String zipcode;

    public AddressView(final AddressEntity addressEntity) {
        this.street = addressEntity.getStreet();
        this.city = addressEntity.getCity();
        this.state = addressEntity.getState();
        this.zipcode = addressEntity.getZipcode();
    }
}
