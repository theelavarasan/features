package com.test.zenfranew.FeaturesTest;

import com.ZenPack.model.FeaturedList;
import com.ZenPack.repository.FeaturedListRepository;
import com.ZenPack.service.Impl.FeaturedListServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FeatureListServiceTest {

    @Autowired
    private FeaturedListRepository repository;

    @InjectMocks
    private FeaturedListServiceImpl service;

    private FeaturedList list;

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
    @DisplayName("Junit Test for Save List")
    void saveList(){
        given(repository.findById(list.getId()))
                .willReturn(Optional.empty());

        given(repository.save(list)).willReturn(list);

        System.out.println(repository);
        System.out.println(service);

        FeaturedList savedList = service.save(list);

        System.out.println(savedList);
        assertThat(savedList).isNotNull();
    }
}
