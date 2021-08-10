package com.pvapp.PVApp.Entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "price")
public class Price implements Serializable {

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
    private double discountedinstalationpricegross;

    @NotNull
    @Range(min = 0, max = 40, message = "Wartość rabatu powinna znajdywać się w przedziale 0-40%")
    private int discount;

    @OneToOne(mappedBy = "price")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Price price = (Price) o;

        return Objects.equals(id, price.id);
    }

    @Override
    public int hashCode() {
        return 1434193993;
    }
}
