package com.example.kumuda.wifidirect1;

/**
 * Created by kumuda on 15/12/15.
 */
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by likki on 11/16/15.
 */
public class page4 extends ListActivity {  //to display the page of view all cabs. each cab is displayed by showing its departure time and date

    private ArrayList<cabItem> results_priv = new ArrayList<cabItem>();

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        System.out.println("enterd1");
        ArrayList details = getListData();
        System.out.println("enterd2");


        CustomListAdapter adapter = new CustomListAdapter(this, details);
        System.out.println("enterd3");
        setListAdapter(adapter);

        System.out.println("enterd4");

    }

    public class cabItem {

        private String date;
        private String time;


        public String getdate() {
            return date;
        }

        public void setdate(String date) {
            this.date = date;
        }

        public String gettime() {
            return time;
        }

        public void settime(String time) {
            this.time = time;
        }


    }

    private ArrayList getListData() {

     /*   String[][] a = new String[4][2];

        a[0][0] = "21/12/2016";
        a[0][1] = "4:15 pm";

        a[1][0] = "01/04/2015";
        a[1][1] = "10:00 am";

        a[2][0] = "30/09/2012";
        a[2][1] = "8:30 pm";

        a[3][0] = "11/06/2015";
        a[3][1] = "6:30 am";*/

        System.out.println("Entered read fileeeeeeeeeeeeeeee");
        read_file ob6 = new read_file();
        read_file.decode();
        System.out.println("Entered read fileeeeeeeeeeeeeeee");

        String[][] a = new String[MainActivity.no_of_cabs][2];
        for(int i = 0 ; i < MainActivity.no_of_cabs ; i++ ) {
            a[i][0] = MainActivity.output[i][2];
            a[i][1] = MainActivity.output[i][3];
        }

        ArrayList<cabItem> results = new ArrayList<cabItem>();


        for(int i=0;i<MainActivity.no_of_cabs;i++) {
            cabItem newsData =  new cabItem();

                newsData.setdate(a[i][0]);
                newsData.settime(a[i][1]);
                results.add(newsData);

        }

        results_priv = (ArrayList<cabItem>)results.clone();
        for(int j = 0; j < results_priv.size(); j++) {

            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDD"  + results_priv.get(j).date);
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDD"  + results_priv.get(j).time);
        }

        return results;
    }





    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {


        cabItem newsData = new cabItem();
        newsData = results_priv.get(position);

        MainActivity.cab_no = position;
        System.out.println("position : "+position);

        for(int i = 0; i < results_priv.size(); i++) {
            System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCC"  + results_priv.get(i).getdate());
        }



        startActivity(new Intent(v.getContext(), page5.class));
    }



}