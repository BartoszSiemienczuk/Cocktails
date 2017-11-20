package pl.com.siemienczuk.cocktails.model.drink;

import java.util.List;

import lombok.Data;

@Data
public class DrinkListWrapper {
    protected List<Drink> drinks;
}
