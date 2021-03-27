package com.pvapp.PVApp.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "instalation")
public class Instalation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="pvmoduleid")
    private PVModule pvModule;
    private int numberofpvmodule;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="inverterid")
    private Inverter inverter;

    private int numberofinverters;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "constructionid")
    private Construction construction;

    private double power;

    //    @ManyToOne
//    @JoinColumn(name="owner_id")
//    private Owner owner;

    public Instalation(PVModule pvModule, int numberofpvmodule, Inverter inverter, int numberofinverters, Construction construction) {
        this.pvModule = pvModule;
        this.numberofpvmodule = numberofpvmodule;
        this.inverter = inverter;
        this.numberofinverters = numberofinverters;
        this.construction = construction;
        this.power = 0;
    }

}
