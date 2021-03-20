package com.pvapp.PVApp.Domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="construction")
public class Construction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String manufacturer;
    private String model;
    private roofType rooftype;
    private roofMaterial roofmaterial;
    private double price;

    public enum roofType {
        DACH_PLASKI, DACH_SKOSNY, GRUNT
    }

    public enum roofMaterial{
        BLACHODACHOWKA, BLACHOTRAPEZ, PLYTA_WARSTWOWA, PAPA, GONT, GRUNT
    }

    public Construction(String manufacturer, String model, roofType rooftype, roofMaterial roofmaterial, double price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.rooftype = rooftype;
        this.roofmaterial = roofmaterial;
        this.price = price;
    }
}
