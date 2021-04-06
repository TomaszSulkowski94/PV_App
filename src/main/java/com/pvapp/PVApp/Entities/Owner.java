package com.pvapp.PVApp.Entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ownerid")
    private int id;


    private String firstname;
    private String secondname;
    private int phonenumber;
    private String additionalphonenumber;
    private String mailadress;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Address> locations;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<QuestionForm> questionForms;

    public Owner(String firstname, String secondname, int phonenumber, String mailadress) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.phonenumber = phonenumber;
        this.additionalphonenumber = null;
        this.mailadress = mailadress;
    }

    //    @OneToMany(mappedBy = "owner")
    //    private List<Instalation> instalationList;


}
