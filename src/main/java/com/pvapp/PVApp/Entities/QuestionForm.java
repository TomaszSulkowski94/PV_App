package com.pvapp.PVApp.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questionform")
public class QuestionForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionformid")
    private int id;

    private int bill;
    @Enumerated(EnumType.STRING)
    private RoofType rooftype;
    @Enumerated(EnumType.STRING)
    private RoofSlope roofslope;
    @Enumerated(EnumType.STRING)
    private RoofPosition roofposition;
    @Enumerated(EnumType.STRING)
    private RoofMaterial roofmaterial;


    public enum RoofType {
        DACH_PLASKI, DACH_SKOSNY, GRUNT
    }

    public enum RoofSlope {
        Dach_płaski, Lekko_nachylony, Srednio_nachylony, Mocno_nachylony
    }

    public enum RoofMaterial {
        BLACHODACHOWKA, BLACHOTRAPEZ, PLYTA_WARSTWOWA, PAPA, GONT, GRUNT, DACHÓWKA_CERAMICZNA, DACHÓWKA_KARPIÓWKA
    }


    public enum RoofPosition {
        Południe, Południowy_Wschód_10, Południowy_Wschód_20, Południowy_Wschód_30, Południowy_Wschód_40, Południowy_Wschód_50, Południowy_Wschód_60, Południowy_Wschód_70, Południowy_Wschód_80, Wschod, Południowy_Zachód_10, Południowy_Zachód_20, Południowy_Zachód_30, Południowy_Zachód_40, Południowy_Zachód_50, Południowy_Zachód_60, Południowy_Zachód_70, Południowy_Zachód_80, Zachód
    }

    @ManyToOne
    @JoinColumn(name = "ownerid")
    private Owner owner;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "questionForm")
    private Instalation instalation;

    public QuestionForm(int bill, RoofType rooftype, RoofSlope roofslope, RoofPosition roofposition, RoofMaterial roofmaterial) {
        this.bill = bill;
        this.rooftype = rooftype;
        this.roofslope = roofslope;
        this.roofposition = roofposition;
        this.roofmaterial = roofmaterial;
    }
}
