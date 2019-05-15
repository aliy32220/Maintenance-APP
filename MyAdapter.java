package com.example.ali.fastmainappfinal;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class MyAdapter extends ArrayAdapter<String> {
    ArrayList<String> ProblemList= new ArrayList<>();
    public MyAdapter(Context context,int textViewResourceId, ArrayList<String> objects)
    {
        super(context,textViewResourceId,objects);
        ProblemList=objects;
    }
    @Override
    public int getCount(){
        return super.getCount();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v=convertView;
        LayoutInflater inflater=(LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=inflater.inflate(R.layout.listviewfile,null);
        TextView textView=(TextView) v.findViewById(R.id.textView);
        textView.setText(ProblemList.get(position));
        return v;
    }
}
