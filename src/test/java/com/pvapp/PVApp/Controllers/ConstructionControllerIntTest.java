//package com.pvapp.PVApp.Controllers;
//
//
//import com.pvapp.PVApp.Entities.Construction;
//import com.pvapp.PVApp.Services.ConstructionService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//
//import static org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(ConstructionController.class)
//public class ConstructionControllerIntTest {
//
//    private static MediaType CONTENT_TYPE =
//            new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private ConstructionService constructionService;
//
//
//    @Test
//    public void getConstructionList() throws Exception {
//
//    }
//
//    @Test
//    public void getconstructions() throws Exception {
//        mvc.perform(
//                        MockMvcRequestBuilders
//                                .get("/construction/list")
//                                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void whenAddNewConstruction_ReturnCreatedStatus() throws Exception {
//        Construction nc = new Construction("XYZ", "Model A", Construction.roofType.DACH_SKOSNY, Construction.roofMaterial.BLACHODACHOWKA, 150, 0);
//
////        UserDoc user4 =
////                new UserDoc(USER_FOUR_KEY, USER_FOUR_ID, "Alfredo", Arrays.asList("alfredo"), 40, "alfredo@yopmail.com");
////        mockMvc.perform(post("/users").contentType(CONTENT_TYPE).content(json(user4))).andExpect(status().isCreated());
//
//
//        mvc.perform(post("/construction/saveConstruction").contentType(CONTENT_TYPE).content(json(nc))).andExpect(status().isCreated());
//    }
//}