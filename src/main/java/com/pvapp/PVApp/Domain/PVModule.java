package com.pvapp.PVApp.Domain;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="pvmodules")
public class PVModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name="manufacturer")
    private String manufacturer;
    @Column(name="model")
    private String model;
    @Column(name="type")
    private moduleType type;
    @Column(name="power")
    private int power;
    @Column(name="currentstc")
    private double currentSTC;
    @Column(name="maxcurrentstc")
    private double maxCurrentSTC;
    @Column(name="voltagestc")
    private double voltageSTC;
    @Column(name="voltagempp")
    private double voltageMPP;
    @Column(name="temperaturelost")
    private double temperatureLost;
    @Column(name="efficency")
    private double efficency;
    @Column(name="price")
    private double price;

    public enum moduleType {
        MONOKRYSTALICZNY, POLIKRYSTALICZNY, BIFACIAL, GLASSGLASS;

    }


    public PVModule(String manufacturer, String model, moduleType type, int power, double currentSTC, double maxCurrentSTC, double voltageSTC, double voltageMPP, double temperatureLost, double efficency, double price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
        this.power = power;
        this.currentSTC = currentSTC;
        this.maxCurrentSTC = maxCurrentSTC;
        this.voltageSTC = voltageSTC;
        this.voltageMPP = voltageMPP;
        this.temperatureLost = temperatureLost;
        this.efficency = efficency;
        this.price = price;
    }
}
