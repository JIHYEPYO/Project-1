package com.example.ncs.project1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

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
                DialogFragment dFragment = new DatePickerFragment();
                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });

        return v;
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT,this,year,month,day);

            // Return the DatePickerDialog
            return  dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day){
            // Do something with the chosen date
        }

        public void onCancel(DialogInterface dialog){
            Toast.makeText(getActivity(),"Date Picker Canceled.", Toast.LENGTH_SHORT).show();
        }
    }

    public static synchronized RegisterFavoritesFragment getInstance() {
        return instance;
    }
}
