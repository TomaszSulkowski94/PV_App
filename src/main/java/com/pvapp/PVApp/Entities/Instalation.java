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

    @ManyToOne
    @JoinColumn(name = "pvmoduleid")
    @ToString.Exclude
    private PVModule pvModule;
    private int numberofpvmodule;

    @ManyToOne
    @JoinColumn(name = "inverterid")
    @ToString.Exclude
    private Inverter inverter;
    private int numberofinverters;
    @ManyToOne
    @JoinColumn(name = "constructionid")
    @ToString.Exclude
    private Construction construction;
    private double power;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "priceid")
    private Price price;

    private int roofposition;
    private int instalationangle;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "questionformid")
    private QuestionForm questionForm;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productionid")
    private Production production;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
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
