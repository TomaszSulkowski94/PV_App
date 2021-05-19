package com.pvapp.PVApp.Entities;

import lombok.*;
import org.hibernate.validator.constraints.Range;



import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "inverter")
public class Inverter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inverterid")
    private int id;

    @NotEmpty(message = "Podaj producenta falownika")
    @Size(min = 3, max = 20, message = "Pole musi przyjmować wartości z zakresu 3 do 20 znaków")
    private String manufacturer;

    @NotEmpty(message = "Podaj model falownika")
    @Size(min = 3, max = 20, message = "Pole musi przyjmować wartości z zakresu 3 do 20 znaków")
    private String model;

    @Enumerated(EnumType.ORDINAL)
    private InverterType type;


    @Range(min = 1, max = 1000000, message = "Moc falownika po stronie DC powinna przyjmować wartości z przedziału 1 a 1000000 [W]")
    private int dcpower;

    @Range(min = 1, max = 1000000, message = "Moc falownika po stronie AC powinna przyjmować wartości z przedziału 1 a 1000000 [W]")
    private int acpower;

    @Range(min = 1, max = 10, message = "Ilość łańcuchów MPPT może przyjmować wartości pomiędzy 1 a 10")
    private int mppt;

    @DecimalMin(value = "0.01", message = "Minimalna wartość prądu zwarcia  powinna być większa niż 0.01 [A]")
    @DecimalMax(value = "100.00", message = "Maksymalnwa wartość prądu zwarcia powinna nyć nie większa niż 100.00 [A]")
    private double maxcurrentzwarcia;

    @DecimalMin(value = "0.01", message = "Minimalna wartość prądu zwarcia w warunkach roboczych powinna być większa niż 0.01 [A]")
    @DecimalMax(value = "100.00", message = "Maksymalnwa wartość prądu zwarcia w warunkach roboczych powinna nyć nie większa niż 100.00 [A]")
    private double maxcurrentrob;

    @Range(min = 1, max = 1000, message = "Dolny zakres napięcia falownika powinien wynosić pomiędzy 1 a 1000 [V]")
    private int dolnyzakresnapiecia;
    @Range(min = 100, max = 100000, message = "Górny zakres napięcia falownika powinien wynosić pomiędzy 100 a 100000 [V]")
    private int gornyzakresnapiecia;
    @Range(min = 100, max = 100000, message = "Maksymalne napięcia falownika powininno wynosić pomiędzy 100 a 100000 [V]")
    private int maksymalnenapiecie;


    @DecimalMin(value = "0.01", message = "Minimalna cena musi wynosi 0.01")
    private double price;

    public enum InverterType {
        JEDNOFAZOWY, TROJFAZOWY
    }


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inverter")
    @ToString.Exclude
    private List<Instalation> instalationList;


}
