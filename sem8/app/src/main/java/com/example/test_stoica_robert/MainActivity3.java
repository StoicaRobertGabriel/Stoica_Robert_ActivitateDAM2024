package com.example.test_stoica_robert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    ArrayList<HusaTelefon>huse=null;
    private int idModificat=0;
//    private HusaAdapter adapter=null;
    private HusaAdapter2 adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView listView=findViewById(R.id.lvHuse);
        huse=getIntent().getParcelableArrayListExtra("huse");

        adapter=new HusaAdapter2(getApplicationContext(),R.layout.layout_list_view_custom_adaptor,huse);
        listView.setAdapter(adapter);

//        ArrayAdapter<HusaTelefon>arrayAdapter=new ArrayAdapter<>(getApplicationContext(), R.layout.layout_list_view_custom_adaptor, huse);
//        listView.setAdapter(arrayAdapter);
//        adapter=new HusaAdapter(huse,getApplicationContext(), android.R.layout.simple_list_item_1);//R.layout.layout_list_view_custom_adaptor
//        listView.setAdapter(adapter);

//        listView.setOnItemClickListener((parent, view, position, id) -> {
//            HusaTelefon clickedCase = huse.get(position);
//            Toast.makeText(MainActivity3.this, clickedCase.toString(), Toast.LENGTH_SHORT).show();
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentModifica=new Intent(getApplicationContext(), MainActivity2.class);
                intentModifica.putExtra("husaDeModificat",huse.get(position));
                idModificat=position;
                startActivityForResult(intentModifica,899);
//                startActivity(intentModifica);
                Toast.makeText(getApplicationContext(),huse.get(position).toString(),Toast.LENGTH_SHORT).show();
//                HusaTelefon husaSelectata=huse.get(position);
//                Toast.makeText(getApplicationContext(),husaSelectata.toString(),Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                HusaTelefon husaStearsa=huse.get(position);
                huse.remove(position);
//                arrayAdapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"Husa "+husaStearsa.toString()+" a fost stearsa!",Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&&requestCode==899){
            huse.set(idModificat,data.getParcelableExtra("valoare"));
            adapter.notifyDataSetChanged();
        }
        Intent resultIntent = new Intent();
        resultIntent.putParcelableArrayListExtra("updatedHuseList", huse);
        setResult(RESULT_OK, resultIntent);
//        finish();
    }
}