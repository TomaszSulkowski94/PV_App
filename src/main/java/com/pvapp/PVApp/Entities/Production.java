package com.pvapp.PVApp.Entities;


import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "production")
public class Production {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productionid")
    private int id;

    private double january;
    private double february;
    private double march;
    private double april;
    private double may;
    private double june;
    private double july;
    private double august;
    private double september;
    private double october;
    private double november;
    private double december;
    private double summary;

    @OneToOne(mappedBy = "production", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Instalation instalation;
}
