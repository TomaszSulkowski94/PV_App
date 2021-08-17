package com.pvapp.PVApp.Entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "technicalresults")
public class TechnicalResults implements Serializable {

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

    @OneToOne(mappedBy = "technicalResults")
    private Instalation instalation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TechnicalResults that = (TechnicalResults) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1093799877;
    }

    public TechnicalResults(double vocmax, double vmppmax, double vmppmin, double iscmax, double imppmax, int nmax, int nmin, int nmaxparallel) {
        this.vocmax = vocmax;
        this.vmppmax = vmppmax;
        this.vmppmin = vmppmin;
        this.iscmax = iscmax;
        this.imppmax = imppmax;
        this.nmax = nmax;
        this.nmin = nmin;
        this.nmaxparallel = nmaxparallel;
    }
}
