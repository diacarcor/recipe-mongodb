package com.example.recipe.repositories.reactive;

import com.example.recipe.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeReactiveRepository extends ReactiveMongoRepository <Recipe,String> {
}
