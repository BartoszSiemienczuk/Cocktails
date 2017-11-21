package pl.com.siemienczuk.cocktails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CocktailsCategoriesActivity extends AppCompatActivity {
    ListView categoriesLV;

    List<String> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktails_categories);

        categoriesLV = findViewById(R.id.categories_list_view);

        categories.add("Ordinary Drink");
        categories.add("Cocktail");
        categories.add("Cocoa");
        categories.add("Beer");

        final ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this, R.layout.category_list_item, R.id.category_list_item_tv, categories);

        categoriesLV.setAdapter(categoriesAdapter);

        categoriesLV.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent cocktailListIntent = new Intent(this, CocktailsListActivity.class);
            cocktailListIntent.putExtra("category_name", categories.get(i));
            startActivity(cocktailListIntent);
        });
    }
}
