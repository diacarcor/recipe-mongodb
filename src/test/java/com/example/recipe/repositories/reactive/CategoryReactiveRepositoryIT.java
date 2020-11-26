package com.example.recipe.repositories.reactive;

import com.example.recipe.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class CategoryReactiveRepositoryIT {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    Category category;
    @BeforeEach
    void setUp() {

        category=new Category();
        category.setDescription("Italian");

        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    void saveCategory(){
        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();

        assertEquals(1L,count);
    }

    @Test
    void findByDescription(){
        categoryReactiveRepository.save(category).block();

        Category categoryFetched = categoryReactiveRepository.findByDescription("Italian").block();

        assertEquals("Italian",categoryFetched.getDescription());
    }
}