package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Repositories.DBRepositories.ConstructionDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstructionService {

    @Autowired
    ConstructionDBRepo constructionRepo;
}
