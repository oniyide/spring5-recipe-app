package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> { //<source , target>

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) { // categoryCommand is the source
        if(categoryCommand == null)
            return null;

        final Category category = new Category();
        category.setId(categoryCommand.getId());
        category.setDescription(categoryCommand.getDescription());

        return  category;
    }
}
