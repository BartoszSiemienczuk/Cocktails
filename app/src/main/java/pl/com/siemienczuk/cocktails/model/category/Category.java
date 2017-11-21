package pl.com.siemienczuk.cocktails.model.category;

import lombok.Data;

@Data
public class Category {
    protected String strCategory;

    public String getName(){
        return this.strCategory;
    }

}
