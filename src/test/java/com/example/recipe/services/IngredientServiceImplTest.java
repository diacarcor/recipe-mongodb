package com.example.recipe.services;

import com.example.recipe.commands.IngredientCommand;
import com.example.recipe.commands.UnitOfMeasureCommand;
import com.example.recipe.converters.IngredientCommandToIngredient;
import com.example.recipe.converters.IngredientToIngredientCommand;
import com.example.recipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.example.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.example.recipe.domain.Ingredient;
import com.example.recipe.domain.Recipe;
import com.example.recipe.domain.UnitOfMeasure;
import com.example.recipe.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;


    @Mock
    RecipeService recipeService;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        this.unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    }


    @BeforeEach
    void setUp() {

       MockitoAnnotations.initMocks(this);

       ingredientService = new IngredientServiceImpl(recipeService, ingredientToIngredientCommand, ingredientCommandToIngredient, unitOfMeasureToUnitOfMeasureCommand, unitOfMeasureRepository) ;
    }

    @Test
    public void findByRecipeIdAndId() {
    }

    @Test
    public void findByRecipeIdAndIngredientId (){
        //given
        Recipe recipe = new Recipe();
        recipe.setId("1");

        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        Ingredient ingredient3 = new Ingredient();

        ingredient1.setId("1");
        ingredient2.setId("2");
        ingredient3.setId("3");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        when(recipeService.findById(anyString())).thenReturn(recipe);

        //when
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId("1", "3");

        //then
        assertEquals("3", ingredientCommand.getId());
        verify(recipeService, times(1)).findById(anyString());

    }

    @Test
    public void saveIngredientCommand(){
        //given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId("1");

        UnitOfMeasureCommand unitOfMeasureC = new UnitOfMeasureCommand();
        unitOfMeasureC.setId("1");
        Optional<UnitOfMeasure> optionalUom = Optional.of(unitOfMeasure);

        IngredientCommand command = new IngredientCommand();
        command.setId("3");
        command.setRecipeId("2");
        command.setDescription("Description");
        command.setAmount(new BigDecimal(1));
        command.setUom(unitOfMeasureC);



        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId("3");
        savedRecipe.getIngredients().iterator().next().setDescription("Description");
        savedRecipe.getIngredients().iterator().next().setAmount(new BigDecimal(1));
        savedRecipe.getIngredients().iterator().next().setUom(unitOfMeasure);

        when(recipeService.findById(anyString())).thenReturn(new Recipe());
        when(recipeService.save(any())).thenReturn(savedRecipe);
        when(unitOfMeasureRepository.findById(anyString())).thenReturn(optionalUom);

        //when
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        //then
        //assertEquals("3", savedCommand.getId());
        verify(recipeService, times(1)).findById(anyString());
        verify(recipeService, times(1)).save(any(Recipe.class));

    }

    @Test
    void  deleteById(){
        Recipe recipe = new Recipe();
        recipe.setId("1");

        recipe.addIngredient(new Ingredient());
        recipe.getIngredients().iterator().next().setId("2");

        when(recipeService.findById(anyString())).thenReturn(recipe);

        //given
        String idToDelete = "2";

        //when
        ingredientService.deleteById(anyString(),idToDelete);

        //then

        verify(recipeService,times(1)).save(any());
        verify(recipeService,times(1)).findById(anyString());


    }

}
