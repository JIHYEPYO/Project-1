package com.example.ncs.project1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by NCS on 2017-05-16.
 */

public class RemoveFavoritesFragment extends android.support.v4.app.Fragment {

    Button buttonExit;
    Button buttonRemove;
    ListView listView;

    SQLiteDatabase db;
    DataBaseController dbController;
    Database item;

    private static RemoveFavoritesFragment instance = new RemoveFavoritesFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_remove_favorites, container, false);

        buttonExit = (Button)v.findViewById(R.id.ButtonExit);
        buttonRemove = (Button)v.findViewById(R.id.ButtonRemove);
        listView = (ListView) v.findViewById(R.id.ListViewRemove);

        dbController = new DataBaseController(getContext());
        db = dbController.getWritableDatabase();

        final View header = getActivity().getLayoutInflater().inflate(R.layout.listview_header, null, false);
        listView.addHeaderView(header);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item = (Database) parent.getItemAtPosition(position) ;
            }
        });

        DatabaseReset();

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getUrl()!=null){
                    db.delete("contacts", "url = ?", new String[] { String.valueOf(item.getUrl()) });
                    DatabaseReset();
                }
            }
        });

        return v;
    }

    public void DatabaseReset(){
        try {
            String query = "SELECT * FROM contacts;";
            Cursor cursor = db.rawQuery(query, null);
            ArrayList<Database> arrayList = new ArrayList<Database>();
            arrayList.clear();

            while (cursor.moveToNext()) {
                Database database = new Database();
                String subject = cursor.getString(cursor.getColumnIndex("subject"));
                String url = cursor.getString(cursor.getColumnIndex("url"));
                String date = cursor.getString(cursor.getColumnIndex("date"));

                database.setSubject(subject);
                database.setUrl(url);
                database.setDate(date);

                arrayList.add(database);
            }
            cursor.close();
            ListViewAdapter listViewAdapter = new ListViewAdapter(getContext(), arrayList);
            listView.setAdapter(listViewAdapter);
        } catch (Exception e) {

        }
    }

    public static synchronized RemoveFavoritesFragment getInstance() {
        return instance;
    }
}
