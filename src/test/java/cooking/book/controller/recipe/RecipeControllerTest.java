package cooking.book.controller.recipe;

import cooking.book.model.recipe.Ingredient;
import cooking.book.model.recipe.Recipe;
import cooking.book.model.recipe.RecipeCategory;
import cooking.book.repository.recipe.RecipeRepository;
import cooking.book.service.recipe.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class RecipeControllerTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController instance;

    @Spy
    List<Recipe> recipes = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        recipes =  getRecipeList();
    }
 
    @Test
    public void testGetAll() {
        when(recipeRepository.findAll()).thenReturn(recipes);
        instance.getAll();

        verify(recipeRepository, atLeastOnce()).findAll();
    }

    @Test
    public void testGetAllByNoOfAccesses() {
        when(recipeRepository.findAllByOrderByNoOfTimesAccessedDesc()).thenReturn(recipes);
        instance.getAllByNoOfAccesses();

        verify(recipeRepository, atLeastOnce()).findAllByOrderByNoOfTimesAccessedDesc();
    }



    private List<Recipe> getRecipeList() {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(0);
        recipe.setRecipeName("testName");
        recipe.setRecipeCategory(RecipeCategory.DESSERT);
        recipe.setInstructions("testInstructions");
        recipe.setSuggestions("testSuggestions");
        recipe.setLastAccessed(new Date());
        recipe.setNoOfTimesAccessed(0);
        recipe.getIngredientsList().add(new Ingredient("testIngredientName", "testIngredientQuantity"));

        recipes.add(recipe);

        return recipes;
    }
}
