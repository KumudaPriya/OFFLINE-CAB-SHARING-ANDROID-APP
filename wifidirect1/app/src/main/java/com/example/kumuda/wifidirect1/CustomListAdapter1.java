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
public class CustomListAdapter1 extends BaseAdapter {
    private ArrayList<page5a.personItem> listData;
    private LayoutInflater layoutInflater;

    private final Context context;




    public CustomListAdapter1(Context aContext, ArrayList<page5a.personItem> listData) {
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
            convertView = layoutInflater.inflate(R.layout.list_row1, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.textView7_list_row1);
            holder.degreeView = (TextView) convertView.findViewById(R.id.textView8_list_row1);
            holder.mobileView = (TextView) convertView.findViewById(R.id.textView9_list_row1);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + listData.get(position).getname());
        holder.nameView.setText("NAME : " + listData.get(position).getname());

        System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + listData.get(position).getdegree());
        holder.degreeView.setText("DEGREE : " + listData.get(position).getdegree());

        System.out.println("SSSSSSSSSSSSSSSSSSSSSSS" + listData.get(position).getmobile());
        holder.mobileView.setText("MOBILE : " + listData.get(position).getmobile());

        System.out.println("enterd10");

        return convertView;
    }

    static class ViewHolder {
        TextView nameView;
        TextView degreeView;
        TextView mobileView;

    }
}