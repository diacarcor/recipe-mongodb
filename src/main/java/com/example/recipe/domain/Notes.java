package com.example.recipe.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
@Setter
public class Notes {

    @Id
    private String id;

    private Recipe recipe;

    private String recipeNotes;
}
