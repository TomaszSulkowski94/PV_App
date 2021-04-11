package com.pvapp.PVApp.Entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "construction")
public class Construction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "constructionid")
    private int id;

    @NotEmpty(message = "Podaj producenta konstrukcji")
    @Size(min = 3, max = 20, message = "Pole musi przyjmować wartości z zakresu 3 do 20 znaków")
    private String manufacturer;

    @NotEmpty(message = "Podaj producenta invertera")
    @Size(min = 3, max = 20, message = "Pole musi przyjmować wartości z zakresu 3 do 20 znaków")
    private String model;


    @Enumerated(EnumType.STRING)
    public roofType rooftype;


    @Enumerated(EnumType.STRING)
    public roofMaterial roofmaterial;

    @DecimalMin(value = "0.01", message = "Minimalna cena musi wynosi 0.01 zł/moduł")
    @DecimalMax(value = "1000.00", message = "Maksymalna cena musi wynosi 1000.0 zł/moduł")
    private double price;


    public enum roofType {
        DACH_PLASKI, DACH_SKOSNY, GRUNT
    }

    public enum roofMaterial {
        BLACHODACHOWKA, BLACHOTRAPEZ, PLYTA_WARSTWOWA, PAPA, GONT, GRUNT, DACHÓWKA_CERAMICZNA, DACHÓWKA_KARPIÓWKA
    }


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "construction",orphanRemoval = true)
    private List<Instalation> list;


    public Construction(String manufacturer, String model, roofType rooftype, roofMaterial roofmaterial, double price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.rooftype = rooftype;
        this.roofmaterial = roofmaterial;
        this.price = price;
    }
}
