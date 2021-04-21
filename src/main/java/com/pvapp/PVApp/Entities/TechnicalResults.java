package com.pvapp.PVApp.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "technicalresults")
public class TechnicalResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technicalresultsid")
    private int id;

    private double vocmax;
    private double vmppmax;
    private double vmppmin;
    private double iscmax;
    private double imppmax;
    private int nmax;
    private int nmin;
    private int nmaxparallel;

    @OneToOne(mappedBy = "technicalResults", cascade = CascadeType.ALL)
    private Instalation instalation;
}
