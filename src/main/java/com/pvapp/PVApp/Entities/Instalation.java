package com.pvapp.PVApp.Entities;


import lombok.*;


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
    @Column(name = "instalationid")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pvmoduleid")
    private PVModule pvModule;
    private int numberofpvmodule;

    @ManyToOne
    @JoinColumn(name = "inverterid")
    private Inverter inverter;
    private int numberofinverters;

    @ManyToOne
    @JoinColumn(name = "constructionid")
    private Construction construction;
    private double power;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "priceid")
    private Price price;

    private int roofposition;
    private int instalationangle;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "questionformid")
    private QuestionForm questionForm;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "productionid")
    private Production production;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "technicalresultsid")
    private TechnicalResults technicalResults;

    public Instalation(PVModule pvModule, int numberofpvmodule, Inverter inverter, int numberofinverters, Construction construction) {
        this.pvModule = pvModule;
        this.numberofpvmodule = numberofpvmodule;
        this.inverter = inverter;
        this.numberofinverters = numberofinverters;
        this.construction = construction;
    }

}
