package com.example.lab10;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private ListView favoriteListView;
    private ArrayAdapter<String> adapter;
    private List<HusaTelefon> favoriteHusaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        favoriteListView = findViewById(R.id.lvFavorites);
        loadFavorites();
    }

    private void loadFavorites() {
        SharedPreferences sharedPreferences = getSharedPreferences("favorite_husa", MODE_PRIVATE);
        String json = sharedPreferences.getString("favorites", "[]");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HusaTelefon>>() {}.getType();
        favoriteHusaList = gson.fromJson(json, type);

        String[] favoriteDetails = new String[favoriteHusaList.size()];
        for (int i = 0; i < favoriteHusaList.size(); i++) {
            favoriteDetails[i] = favoriteHusaList.get(i).toString();
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, favoriteDetails);
        favoriteListView.setAdapter(adapter);
    }
}
