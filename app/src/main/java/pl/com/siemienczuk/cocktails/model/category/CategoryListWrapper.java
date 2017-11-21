package pl.com.siemienczuk.cocktails.model.category;

import java.util.List;

public class CategoryListWrapper {
    protected List<Category> drinks;

    public List<Category> getCategories(){
        return this.drinks;
    }
}
