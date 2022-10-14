package com.test.zenfranew.FeaturesTest;

import com.ZenPack.model.FeaturedList;
import com.ZenPack.repository.FeaturedListRepository;
import org.assertj.core.api.AbstractBooleanAssert;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes ={FeaturedListRepository.class} )
public class FeaturedRepositoryTest {
    @Autowired
    private FeaturedListRepository repository;

    @Autowired
    private MockMvc mockMvc;

    private FeaturedList list;
    private FeaturedList list1;

    @Test
    void save(){
        FeaturedList list =new FeaturedList();
        list.setId(1);
        list.setText("Project Management");
        list.setFeatureId("2283433701");
        list.setIcon("fa fa tasks");
        list.setFeatureUrl("#");
        FeaturedList newList =repository.save(list);
        assertNotNull(newList);
        assertThat(newList.getId()).isNotEqualTo(null);

    }

}
