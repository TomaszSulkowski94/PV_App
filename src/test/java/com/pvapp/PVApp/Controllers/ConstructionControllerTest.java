package com.pvapp.PVApp.Controllers;

import com.pvapp.PVApp.Entities.Construction;
import com.pvapp.PVApp.Services.ConstructionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@WebMvcTest(ConstructionControllerTest.class)
public class ConstructionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConstructionService constructionService;

    @Test
    public void create_Construction_whenPostMethod() {
//        Construction construction = new Construction();
//        given(constructionService.saveConstruction(construction)).willReturn(construction);
//    mockMvc.perform(post("/"))
    }

    @Test
    public void getconstructions() {
    }

    @Test
    public void createConstruction() {
    }

    @Test
    public void saveConstruction() {
    }

    @Test
    public void deleteConstruction() {
    }

    @Test
    public void updateConstruction() {
    }

    @Test
    public void testUpdateConstruction() {
    }

    @Test
    public void exportToPdf() {
    }

    @Test
    public void uploadFileForm() {
    }

    @Test
    public void uploadFile() {
    }

    @Test
    public void exportToExcel() {
    }
}