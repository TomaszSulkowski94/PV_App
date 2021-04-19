package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Entities.TechnicalResults;
import com.pvapp.PVApp.Repositories.DBRepositories.TechnicalResultsDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TechnicalResultService {

    @Autowired
    TechnicalResultsDB technicalResultsDB;

    public void createTR(Instalation instalation) {
        log.info("Saving technical results --service");
       // technicalResultsDB.create();
    }

    public void deleteTR(int id){
        log.info("Deleting technical results --service");
        technicalResultsDB.delete(id);
    }

    public void updateTR(Instalation instalation){
        log.info("Deleting technical results --service");
      //  technicalResultsDB.update();
    }

    public TechnicalResults getTR(int id){
        log.info("Getting technical results --service");
        return technicalResultsDB.printbyid(id);
    }

    public List<TechnicalResults> getAllTR(){
        log.info("Getting all technical results --service");
        return new ArrayList<TechnicalResults>(technicalResultsDB.printAll());
    }
}
