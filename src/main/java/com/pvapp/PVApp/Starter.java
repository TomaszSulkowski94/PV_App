package com.pvapp.PVApp;

import com.pvapp.PVApp.Repositories.DBRepositories.ConstructionDBRepo;
import com.pvapp.PVApp.Repositories.DBRepositories.InverterDBRepo;
import com.pvapp.PVApp.Repositories.DBRepositories.PVModuleDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Scope("singleton")
public class Starter implements CommandLineRunner {

//    @Autowired
//    PVModuleDBRepo pvModuleRepo;
//
//    @Autowired
//    InverterDBRepo inverterRepo;
//
//    @Autowired
//    ConstructionDBRepo constructionRepo;

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
    }
}
