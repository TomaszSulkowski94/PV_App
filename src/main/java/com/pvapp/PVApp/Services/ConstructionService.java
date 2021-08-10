package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Construction;
import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Repositories.DBRepositories.ConstructionDBRepo;
import com.pvapp.PVApp.Utils.Import.ExcelHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@Service
@Slf4j
public class ConstructionService {

    @Autowired
    ConstructionDBRepo constructionRepo;

    @Autowired
    InstalationService instalationService;

    public List<Construction> getAllConstructions() {
        log.info("Getting all constructions from DB --service");
        return new ArrayList<>(constructionRepo.printAll());
    }

    public List<Construction> getAllIdOrder() {
        log.info("Getting all constructions from DB --service");
        return new ArrayList<>(constructionRepo.printAllIdOrder());
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
        List<Instalation> instalations = instalationService.getAllByConstruction(construction);
        if (!instalations.isEmpty()) {
            for (Instalation instalation : instalations) {
                instalationService.update(instalation);
            }
        }
    }

    public Construction getConstructionByRoofTypeMaterial(String rooftype, String roofmaterial) {
        log.info("Getting construction from DB by roof type and roof material--service");
        return constructionRepo.getByRoofTypeMaterial(rooftype, roofmaterial);
    }

    public void saveFile(MultipartFile file) {
        try {
            List<Construction> constructions = ExcelHelper.excelToConstruction(file.getInputStream());
            constructionRepo.saveFromFilem(constructions);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

}

