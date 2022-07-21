package com.example.andriod_test_task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context Context;
    String[] SportName;
    int SportImages[];
    LayoutInflater Inflater;

    public CustomAdapter(Context ctx, String[] sportname, int[] sportimages){
        this.Context= ctx;
        this.SportName=sportname;
        this.SportImages=sportimages;
        this.Inflater=LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return SportName.length;
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
        view = Inflater.inflate(R.layout.activity_custom_list_view,null);
        TextView SportText = (TextView) view.findViewById(R.id.sportname);
        ImageView SportImg = (ImageView) view.findViewById(R.id.image);
        SportText.setText(SportName[i]);
        SportImg.setImageResource(SportImages[i]);

        return view;
    }
}
