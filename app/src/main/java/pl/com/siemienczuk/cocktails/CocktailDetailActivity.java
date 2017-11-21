package pl.com.siemienczuk.cocktails;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CocktailDetailActivity extends AppCompatActivity {
    TextView drinkNameTv;
    ImageView drinkImageIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail_detail);

        drinkNameTv = findViewById(R.id.cocktail_details_activity_title_tv);
        drinkImageIv = findViewById(R.id.cocktail_details_iv);

        Intent entryIntent = this.getIntent();
        String cocktailName = "";
        String thumbUrl = "";
        if(entryIntent.hasExtra("cocktail_name")){
            cocktailName = entryIntent.getStringExtra("cocktail_name");
            drinkNameTv.setText(cocktailName);
        }

        if(entryIntent.hasExtra("cocktail_thumb_url")){
            thumbUrl = entryIntent.getStringExtra("cocktail_thumb_url");
            Picasso.with(this).load(thumbUrl).into(drinkImageIv);
        }
    }
}
