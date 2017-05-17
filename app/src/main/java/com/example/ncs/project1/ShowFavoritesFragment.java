package com.example.ncs.project1;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.TableLayout;

import java.util.ArrayList;

/**
 * Created by NCS on 2017-05-16.
 */

public class ShowFavoritesFragment extends android.support.v4.app.Fragment {

    Button buttonExit;
    Button buttonShow;
    ListView listView;

    SQLiteDatabase db;
    DataBaseController dbController;

    String url;

    private static ShowFavoritesFragment instance = new ShowFavoritesFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_favorites, container, false);

        buttonExit = (Button) v.findViewById(R.id.ButtonExit);
        buttonShow = (Button) v.findViewById(R.id.ButtonShow);
        listView = (ListView) v.findViewById(R.id.ListViewShow);

        dbController = new DataBaseController(getContext());
        db = dbController.getWritableDatabase();

        final View header = getActivity().getLayoutInflater().inflate(R.layout.listview_header, null, false);
        listView.addHeaderView(header);

        url = "";

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Database item = (Database) parent.getItemAtPosition(position) ;
                url = item.getUrl();
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(url.equals("")){
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
                }else{
                    Intent intent = new Intent();
                    intent.putExtra("SET_URL",url.toString());
                    getActivity().setResult(Activity.RESULT_OK,intent);
                    getActivity().finish();
                }

            }
        });

        return v;
    }

    public static synchronized ShowFavoritesFragment getInstance() {
        return instance;
    }
}
