package com.example.test_stoica_robert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class HusaAdapter2 extends ArrayAdapter<HusaTelefon> {
    private Context context;
    private int resource;
    private List<HusaTelefon> cases;

    public HusaAdapter2(Context context, int resource, List<HusaTelefon> cases) {
        super(context, resource, cases);
        this.context = context;
        this.resource = resource;
        this.cases = cases;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the layout if it's not already inflated
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
        }

        // Get the current Case object
        HusaTelefon currentCase = cases.get(position);

        // Bind data to the views in the layout
        TextView materialText = convertView.findViewById(R.id.tvMaterial);
        TextView sizeText = convertView.findViewById(R.id.tvLungime);
        CheckBox smartphoneCheckbox = convertView.findViewById(R.id.cbSmart);

        materialText.setText("Material: " + currentCase.getMaterial());
        sizeText.setText("Size: " + currentCase.getLungime());
        smartphoneCheckbox.setChecked(currentCase.getSmartphone());

        // Handle changes to the CheckBox state
        smartphoneCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentCase.setSmartphone(isChecked);
        });

        return convertView;
    }
}
