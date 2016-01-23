package com.example.kumuda.wifidirect1;

/**
 * Created by kumuda on 15/12/15.
 */
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by likki on 11/16/15.
 */
public class page5a extends ListActivity { //to display the page that conatins details of partner

    private ArrayList<personItem> results_priv = new ArrayList<personItem>();

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        System.out.println("enterd1");
        ArrayList details = getListData();
        System.out.println("enterd2");
        CustomListAdapter1 adapter = new CustomListAdapter1(this, details);
        System.out.println("enterd3");
        setListAdapter(adapter);
        System.out.println("enterd4");

    }

    public class personItem {

        private String name;
        private String degree;
        private String mobile;


        public String getname() {
            return name;
        }

        public void setname(String name) {
            this.name = name;
        }

        public String getdegree() {
            return degree;
        }

        public void setdegree(String degree) {
            this.degree = degree;
        }

        public String getmobile() {
            return mobile;
        }

        public void setmobile(String mobile) {
            this.mobile = mobile;
        }


    }

    private ArrayList getListData() {

        int k, present;
        int m = Integer.parseInt(MainActivity.output[MainActivity.cab_no][1]);
        k = ( m - 9 ) / 3;
        present = 9;


       /* String[][] a = new String[k][l];

        a[0][0] = "ajit";
        a[0][1] = "b.tech";
        a[0][2] = "1234567890";

        a[1][0] = "seema";
        a[1][1] = "m.sc";
        a[1][2] = "8593017597";

        a[2][0] = "riya";
        a[2][1] = "m.des";
        a[2][2] = "7891224567";

        a[3][0] = "sparsh";
        a[3][1] = "m.tech";
        a[3][2] = "9225669101";*/

        System.out.println("Entered read fileeeeeeeeeeeeeeee");
        read_file ob6 = new read_file();
        read_file.decode();
        System.out.println("Entered read fileeeeeeeeeeeeeeee");


        String[][] a = new String[k][3];
        for(int i = 0 ; i<k ; i++ ) {
                for(int j=0 ; j<3;j++) {
                    a[i][j] = MainActivity.output[MainActivity.cab_no][present];
                    System.out.println("EEEEEEEEEEEEEEEEE : " + a[i][j]);
                    present++;
                }
        }


        ArrayList<personItem> results = new ArrayList<personItem>();


        for(int i=0;i<k;i++) {

            TextView name1 = (TextView) findViewById (R.id.editText1_cabdetails);
            personItem newsData =  new personItem();

            newsData.setname(a[i][0]);
            newsData.setdegree(a[i][1]);
            newsData.setmobile(a[i][2]);
            results.add(newsData);

        }

        results_priv = (ArrayList<personItem>)results.clone();
        for(int j = 0; j < results_priv.size(); j++) {

            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDD"  + results_priv.get(j).name);
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDD"  + results_priv.get(j).degree);
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDD"  + results_priv.get(j).mobile);
        }

        return results;
    }







}