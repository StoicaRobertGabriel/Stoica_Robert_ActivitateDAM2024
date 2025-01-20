package com.example.lab10;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    private ListView listView;
    private DatabaseReference firebaseRef;
    private List<String> favoriteHuse = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        listView = findViewById(R.id.lvFavFirebase);
        firebaseRef = FirebaseDatabase.getInstance().getReference("HusaTelefon");

        loadFavorites();
    }

    private void loadFavorites() {
        firebaseRef.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                favoriteHuse.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    HusaTelefon husa = ds.getValue(HusaTelefon.class);
                    favoriteHuse.add(husa.toString());
                }
                adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, favoriteHuse);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
