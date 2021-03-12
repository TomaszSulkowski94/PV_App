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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String manufacturer;
    private String model;
    private roofType roofType;
    private roofMaterial roofMaterial;
    private double price;

    public enum roofType {
        DACH_PLASKI, DACH_SKOSNY, GRUNT
    }

    public enum roofMaterial{
        BLACHODACHOWKA, BLACHOTRAPEZ, PLYTA_WARSTWOWA, PAPA, GONT, GRUNT
    }


}
