package pl.com.siemienczuk.cocktails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CocktailDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_detail);

        Intent entryIntent = this.getIntent();
        String cocktailName = "";
        if(entryIntent.hasExtra("cocktail_name")){
            cocktailName = entryIntent.getStringExtra("cocktail_name");
            TextView tv = findViewById(R.id.cocktail_details_activity_title_tv);
            tv.setText(cocktailName);
        }
    }
}
