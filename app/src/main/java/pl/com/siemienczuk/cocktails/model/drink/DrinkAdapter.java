package pl.com.siemienczuk.cocktails.model.drink;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.net.URL;


public class DrinkAdapter extends TypeAdapter<Drink>{
    @Override
    public void write(JsonWriter out, Drink drink) throws IOException {
        out.beginObject();
        out.name("strDrink").value(drink.getName());
        out.name("idDrink").value(drink.getId());
        out.name("strDrinkThumb").value(drink.getPhotoUrl().toString());
        out.endObject();
    }

    @Override
    public Drink read(JsonReader in) throws IOException {
        final Drink drink = new Drink();

        in.beginObject();
        while(in.hasNext()){
            switch(in.nextName()){
                case "strDrink":
                    if(in.peek() != JsonToken.NULL){
                        drink.setName(in.nextString());
                    } else {
                        in.nextNull();
                    }
                    break;
                case "idDrink":
                    drink.setId(in.nextLong());
                    break;
                case "strDrinkThumb":
                    if(in.peek() != JsonToken.NULL){
                        drink.setPhotoUrl(new URL(in.nextString()));
                    } else {
                        in.nextNull();
                    }
                    break;
            }
        }
        in.endObject();
        return drink;
    }
}
