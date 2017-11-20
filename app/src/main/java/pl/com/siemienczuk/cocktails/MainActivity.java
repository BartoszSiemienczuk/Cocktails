package pl.com.siemienczuk.cocktails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView cocktailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cocktailList = (ListView)findViewById(R.id.cocktails_list_view);

        ArrayList<String> cocktails = new ArrayList<>();
        cocktails.add("Barbados Sunrise");
        cocktails.add("Crazy Frog");
        cocktails.add("Classic Mojito");
        cocktails.add("Blue Mojito");
        cocktails.add("Cosmopolitan");
        cocktails.add("Sex on the beach");
        cocktails.add("Martini");
        cocktails.add("Long Island Iced Tea");

        ArrayAdapter<String> cocktailAdapter = new ArrayAdapter<String>(
                this, R.layout.cocktail_list_item, R.id.cocktail_list_item_tv, cocktails
        );

        cocktailList.setAdapter(cocktailAdapter);

        cocktailList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, "Toast with drink no."+ i, Toast.LENGTH_LONG).show();
                
            }
        });
    }
}
