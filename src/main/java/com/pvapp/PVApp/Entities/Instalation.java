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

//    @ManyToOne
//    @JoinColumn(name="owner_id")
//    private Owner owner;

    @ManyToOne
    @JoinColumn(name="pvmodule_id")
    private PVModule pvModule;
    private int numberofpvmodule;

    @ManyToOne
    @JoinColumn(name="inverter_id")
    private Inverter inverter;

    @ManyToOne
    @JoinColumn(name = "construction_id")
    private Construction construction;

    private double power;

    public Instalation(PVModule pvModule, int numberofpvmodule, Inverter inverter, Construction construction, double power) {
        this.pvModule = pvModule;
        this.numberofpvmodule = numberofpvmodule;
        this.inverter = inverter;
        this.construction = construction;
        this.power = power;
    }
}
