package com.pvapp.PVApp.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressid")
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private District district;
    private String city;
    private String postalcode;
    private String street;
    private int housenumber;

    public enum District{
        Dolnośląskie,Kujawsko_Pomorskie,Lubelski,Lubuskie,Łódzkie,Małopolskie,Mazowieckie,Opolskie,Podkarpackie,Podlaskie,Pomorskie,Śląskie,Świętokrzyskie,Warmińsko_Mazurskie,Wielkopolskie,Zachodniopomorskie
    }

    @ManyToOne
    @JoinColumn(name = "ownerid")
    private Owner owner;

    public Address(District district, String city, String postalcode, String street, int housenumber) {
        this.district = district;
        this.city = city;
        this.postalcode = postalcode;
        this.street = street;
        this.housenumber = housenumber;
    }

}
