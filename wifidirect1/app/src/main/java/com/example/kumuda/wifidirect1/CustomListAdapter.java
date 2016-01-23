package com.example.kumuda.wifidirect1;

/**
 * Created by kumuda on 15/12/15.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;

/**
 * Created by likki on 11/16/15.
 */


public class CustomListAdapter extends BaseAdapter {
    private ArrayList<page4.cabItem> listData;
    private LayoutInflater layoutInflater;

    private final Context context;




    public CustomListAdapter(Context aContext, ArrayList<page4.cabItem> listData) {
        System.out.println("enterd7");
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        this.context = aContext;
        System.out.println("enterd7");
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("enterd8");
        ViewHolder holder;
        if (convertView == null) {
            System.out.println("enterd9");
            convertView = layoutInflater.inflate(R.layout.date_time_row, null);
            holder = new ViewHolder();
            holder.dateView = (TextView) convertView.findViewById(R.id.date_allcab);
            holder.timeView = (TextView) convertView.findViewById(R.id.time_allcab);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + listData.get(position).getdate());
        holder.dateView.setText(listData.get(position).getdate());

        System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + listData.get(position).gettime());
        holder.timeView.setText(listData.get(position).gettime());

        System.out.println("enterd10");

        return convertView;
    }

    static class ViewHolder {
        TextView dateView;
        TextView timeView;

    }
}