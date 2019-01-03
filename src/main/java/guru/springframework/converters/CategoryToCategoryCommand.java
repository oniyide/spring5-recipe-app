package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> { // category is source, categoryCommand

    @Override
    @Nullable
    @Synchronized
    public CategoryCommand convert(Category category) {
        if(category == null)
            return null;

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setDescription(category.getDescription());

        return categoryCommand;
    }
}
