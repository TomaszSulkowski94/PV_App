package com.pvapp.PVApp.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "results")
public class Results {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resultid")
    private int id;

    private double[] production;
    private int strings;
    private int maxmodulestring;
    private double current;
    private double voltage;
    private Connectiontype connectiontype;

    private enum Connectiontype{
        SZEREGOWE, ROWNOLEGLE;
    }
}
