package com.example.kumuda.wifidirect1;

/**
 * Created by kumuda on 15/12/15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class page5 extends Activity { //to display a page that shows details of a cab


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cab_details);

        System.out.println("Entered read fileeeeeeeeeeeeeeee");
        read_file ob6 = new read_file();
        read_file.decode();
        System.out.println("Entered read fileeeeeeeeeeeeeeee");

        TextView date1 = (TextView) findViewById (R.id.editText1_cabdetails);
        TextView time1 = (TextView) findViewById (R.id.editText2_cabdetails);
        TextView capacity1 = (TextView) findViewById (R.id.editText3_cabdetails);
        TextView destination1 = (TextView) findViewById (R.id.editText4_cabdetails);
        TextView from1 = (TextView) findViewById (R.id.editText5_cabdetails);
        TextView note1 = (TextView) findViewById (R.id.editText6_cabdetails);
        TextView total1 = (TextView) findViewById (R.id.editText7_cabdetails);

        date1.setText(MainActivity.output[MainActivity.cab_no][2]);
        time1.setText(MainActivity.output[MainActivity.cab_no][3]);
        capacity1.setText(MainActivity.output[MainActivity.cab_no][4]);
        destination1.setText(MainActivity.output[MainActivity.cab_no][5]);
        from1.setText(MainActivity.output[MainActivity.cab_no][6]);
        note1.setText(MainActivity.output[MainActivity.cab_no][7]);
        total1.setText(MainActivity.output[MainActivity.cab_no][8]);



    }

    public void go_to_partnerlist(View view) {

        startActivity(new Intent(page5.this, page5a.class));

    }

    public void go_to_options_a(View view) {
        startActivity(new Intent(page5.this, page2.class));
    }



    public void go_to_addpartner(View view) {

        startActivity(new Intent(page5.this, page6.class));
    }




}