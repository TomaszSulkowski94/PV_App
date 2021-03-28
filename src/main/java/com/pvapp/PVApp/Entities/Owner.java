package com.pvapp.PVApp.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    private String firstname;
    private String secondname;
    private String district;
    private String city;
    private String postalcode;
    private String street;
    private String housenumber;
    private String mailadress;
    private String phonenumber;

//    @OneToMany(mappedBy = "owner")
//    private List<Instalation> instalationList;


    public Owner(String firstname, String secondname, String district, String city, String postalcode, String street, String housenumber, String mailadress, String phonenumber) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.district = district;
        this.city = city;
        this.postalcode = postalcode;
        this.street = street;
        this.housenumber = housenumber;
        this.mailadress = mailadress;
        this.phonenumber = phonenumber;
    }
}
