package pl.com.siemienczuk.cocktails.model.drink;

import java.net.URL;

import lombok.Data;

@Data
public class Drink {
    protected Long id;
    protected String name;
    protected URL photoUrl;
}
