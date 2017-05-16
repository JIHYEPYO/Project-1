package com.example.ncs.project1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by NCS on 2017-05-16.
 */

public class RegisterFavoritesFragment extends android.support.v4.app.Fragment {

    EditText editTextSubject;
    EditText editTextURL;
    Button buttonRegisterDate;

    private static RegisterFavoritesFragment instance = new RegisterFavoritesFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register_favorites, container, false);

        editTextSubject = (EditText) v.findViewById(R.id.EditTextSubject);
        editTextURL = (EditText) v.findViewById(R.id.EditTextURL);
        buttonRegisterDate = (Button) v.findViewById(R.id.ButtonRegisterDate);

        buttonRegisterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return v;
    }

    public static synchronized RegisterFavoritesFragment getInstance() {
        return instance;
    }
}
