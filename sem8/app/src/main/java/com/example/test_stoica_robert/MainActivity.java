package com.example.test_stoica_robert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<HusaTelefon> huseTelefoane=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.constraint_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button button=findViewById(R.id.stoica_robert_btnActivity2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity2.class);
                startActivityForResult(intent,999);
            }
        });
        Button button1=findViewById(R.id.btnLista);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity3.class);
                intent.putParcelableArrayListExtra("huse",huseTelefoane);
                startActivityForResult(intent,998);
            }
        });
        Button button2=findViewById(R.id.btnImg);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity4.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==999&&resultCode==RESULT_OK){
            HusaTelefon husaTelefon=data.getParcelableExtra("valoare");
            huseTelefoane.add(husaTelefon);
            Toast.makeText(getApplicationContext(),"Husa adaugata. Total: "+huseTelefoane.size(),Toast.LENGTH_LONG).show();
        }
        if(requestCode==998&&resultCode==RESULT_OK){
            huseTelefoane = data.getParcelableArrayListExtra("updatedHuseList");
        }
    }
}