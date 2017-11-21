package pl.com.siemienczuk.cocktails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cz.msebera.android.httpclient.Header;
import pl.com.siemienczuk.cocktails.model.drink.Drink;
import pl.com.siemienczuk.cocktails.model.drink.DrinkAdapter;
import pl.com.siemienczuk.cocktails.model.drink.DrinkListWrapper;
import pl.com.siemienczuk.cocktails.service.CocktailsDbEndpoints;

public class CocktailsListActivity extends AppCompatActivity {
    ListView cocktailListView;
    List<Drink> cocktailList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails_list);

        cocktailListView = (ListView)findViewById(R.id.cocktails_list_view);

        final ArrayList<String> cocktails = new ArrayList<>();
        cocktails.add("Loading...");

        final ArrayAdapter<String> cocktailAdapter = new ArrayAdapter<String>(
                this, R.layout.cocktail_list_item, R.id.cocktail_list_item_tv, cocktails
        );

        cocktailListView.setAdapter(cocktailAdapter);

        cocktailListView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent cocktailDetailIntent = new Intent(CocktailsListActivity.this, CocktailDetailActivity.class);
            cocktailDetailIntent.putExtra("cocktail_name", cocktailList.get(i).getName());
            cocktailDetailIntent.putExtra("cocktail_thumb_url", cocktailList.get(i).getPhotoUrl().toString());
            startActivity(cocktailDetailIntent);
        });

        AsyncHttpClient cl = new AsyncHttpClient();
        cl.get(CocktailsDbEndpoints.FILTER_BY_CATEGORY,
            new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.e("Error", responseString);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    Log.d("Success:", responseString);
                    Gson gson = new GsonBuilder().registerTypeAdapter(Drink.class, new DrinkAdapter()).create();
                    DrinkListWrapper downloadedDrinks = gson.fromJson(responseString, DrinkListWrapper.class);
                    cocktailAdapter.clear();
                    cocktailList.clear();
                    cocktailList.addAll(downloadedDrinks.getDrinks());
                    cocktailAdapter.addAll(cocktailList.stream().map(Drink::getName).collect(Collectors.toList()));
                }
            }
        );
    }
}
