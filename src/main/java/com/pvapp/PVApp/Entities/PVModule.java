package com.pvapp.PVApp.Entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pvmodules")
public class PVModule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pvmoduleid")
    private int id;

    @NotEmpty(message = "Podaj producenta modułu PV")
    @Size(min = 3, max = 20, message = "Pole musi przyjmować wartości z zakresu 3 do 20 znaków")
    private String manufacturer;

    @NotEmpty(message = "Podaj model modułu PV")
    @Size(min = 3, max = 20, message = "Pole musi przyjmować wartości z zakresu 3 do 20 znaków")
    private String model;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private moduleType type;


    @Range(min = 1, max = 9999, message = "Moc pojedynczego modułu powinna być w zakresie 1-9999 [Wp]")
    private int power;

    @Column(name = "currentstc")
    @DecimalMin(value = "0.01", message = "Minimalna wartość prądu w warunkach STC  powinna być większa niż 0.01 [A]")
    @DecimalMax(value = "100.00", message = "Maksymalnwa wartość prądu w warunkach STC powinna być nie większa niż 100.00 [A]")
    private double currentSTC;

    @Column(name = "maxcurrentstc")
    @DecimalMin(value = "0.01", message = "Minimalna wartość maksymalnego prądu zwarcia w warunkach STC  powinna być większa niż 0.01 [A]")
    @DecimalMax(value = "100.00", message = "Maksymalnwa wartość maksymalnego prądu zwarcia w warunkach STC nie większa niż 100.00 [A]")
    private double maxCurrentSTC;

    @Column(name = "voltagestc")
    @DecimalMin(value = "0.01", message = "Minimalne napięcie w warunkach STC  powinna być większa niż 0.01 [V]")
    @DecimalMax(value = "100.00", message = "Maksymalne napięcie w warunkach STC powinna być nie większa niż 100.00 [V]")
    private double voltageSTC;

    @Column(name = "voltagempp")
    @DecimalMin(value = "0.01", message = "Minimalne napięcie w warunkach MPP  powinno być większa niż 0.01 [V]")
    @DecimalMax(value = "100.00", message = "Maksymalne napięcie w warunkach MPP powinno być nie większa niż 100.00 [V]")
    private double voltageMPP;

    @Column(name = "temperaturelost")
    @DecimalMin(value = "0.0001", message = "Minimalna strata temperaturowa  powinna być większa niż 0.01 [%/C]")
    @DecimalMax(value = "1.0000", message = "Maksymalnwa strata temperaturowa powinna nyć nie większa niż 1.00 [%/C]")
    private double temperatureLost;

    @Column(name = "efficency")
    @DecimalMin(value = "0.0001", message = "Minimalna sprawność  powinna być większa niż 0.0001 [-]")
    @DecimalMax(value = "1.0000", message = "Maksymalnwa sprawność  powinna nyć nie większa niż 1.0000 [-]")
    private double efficency;

    @Column(name = "price")
    @DecimalMin(value = "0.01", message = "Minimalna wartość prądu zwarcia  powinna być większa niż 0.01 [€/Wp]")
    @DecimalMax(value = "100.00", message = "Maksymalnwa wartość prądu zwarcia powinna nyć nie większa niż 100.00 [€/Wp]")
    private double price;

    public enum moduleType {
        POLIKRYSTALICZNY, MONOKRYSTALICZNY, BIFACIAL, GLASSGLASS

    }

    @OneToMany(cascade = {CascadeType.MERGE}, mappedBy = "pvModule")
    List<Instalation> instalationList;

    public PVModule(String manufacturer, String model, moduleType type, int power, double currentSTC, double maxCurrentSTC, double voltageSTC, double voltageMPP, double temperatureLost, double efficency, double price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
        this.power = power;
        this.currentSTC = currentSTC;
        this.maxCurrentSTC = maxCurrentSTC;
        this.voltageSTC = voltageSTC;
        this.voltageMPP = voltageMPP;
        this.temperatureLost = temperatureLost;
        this.efficency = efficency;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PVModule pvModule = (PVModule) o;

        return Objects.equals(id, pvModule.id);
    }

    @Override
    public int hashCode() {
        return 456110359;
    }
}
