package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Repositories.DBRepositories.InverterDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InverterService {

    @Autowired
    InverterDBRepo inverterRepo;
}
