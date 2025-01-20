package com.example.test_stoica_robert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraint_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etMaterial=findViewById(R.id.stoica_robert_etMaterial);
        EditText etLungime=findViewById(R.id.stoica_robert_etLungime);
        CheckBox cbSmartphone= findViewById(R.id.stoica_robert_cbSmartphone);

        Intent intent=getIntent();
        if(intent.hasExtra("husaDeModificat")){
            HusaTelefon husaTelefon=intent.getParcelableExtra("husaDeModificat");

            etMaterial.setText(String.valueOf(husaTelefon.getMaterial()));
            etLungime.setText(String.valueOf(husaTelefon.getLungime()));
            cbSmartphone.setChecked(husaTelefon.getSmartphone());

        }

        Button button=findViewById(R.id.stoica_robert_btnSalveaza);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                EditText etMaterial=findViewById(R.id.stoica_robert_etMaterial);
//                EditText etLungime=findViewById(R.id.stoica_robert_etLungime);
//                CheckBox cbSmartphone= findViewById(R.id.stoica_robert_cbSmartphone);

                HusaTelefon husaTelefon=new HusaTelefon(etMaterial.getText().toString(),Integer.parseInt(etLungime.getText().toString()),cbSmartphone.isChecked());
                Intent intent=new Intent();
                intent.putExtra("valoare",husaTelefon);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }
}