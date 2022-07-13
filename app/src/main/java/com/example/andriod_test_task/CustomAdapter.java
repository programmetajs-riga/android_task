package com.example.andriod_test_task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context context;
    String[] sportname;
    int sportimages[];
    LayoutInflater inflater;

    public CustomAdapter(Context ctx, String[] sportname, int[] sportimages){
        this.context= ctx;
        this.sportname=sportname;
        this.sportimages=sportimages;
        this.inflater=LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return sportname.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_custom_list_view,null);
        TextView sporttext = (TextView) view.findViewById(R.id.sportname);
        ImageView sportimg = (ImageView) view.findViewById(R.id.image);
        sporttext.setText(sportname[i]);
        sportimg.setImageResource(sportimages[i]);

        return view;
    }
}
