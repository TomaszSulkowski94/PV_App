package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Entities.Results;
import org.springframework.stereotype.Service;

@Service
public class ResultsService {

    String[] month = {"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"};
    double[] electircfactors = {0.03, 0.04, 0.07, 0.09, 0.12, 0.13, 0.12, 0.13, 0.11, 0.09, 0.05, 0.03};

    public void calculate(Instalation instalation) {
        Results results = new Results();
    }
    
}
