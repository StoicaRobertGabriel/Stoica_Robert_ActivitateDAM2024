package com.example.lab10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etMaterial, etLungime;
    private Button btnAdd, btnLoad, btnFavorites;
    private ListView listView;
    private HusaTelefonDatabase database;
    private ArrayAdapter<String> adapter;
    private List<HusaTelefon> husaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMaterial = findViewById(R.id.etMaterial);
        etLungime = findViewById(R.id.etLungime);
        btnAdd = findViewById(R.id.btnAdd);
        btnLoad = findViewById(R.id.btnLoad);
        btnFavorites = findViewById(R.id.btnFavorites);
        listView = findViewById(R.id.listView);

        database = HusaTelefonDatabase.getInstance(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String material = etMaterial.getText().toString().trim();
                int lungime = Integer.parseInt(etLungime.getText().toString().trim());
                HusaTelefon husa = new HusaTelefon(material, lungime, true);

                new InsertHusaAsyncTask(database.husaTelefonDao()).execute(husa);
                saveToFile(husa);
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadHuseAsyncTask(database.husaTelefonDao()).execute();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                HusaTelefon selectedHusa = husaList.get(i);
                saveToFavorites(selectedHusa);
                Toast.makeText(getApplicationContext(),"New favorite item saved",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        btnFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void saveToFile(HusaTelefon husa) {
        try (FileOutputStream fos = openFileOutput("husa_data.txt", Context.MODE_APPEND);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(husa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFavorites(HusaTelefon husa) {
        SharedPreferences sharedPreferences = getSharedPreferences("favorite_husa", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String json = sharedPreferences.getString("favorites", "[]");
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HusaTelefon>>() {}.getType();
        ArrayList<HusaTelefon> favorites = gson.fromJson(json, type);

        if (favorites == null) {
            favorites = new ArrayList<>();
        }

        favorites.add(husa);
        editor.putString("favorites", gson.toJson(favorites));
        editor.apply();
    }

    private static class InsertHusaAsyncTask extends AsyncTask<HusaTelefon, Void, Void> {
        private HusaTelefonDao husaTelefonDao;

        InsertHusaAsyncTask(HusaTelefonDao husaTelefonDao) {
            this.husaTelefonDao = husaTelefonDao;
        }

        @Override
        protected Void doInBackground(HusaTelefon... huse) {
            husaTelefonDao.insert(huse[0]);
            return null;
        }
    }

    private class LoadHuseAsyncTask extends AsyncTask<Void, Void, List<HusaTelefon>> {
        private HusaTelefonDao husaTelefonDao;

        LoadHuseAsyncTask(HusaTelefonDao husaTelefonDao) {
            this.husaTelefonDao = husaTelefonDao;
        }

        @Override
        protected List<HusaTelefon> doInBackground(Void... voids) {
            return husaTelefonDao.getAllHuse();
        }

        @Override
        protected void onPostExecute(List<HusaTelefon> huse) {
            husaList = huse;
            updateListView();
        }
    }

    private void updateListView() {
        String[] husaDetails = new String[husaList.size()];
        for (int i = 0; i < husaList.size(); i++) {
            husaDetails[i] = husaList.get(i).toString();
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, husaDetails);
        listView.setAdapter(adapter);
    }
}
