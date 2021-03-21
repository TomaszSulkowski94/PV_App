package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Repositories.DBRepositories.InstalationDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstalationService {


    @Autowired
    InstalationDBRepo instalationDBRepo;

    public List<Instalation> getAll(){
        return new ArrayList<Instalation>(instalationDBRepo.printAll());
    }
}
