package com.example.kumuda.wifidirect1;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import com.example.kumuda.wifidirect1.R;
import android.bluetooth.BluetoothAdapter;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/*********************
 * MAIN ACTIVITY OF CLIENT APP
 * IF CLIENT WANTS TO INSERT A NEW CAB HE SHOULD GO TO PAGE3 AND PRESS OK BUTTON , THIS WILL SEND
 * MESSAGE STRING TO SERVER
 *
 * SIMILARLY IF USER WANTS JOIN CAB HE SHOULD GO TO PAGE 6 AND PRESS OK BUTTON , THIS WILL SEND
 * MESSAGE STRING TO SERVER
 *
 * AFTER INSERTING OR JOINING CAB HE SHOULD PRESS UPDATE LIST OPTION IN PAGE 2 TO SEETHE UPDATED LIST OF CABS
 *
 * USER WILL OPEN A SOCKET CONNECT CONNECTION WHEN HE WANTS TO SEND OR RECIEVE INFORMATION FROM SERVER
 * I.E IN PAGE 2 , 3 , 6
 *
 */

public class MainActivity extends Activity {

    public static int cab_no = 0;
    public static  String[][] output = new String[50][40];
    public static int no_of_cabs = 0;
    public static  String[][] newcab = new String[1][10];
    public static  String[][] newperson= new String[1][3];
    public static int no = 0;
    public static int a = 0;
    public static String insert_new_person_string = "";
    public static String cab_new_person_string = "";
    public static String server_file_string = "";
    public BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

    private static final int DISCOVER_DURATION = 300;
    private static final int REQUEST_BLU = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            BELOW CODE IS FOR THE ANIMATION AT THE BEGINNING OF THE APP
         */

        Thread timer = new Thread() {
            public void run() {
                try {

                    sleep(2500);
                } catch (InterruptedException e)

                {
                    e.printStackTrace();
                } finally {


                    read_file ob1 = new read_file();
                    read_file.decode(); // TO READ THE WIFI TEXT FILE AND TO DIAPLAY THE PRESENT CABS



                    startActivity(new Intent(MainActivity.this, page2.class));

                    enableBlu();

                }
            }
        };
        timer.start();


    }


    public void enableBlu(){        // TO ENABLE BLUETOOTH AUTOMATICALLY AFTER OPENING THE APP


        Intent discoveryIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);

        discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
                0 );

        startActivityForResult(discoveryIntent, REQUEST_BLU);
    }


}