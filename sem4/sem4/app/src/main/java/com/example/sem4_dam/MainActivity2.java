package com.example.sem4_dam;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btn=findViewById(R.id.btnSalveaza);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText etnMSN=findViewById(R.id.editTextNumber);
                Spinner spin=findViewById(R.id.spinner2);
                spin.getSelectedItem();
                EditText etManufacturer=findViewById(R.id.editTextNumber);
                Boolean wireless=((CheckBox)findViewById(R.id.checkBox)).isChecked();
            }
        });
    }
}