package com.pvapp.PVApp.Entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "questionform")
public class QuestionForm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionformid")
    private int id;

    @NotNull
    @Range(min = 50, max = 2000, message = "Wartość rabatu powinna znajdywać się w przedziale 0-40%")
    private int bill;
    @Enumerated(EnumType.STRING)
    private RoofType rooftype;

    private int roofslope;
    private int roofposition;

    @Enumerated(EnumType.STRING)
    private RoofMaterial roofmaterial;


    public enum RoofType {
        DACH_PLASKI, DACH_SKOSNY, GRUNT
    }

    public enum RoofMaterial {
        BLACHODACHOWKA, BLACHOTRAPEZ, PLYTA_WARSTWOWA, PAPA, GONT, GRUNT, DACHÓWKA_CERAMICZNA, DACHÓWKA_KARPIÓWKA, SPECIFIED_BY_CONSTRUCTION
    }


    @OneToOne(mappedBy = "questionForm", cascade = CascadeType.ALL)
    private Instalation instalation;

    public QuestionForm(int bill, RoofType rooftype, int roofslope, int roofposition, RoofMaterial roofmaterial) {
        this.bill = bill;
        this.rooftype = rooftype;
        this.roofslope = roofslope;
        this.roofposition = roofposition;
        this.roofmaterial = roofmaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        QuestionForm that = (QuestionForm) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1470560699;
    }
}
