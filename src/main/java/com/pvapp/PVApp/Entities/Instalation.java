package com.pvapp.PVApp.Entities;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "instalation")
public class Instalation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instalationid")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pvmoduleid")
    private PVModule pvModule;

    @NotNull
    @Positive
    private int numberofpvmodule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inverterid")
    private Inverter inverter;

    @NotNull
    @Positive
    private int numberofinverters;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "constructionid")
    private Construction construction;
    private double power;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "priceid")
    private Price price;

    private int roofposition;
    private int instalationangle;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "questionformid")
    private QuestionForm questionForm;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "productionid")
    private Production production;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "technicalresultsid")
    private TechnicalResults technicalResults;

    public Instalation(PVModule pvModule, int numberofpvmodule, Inverter inverter, int numberofinverters, Construction construction) {
        this.pvModule = pvModule;
        this.numberofpvmodule = numberofpvmodule;
        this.inverter = inverter;
        this.numberofinverters = numberofinverters;
        this.construction = construction;
    }

    public Instalation(PVModule pvModule, int numberofpvmodule, Inverter inverter, int numberofinverters, Construction construction, int instalationangle, int roofposition) {
        this.pvModule = pvModule;
        this.numberofpvmodule = numberofpvmodule;
        this.inverter = inverter;
        this.numberofinverters = numberofinverters;
        this.construction = construction;
        this.instalationangle=instalationangle;
        this.roofposition=roofposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Instalation that = (Instalation) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1088603704;
    }
}
