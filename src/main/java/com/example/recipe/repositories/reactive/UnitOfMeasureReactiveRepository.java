package com.example.recipe.repositories.reactive;

import com.example.recipe.domain.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;



public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository <UnitOfMeasure, String> {

    Mono<UnitOfMeasure> findByUom(String uom);

}
