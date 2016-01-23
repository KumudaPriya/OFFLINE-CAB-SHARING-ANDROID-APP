package com.example.kumuda.server_application;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;

/**
 * Created by kumuda on 6/1/16.
 */
public class page2 extends Activity {


    public static int no = 0;
    public BluetoothAdapter adapter1 = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.server_working);



    }

}
