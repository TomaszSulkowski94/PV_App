package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Domain.Inverter;
import com.pvapp.PVApp.Repositories.DBRepositories.InverterDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InverterService {

    @Autowired
    InverterDBRepo inverterRepo;


    public List<Inverter> getAllInverters() {
        return new ArrayList<Inverter>(inverterRepo.printAll());
    }

    public void saveInverter(Inverter inverter) {
        inverterRepo.create(inverter);
    }

    public void deleteInverter(int id) {
        inverterRepo.delete(id);
    }

    public Inverter getInverter(int id) {
        return inverterRepo.printbyid(id);
    }

    public void update(Inverter inverter) {
        inverterRepo.update(inverter);
    }
}
