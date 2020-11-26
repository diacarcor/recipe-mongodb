package com.example.recipe.repositories.reactive;

import com.example.recipe.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class RecipeReactiveRepositoryTest {

    @Autowired
    RecipeReactiveRepository reactiveRepository;

    Recipe recipe;

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
        recipe.setDescription("My Recipe");

        reactiveRepository.deleteAll().block();
    }

    @Test
    void saveRecipe(){
        reactiveRepository.save(recipe).block();

        Long count = reactiveRepository.count().block();

        assertEquals(1L, count);
    }

}