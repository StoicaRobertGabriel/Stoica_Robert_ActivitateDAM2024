package com.example.lab10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    private CheckBox cbAvailableOnline;
    private ListView listView;
    private HusaTelefonDatabase database;
    private ArrayAdapter<String> adapter;
    private List<HusaTelefon> husaList = new ArrayList<>();

    private DatabaseReference firebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMaterial = findViewById(R.id.etMaterial);
        etLungime = findViewById(R.id.etLungime);
        btnAdd = findViewById(R.id.btnAdd);
        btnLoad = findViewById(R.id.btnLoad);
        btnFavorites = findViewById(R.id.btnFavorites);
        cbAvailableOnline = findViewById(R.id.cbOnline);
        listView = findViewById(R.id.listView);

        database = HusaTelefonDatabase.getInstance(this);

        firebaseRef = FirebaseDatabase.getInstance().getReference("HusaTelefon");

        btnAdd.setOnClickListener(v -> {
            String material = etMaterial.getText().toString().trim();
            int lungime = Integer.parseInt(etLungime.getText().toString().trim());
            HusaTelefon husa = new HusaTelefon(material, lungime, true);

            new InsertHusaAsyncTask(database.husaTelefonDao()).execute(husa);
            saveToFile(husa);

            if (cbAvailableOnline.isChecked()) {
                saveToFirebase(husa);
            }
        });

        btnLoad.setOnClickListener(v -> new LoadHuseAsyncTask(database.husaTelefonDao()).execute());

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            HusaTelefon selectedHusa = husaList.get(position);
            saveToFavorites(selectedHusa);
            Toast.makeText(getApplicationContext(), "New favorite item saved", Toast.LENGTH_SHORT).show();
            return true;
        });

        btnFavorites.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity3.class);
            startActivity(intent);
        });

        listenToFirebaseChanges();
    }

    private void saveToFile(HusaTelefon husa) {
        try (FileOutputStream fos = openFileOutput("husa_data.txt", Context.MODE_APPEND);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(husa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFirebase(HusaTelefon husa) {
        String key = firebaseRef.push().getKey();
        firebaseRef.child(key).setValue(husa)
                .addOnSuccessListener(aVoid -> Toast.makeText(getApplicationContext(), "Saved to Firebase", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Failed to save online", Toast.LENGTH_SHORT).show());
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

    private void listenToFirebaseChanges() {
        firebaseRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(MainActivity.this, "Database updated!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to listen to changes", Toast.LENGTH_SHORT).show();
            }
        });
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
