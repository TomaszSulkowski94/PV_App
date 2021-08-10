package com.pvapp.PVApp.Services;

import com.pvapp.PVApp.Entities.Instalation;
import com.pvapp.PVApp.Entities.Inverter;
import com.pvapp.PVApp.Repositories.DBRepositories.InverterDBRepo;
import com.pvapp.PVApp.Utils.Import.ExcelHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class InverterService {

    @Autowired
    InverterDBRepo inverterRepo;

    @Autowired
    InstalationService instalationService;

    public List<Inverter> getAllInverters() {
        log.info("Getting all inverters --service");
        return new ArrayList<>(inverterRepo.printAll());
    }

    public void saveInverter(Inverter inverter) {
        log.info("Saving inverter --service");
        inverterRepo.create(inverter);
    }

    public void deleteInverter(int id) {
        log.info("Deleting inverter --service");
        inverterRepo.delete(id);
    }

    public Inverter getInverter(int id) {
        log.info("Getting inverter by id --service");
        return inverterRepo.printbyid(id);
    }

    public void update(Inverter inverter) {
        log.info("Updating inverter --service");
        inverterRepo.update(inverter);
        List<Instalation> instalations = instalationService.getAllByInverter(inverter);
        if (!instalations.isEmpty()) {
            for (Instalation instalation : instalations) {
                instalationService.update(instalation);
            }
        }
    }

    public void saveFile(MultipartFile file) {
        try {
            List<Inverter> inverters = ExcelHelper.excelToInverter(file.getInputStream());
            inverterRepo.saveAll(inverters);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
