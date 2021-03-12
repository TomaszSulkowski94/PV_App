package com.pvapp.PVApp.Domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="inverter")
public class Inverter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String manufacturer;
    private String model;
    private type type;
    private int dcpower;
    private int acpower;
    private int mppt;
    private int maxcurrentzwarcia;
    private int maxcurrentrob;
    private int dolnyzakresnapiecia;
    private int gornyzakresnapiecia;
    private int maksymalnenapiecie;

    public enum type {
        JEDNOFAZOWY, TROJFAZOWY
    }
}
