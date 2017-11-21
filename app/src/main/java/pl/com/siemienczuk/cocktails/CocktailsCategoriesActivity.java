package pl.com.siemienczuk.cocktails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
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
import pl.com.siemienczuk.cocktails.model.category.Category;
import pl.com.siemienczuk.cocktails.model.category.CategoryListWrapper;
import pl.com.siemienczuk.cocktails.service.CocktailsDbEndpoints;

public class CocktailsCategoriesActivity extends AppCompatActivity {
    ListView categoriesLV;

    List<String> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails_categories);

        categoriesLV = findViewById(R.id.categories_list_view);

        categories.add("Loading...");

        final ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this, R.layout.category_list_item, R.id.category_list_item_tv, categories);

        categoriesLV.setAdapter(categoriesAdapter);

        categoriesLV.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent cocktailListIntent = new Intent(this, CocktailsListActivity.class);
            cocktailListIntent.putExtra("category_name", categories.get(i));
            startActivity(cocktailListIntent);
        });


        AsyncHttpClient cl = new AsyncHttpClient();
        cl.get(CocktailsDbEndpoints.CATEGORY_LIST, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(CocktailsListActivity.class.getName(), "Error");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("Success:", responseString);
                Gson gson = new GsonBuilder().create();
                CategoryListWrapper downloadedCategories = gson.fromJson(responseString, CategoryListWrapper.class);
                categories.clear();
                categories.addAll(downloadedCategories.getCategories().stream().map(Category::getName).collect(Collectors.toList()));
                categoriesAdapter.notifyDataSetChanged();
            }
        });


    }
}
