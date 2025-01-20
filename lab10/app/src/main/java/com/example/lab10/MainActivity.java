package com.example.lab10;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etMaterial, etLungime;
    private Button btnAdd, btnLoad;
    private ListView listView;
    private HusaTelefonDatabase database;
    private ArrayAdapter<String> adapter;
    private List<HusaTelefon> husaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMaterial = findViewById(R.id.etMaterial);
        etLungime = findViewById(R.id.etLungime);
        btnAdd = findViewById(R.id.btnAdd);
        btnLoad = findViewById(R.id.btnLoad);
        listView = findViewById(R.id.listView);

        database = HusaTelefonDatabase.getInstance(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String material = etMaterial.getText().toString().trim();
                int lungime = Integer.parseInt(etLungime.getText().toString().trim());
                HusaTelefon husa = new HusaTelefon(material, lungime, true);

                new InsertHusaAsyncTask(database.husaTelefonDao()).execute(husa);
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadHuseAsyncTask(database.husaTelefonDao()).execute();
            }
        });
    }

    private class InsertHusaAsyncTask extends AsyncTask<HusaTelefon, Void, Void> {
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
