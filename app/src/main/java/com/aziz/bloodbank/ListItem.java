package com.aziz.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListItem extends Fragment {

    DonerData donerData = new DonerData();

    public ListItem() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            donerData.id = getArguments().getInt("Id");
            donerData.full_name = getArguments().getString("Name");
            donerData.city = getArguments().getString("City");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_item, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name  = (TextView) view.findViewById(R.id.name);
        TextView location  = (TextView) view.findViewById(R.id.location);

        name.setText(donerData.full_name);
        location.setText(donerData.city);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(),DetailActivity.class);
                i.putExtra("Id",donerData.id);
                startActivity(i);
            }
        });
    }
}
