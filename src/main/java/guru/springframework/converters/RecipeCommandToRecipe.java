package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;
    private final CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Override
    @Nullable
    @Synchronized
    public Recipe convert(RecipeCommand recipeCommand) {

        if (recipeCommand == null)
            return null;

        Recipe recipe = new Recipe();

        recipe.setId(recipeCommand.getId());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setNotes(notesConverter.convert(recipeCommand.getNotes()));

        // Set Ingredients
        if(recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
            recipeCommand.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        // Set Categories
        if(recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0){
            recipeCommand.getCategories()
                    .forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }


        return recipe;
    }
}
