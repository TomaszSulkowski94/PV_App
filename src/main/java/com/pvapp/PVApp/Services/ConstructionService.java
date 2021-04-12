package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Construction;
import com.pvapp.PVApp.Entities.QuestionForm;
import com.pvapp.PVApp.Repositories.DBRepositories.ConstructionDBRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
@Slf4j
public class ConstructionService {

    @Autowired
    ConstructionDBRepo constructionRepo;

    public List<Construction> getAllConstructions() {
        log.info("Getting all constructions from DB --service");
        return new ArrayList<Construction>(constructionRepo.printAll());
    }

    public void saveConstruction(Construction construction) {
        log.info("Saving construction to DB --service");
        constructionRepo.create(construction);
    }

    public void delete(int id) {
        log.info("Deleting construction from DB --service");
        constructionRepo.delete(id);
    }

    public Construction getConstruction(int id) {
        log.info("Getting construction from DB --service");
        return constructionRepo.printbyid(id);
    }

    public void update(Construction construction) {
        log.info("Updating construction from DB --service");
        constructionRepo.update(construction);
    }


    public Construction getConstructionByRoofTypeMaterial(String rooftype, String roofmaterial) {
        log.info("Getting construction from DB by roof type and roof material--service");
        return constructionRepo.getByRoofTypeMaterial(rooftype, roofmaterial);
    }
}

