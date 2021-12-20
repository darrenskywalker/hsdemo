package com.example.hsdemo.entities;

import com.example.hsdemo.views.person.AddressView;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "address")
@NoArgsConstructor
@Getter
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    public AddressEntity(final AddressView addressView) {
        this.street = addressView.getStreet();
        this.city = addressView.getCity();
        this.state = addressView.getState();
        this.zipcode = addressView.getZipcode();
    }
}
