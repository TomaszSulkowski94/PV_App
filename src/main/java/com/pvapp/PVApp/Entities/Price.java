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
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priceid")
    private int id;
    private double moduleprice;
    private double inverterprice;
    private double constructionprice;
    private double instalationpricenet;
    private double taxvalue;
    private double instalationpricegross;
    private int discount;

    @OneToOne(mappedBy = "price", cascade = CascadeType.ALL)
    private Instalation instalation;

    public Price(double moduleprice, double inverterprice, double constructionprice, double instalationpricenet, double taxvalue, double instalationpricegross) {
        this.moduleprice = moduleprice;
        this.inverterprice = inverterprice;
        this.constructionprice = constructionprice;
        this.instalationpricenet = instalationpricenet;
        this.taxvalue = taxvalue;
        this.instalationpricegross = instalationpricegross;
        this.discount = 0;
    }
}
