package com.pvapp.PVApp.Config;


import com.pvapp.PVApp.Repositories.DBRepositories.ConstructionDBRepo;
import com.pvapp.PVApp.Repositories.DBRepositories.InverterDBRepo;
import com.pvapp.PVApp.Repositories.DBRepositories.PVModuleDBRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@Configuration
//public class ProfileConfig {
//
//
//    @Bean(name = "ConstructionDBRepo")
//    public ConstructionDBRepo constructionRepoDB() {
//        return new ConstructionDBRepo();
//    }
//
//    @Bean(name = "PVModuleDBRepo")
//    public PVModuleDBRepo pvModuleRepoDB() {
//        return new PVModuleDBRepo();
//    }
//
//    @Bean(name = "InverterDBRepo")
//    public InverterDBRepo inverterRepoDB() {
//        return new InverterDBRepo();
//    }
//}
