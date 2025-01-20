package com.example.test_stoica_robert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HusaAdapter extends BaseAdapter {
    private ArrayList<HusaTelefon> huse=null;
    private Context context;
    private int theLayout;

    public HusaAdapter(ArrayList<HusaTelefon> huse, Context context, int theLayout) {
        this.huse = huse;
        this.context = context;
        this.theLayout = theLayout;
    }

    @Override
    public int getCount() {
        return huse.size();
    }

    @Override
    public Object getItem(int position) {
        return huse.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(theLayout,parent,false);

        TextView tvMaterial=view.findViewById(R.id.tvMaterial);
        TextView tvLungime=view.findViewById(R.id.tvLungime);
        CheckBox cbSmart=view.findViewById(R.id.cbSmart);

        HusaTelefon husaTelefon=(HusaTelefon)getItem(position);

        tvMaterial.setText(husaTelefon.getMaterial());
        tvLungime.setText(husaTelefon.getLungime());
        cbSmart.setChecked(husaTelefon.getSmartphone());
        return view;
    }
}
