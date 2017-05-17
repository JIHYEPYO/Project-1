package com.example.ncs.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by PYOJIHYE on 2017-05-16.
 */

public class ListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<Database> arrayList;

    public ListViewAdapter(Context context, ArrayList<Database> list) {
        this.context = context;
        arrayList = list;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Database database = arrayList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_contents, null);
        }

        TextView textViewSubject = (TextView) convertView.findViewById(R.id.TableTextViewSubject);
        textViewSubject.setText(database.getSubject());

        TextView textViewURL = (TextView) convertView.findViewById(R.id.TableTextViewURL);
        textViewURL.setText(database.getUrl());

        TextView textViewDate = (TextView) convertView.findViewById(R.id.TableTextViewDate);
        textViewDate.setText(database.getDate());

        return convertView;
    }

}