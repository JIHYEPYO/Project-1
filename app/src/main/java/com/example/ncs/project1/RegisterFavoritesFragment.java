package com.example.ncs.project1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by NCS on 2017-05-16.
 */

public class RegisterFavoritesFragment extends android.support.v4.app.Fragment {

    EditText editTextSubject;
    EditText editTextURL;
    Button buttonRegisterDate;
    Button buttonExit;
    Button buttonRegister;

    private static RegisterFavoritesFragment instance = new RegisterFavoritesFragment();

    DataBaseController dbController;
    SQLiteDatabase db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register_favorites, container, false);

        editTextSubject = (EditText) v.findViewById(R.id.EditTextSubject);
        editTextURL = (EditText) v.findViewById(R.id.EditTextURL);
        buttonRegisterDate = (Button) v.findViewById(R.id.ButtonRegisterDate);
        buttonExit = (Button) v.findViewById(R.id.ButtonExit);
        buttonRegister = (Button) v.findViewById(R.id.ButtonRegister);

        dbController = new DataBaseController(v.getContext());

        try {
            db = dbController.getWritableDatabase();
        } catch (SQLiteException e) {
            db = dbController.getReadableDatabase();
        }

        buttonRegisterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dFragment = new DatePickerFragment();
                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = editTextSubject.getText().toString();
                String url = editTextURL.getText().toString();
                String date = DatePickerFragment.Year + "-" + DatePickerFragment.Month + "-" + DatePickerFragment.Day;

                if (!subject.equals("") && !url.equals("") && !date.equals("0-0-0")) {
                    db.execSQL("INSERT INTO contacts VALUES (null, '" + subject + "', '" + url + "', '" + date + "');");
                    editTextSubject.setText("");
                    editTextURL.setText("");
                    DatePickerFragment.Year = 0;
                    DatePickerFragment.Month = 0;
                    DatePickerFragment.Day = 0;

                    Toast.makeText(getContext(), "관심사이트로 등록되었습니다", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "모든 값을 입력해 주세요", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        public static int Year, Month, Day;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            Year = calendar.get(Calendar.YEAR);
            Month = calendar.get(Calendar.MONTH);
            Day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_LIGHT, this, Year, Month, Day);

            return dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            Year = year;
            Month = month;
            Day = day;
        }

        public void onCancel(DialogInterface dialog) {
            Toast.makeText(getActivity(), "Date Picker Canceled.", Toast.LENGTH_SHORT).show();
        }
    }

    public static synchronized RegisterFavoritesFragment getInstance() {
        return instance;
    }
}
