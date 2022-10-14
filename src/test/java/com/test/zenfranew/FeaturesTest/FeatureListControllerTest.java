package com.test.zenfranew.FeaturesTest;

import com.ZenPack.controller.FeatureListController;
import com.ZenPack.model.FeaturedList;
import com.ZenPack.repository.FeaturedListRepository;
import com.ZenPack.service.Impl.FeaturedListServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
//@SpringBootConfiguration
public class FeatureListControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeaturedListRepository repository;

    @MockBean
    private FeaturedListServiceImpl service;

    @InjectMocks
    private FeatureListController featureListController;

    @Autowired
    private ObjectMapper objectMapper;

    private FeaturedList list;
    private FeaturedList list1;

    @BeforeEach
    void init(){
        FeaturedList list =new FeaturedList();
        list.setId(1);
        list.setText("Project Management");
        list.setFeatureId("2283433701");
        list.setIcon("fa fa tasks");
        list.setFeatureUrl("#");

        FeaturedList list1 =new FeaturedList();
        list1.setId(1);
        list1.setText("Project Summary");
        list1.setFeatureId("2283433701");
        list1.setIcon("fa fa tasks");
        list1.setFeatureUrl("#");
    }

    @Test
    void shouldCreateFeatures() throws Exception {
        FeaturedList list = new FeaturedList();
        list.setId(3);
        list.setText("Project Management");
        list.setFeatureId("2283433701");
        list.setIcon("fa fa tasks");
        list.setFeatureUrl("#");

        when(service.save(any(FeaturedList.class))).thenReturn(list);

        this.mockMvc.perform(post("api/v1/create_features")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(objectMapper.writeValueAsString(list)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text", CoreMatchers.is(list.getText())))
                .andExpect(jsonPath("$.featureId", CoreMatchers.is(list.getFeatureId())))
                .andExpect(jsonPath("$.icon", CoreMatchers.is(list.getIcon())))
                .andExpect(jsonPath("$.featureUrl", CoreMatchers.is(list.getFeatureUrl())));
    }

    @Test
    void shouldFetchAllList() throws Exception {
        List<FeaturedList> featuredListList = new ArrayList<>();
        featuredListList.add(list);
        featuredListList.add(list1);

        when(service.findAllList()).thenReturn(featuredListList);

        this.mockMvc.perform(get("/api/v1/get_features"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",CoreMatchers.is(featuredListList.size())));
    }
}
