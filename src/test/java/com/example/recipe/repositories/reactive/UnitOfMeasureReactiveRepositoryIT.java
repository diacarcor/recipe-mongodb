package com.example.recipe.repositories.reactive;

import com.example.recipe.domain.UnitOfMeasure;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class UnitOfMeasureReactiveRepositoryIT {

    @Autowired
    UnitOfMeasureReactiveRepository reactiveRepository;

    UnitOfMeasure smallCup;


    @BeforeEach
    void setUp() {
        smallCup = new UnitOfMeasure();
        smallCup.setUom("Small Cup");
        reactiveRepository.deleteAll().block();
    }

    @Test
    void saveUom() {

        reactiveRepository.save(smallCup).block();

        Long count = reactiveRepository.count().block();

        assertEquals(1L,count);
    }

    @Test
    void findByUom (){

        reactiveRepository.save(smallCup).block();

        UnitOfMeasure uom = reactiveRepository.findByUom("Small Cup").block();

        assertEquals("Small Cup",uom.getUom());

    }




}