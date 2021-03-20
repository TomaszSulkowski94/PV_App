package com.pvapp.PVApp.Domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String secondName;
    private String city;
    private String postalCode;
    private String street;
    private String housenumber;

    public Owner(String firstName, String secondName, String city, String postalCode, String street, String housenumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.housenumber = housenumber;
    }
}
