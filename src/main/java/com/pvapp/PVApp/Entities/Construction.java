package com.pvapp.PVApp.Entities;

import lombok.*;
import org.hibernate.validator.constraints.Range;


import javax.persistence.*;
import javax.validation.constraints.*;
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

    @NotEmpty(message = "Podaj model konstrukcji")
    @Size(min = 3, max = 20, message = "Pole musi przyjmować wartości z zakresu 3 do 20 znaków")
    private String model;

    @Enumerated(EnumType.STRING)
    public roofType rooftype;

    @Enumerated(EnumType.STRING)
    public roofMaterial roofmaterial;

    @DecimalMin(value = "0.01", message = "Minimalna cena musi wynosi 0.01 zł/moduł")
    @DecimalMax(value = "1000.00", message = "Maksymalna cena musi wynosi 1000.0 zł/moduł")
    private double price;

    @Range(min = 0, max = 70, message = "Kąt nachylenia dachu powinien być pomiedzy 0-70")
    private int roofslope;


    public enum roofType {
        DACH_PLASKI, DACH_SKOSNY, GRUNT;
    }

    public enum roofMaterial {
        BLACHODACHOWKA, BLACHOTRAPEZ, PLYTA_WARSTWOWA, PAPA, GONT, GRUNT, DACHÓWKA_CERAMICZNA, DACHÓWKA_KARPIÓWKA
    }


    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "construction")
    private List<Instalation> list;


    public Construction(String manufacturer, String model, roofType rooftype, roofMaterial roofmaterial, double price, int roofslope) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.rooftype = rooftype;
        this.roofmaterial = roofmaterial;
        this.price = price;
        this.roofslope=roofslope;
    }

    public Construction(String manufacturer, String model, roofType rooftype, roofMaterial roofmaterial, double price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.rooftype = rooftype;
        this.roofmaterial = roofmaterial;
        this.price = price;
    }
}
