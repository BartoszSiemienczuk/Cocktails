package pl.com.siemienczuk.cocktails.model.drink;

import com.google.gson.annotations.JsonAdapter;

import java.net.URL;

import lombok.Data;

@Data
public class Drink {
    protected Long id;
    protected String name;
    protected URL photoUrl;
}
