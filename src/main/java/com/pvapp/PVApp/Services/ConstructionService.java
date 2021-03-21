package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Construction;
import com.pvapp.PVApp.Repositories.DBRepositories.ConstructionDBRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class ConstructionService {

    @Autowired
    ConstructionDBRepo constructionRepo;

    public List<Construction> getAllConstructions() {
        return new ArrayList<Construction>(constructionRepo.printAll());
    }

    public void saveConstruction(Construction construction) {
        constructionRepo.create(construction);
    }

    public void delete(int id) {
        constructionRepo.delete(id);
    }

    public Construction getConstruction(int id) {
        return constructionRepo.printbyid(id);
    }

    public void update(Construction construction) {
    constructionRepo.update(construction);
    }
}
