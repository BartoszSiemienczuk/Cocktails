package pl.com.siemienczuk.cocktails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import pl.com.siemienczuk.cocktails.model.drink.Drink;
import pl.com.siemienczuk.cocktails.model.drink.DrinkAdapter;
import pl.com.siemienczuk.cocktails.model.drink.DrinkListWrapper;

public class MainActivity extends AppCompatActivity {
    ListView cocktailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cocktailList = (ListView)findViewById(R.id.cocktails_list_view);

        final ArrayList<String> cocktails = new ArrayList<>();
        cocktails.add("Barbados Sunrise");
        cocktails.add("Crazy Frog");
        cocktails.add("Classic Mojito");
        cocktails.add("Blue Mojito");
        cocktails.add("Cosmopolitan");
        cocktails.add("Sex on the beach");
        cocktails.add("Martini");
        cocktails.add("Long Island Iced Tea");

        final ArrayAdapter<String> cocktailAdapter = new ArrayAdapter<String>(
                this, R.layout.cocktail_list_item, R.id.cocktail_list_item_tv, cocktails
        );

        cocktailList.setAdapter(cocktailAdapter);

        cocktailList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, "Toast with drink no."+ i, Toast.LENGTH_LONG).show();
                Intent cocktailDetailIntent = new Intent(MainActivity.this, CocktailDetailActivity.class);
                cocktailDetailIntent.putExtra("cocktail_name", cocktails.get(i));
                startActivity(cocktailDetailIntent);
            }
        });

        AsyncHttpClient cl = new AsyncHttpClient();
        cl.get("http://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail",
            new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.e("Error", responseString);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    Log.d("Success:", responseString);
                    Gson gson = new GsonBuilder().registerTypeAdapter(Drink.class, new DrinkAdapter()).create();
                    DrinkListWrapper drinkList = gson.fromJson(responseString, DrinkListWrapper.class);
                    cocktailAdapter.clear();
                    drinkList.getDrinks().forEach(drink -> cocktailAdapter.add(drink.getName()));
                }
            }
        );
    }
}
