package com.example.sem2_srg;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Log.wtf("activitate","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("activitate","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("activitate","onResume");
        Toast.makeText(this,R.string.mesaj,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("activitate","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("activitate","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("activitate","onDestroy");
    }
}