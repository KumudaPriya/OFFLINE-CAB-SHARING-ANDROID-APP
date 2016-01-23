package com.example.kumuda.wifidirect1;

/**
 * Created by kumuda on 15/12/15.
 */
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class page3 extends Activity { //to display page that takes data of user for entering  new cab


    public static int no = 0;
    public BluetoothAdapter adapter3 = BluetoothAdapter.getDefaultAdapter();
    public static String regexDDMMYYYY="^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
    public static final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    private Pattern pattern;
    private Matcher matcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert1);

     /*  BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
       int  no  = pairedDevices.size();

        for(int i = 0 ; i<no ; i++) {
            onActivityResult();
            System.out.println("printinggg " + i);
        } */


    }


    public  void onActivityResult () {

        System.out.println("entereddddddddddddddd result123");



        File root = Environment.getExternalStorageDirectory();
        File dir = new File (root.getAbsolutePath() + "/bluetooth");
        dir.mkdirs();
        System.out.println(root.getAbsolutePath() + "/bluetooth");
        File file = new File(dir,"wififile.txt");



        Intent intent1 = new Intent();
        intent1.setAction(Intent.ACTION_SEND);
        intent1.setType("text/plain");
        intent1.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file) );

        PackageManager pm = getPackageManager();
        List appsList = pm.queryIntentActivities( intent1, 0);

        if(appsList.size() > 0 ){

            System.out.println("entereddddddddddddddd result123aaaaaaaaaaaaaaaa");
            startActivity(intent1);
            System.out.println("entereddddddddddddddd result123aaaaaaaaaaaaaaaa");
        }



    }


    public static boolean isAValidDDMMYYYYDate(String date) {
        return Pattern.matches(regexDDMMYYYY, date);
    }

    public boolean validate_time(final String time){

        pattern = Pattern.compile(TIME24HOURS_PATTERN);
        matcher = pattern.matcher(time);
        return matcher.matches();

    }




    public void go_to_options(View view) {

        EditText  mEdit1   = (EditText)findViewById(R.id.editText1);
        EditText  mEdit2   = (EditText)findViewById(R.id.editText2);
        EditText  mEdit3   = (EditText)findViewById(R.id.editText3);
        EditText  mEdit4   = (EditText)findViewById(R.id.editText4);
        EditText  mEdit5   = (EditText)findViewById(R.id.editText5);
        EditText  mEdit6   = (EditText)findViewById(R.id.editText6);
        EditText  mEdit7   = (EditText)findViewById(R.id.editText7);
        EditText  mEdit8   = (EditText)findViewById(R.id.editText8);
        EditText  mEdit9   = (EditText)findViewById(R.id.editText9);
        EditText  mEdit10   = (EditText)findViewById(R.id.editText10);

        String mEdit1_trim = mEdit1.getText().toString().trim();
        String mEdit2_trim = mEdit2.getText().toString().trim();
        String mEdit3_trim = mEdit3.getText().toString().trim();
        String mEdit4_trim = mEdit4.getText().toString().trim();
        String mEdit5_trim = mEdit5.getText().toString().trim();
        String mEdit6_trim = mEdit6.getText().toString().trim();
        String mEdit7_trim = mEdit7.getText().toString().trim();
        String mEdit8_trim = mEdit8.getText().toString().trim();
        String mEdit9_trim = mEdit9.getText().toString().trim();
        String mEdit10_trim = mEdit10.getText().toString().trim();

        if (!(mEdit1_trim.matches(""))&&!(mEdit2_trim.matches(""))&&!(mEdit3_trim.matches(""))&&!(mEdit4_trim.matches(""))&&!(mEdit5_trim.matches(""))&&!(mEdit6_trim.matches(""))&&!(mEdit7_trim.matches(""))&&!(mEdit8_trim.matches(""))&&!(mEdit9_trim.matches(""))&&!(mEdit10_trim.matches("")) ) {


            if (isAValidDDMMYYYYDate(mEdit1_trim)   && validate_time(mEdit2_trim) ) {


                MainActivity.newcab[0][0] = mEdit1.getText().toString();
                MainActivity.newcab[0][1] = mEdit2.getText().toString();
                MainActivity.newcab[0][2] = mEdit3.getText().toString();
                MainActivity.newcab[0][3] = mEdit4.getText().toString();
                MainActivity.newcab[0][4] = mEdit5.getText().toString();
                MainActivity.newcab[0][5] = mEdit6.getText().toString();
                MainActivity.newcab[0][6] = mEdit7.getText().toString();
                MainActivity.newcab[0][7] = mEdit8.getText().toString();
                MainActivity.newcab[0][8] = mEdit9.getText().toString();
                MainActivity.newcab[0][9] = mEdit10.getText().toString();


                for (int i = 0; i < 10; i++)
                    MainActivity.insert_new_person_string = MainActivity.insert_new_person_string + MainActivity.newcab[0][i] + ",";

                // Toast.makeText(this, MainActivity.insert_new_person_string, Toast.LENGTH_SHORT).show();
                discover_device();

                startActivity(new Intent(page3.this, page2.class));
                // onActivityResult();


            }

            else if((!isAValidDDMMYYYYDate(mEdit1_trim))   && (!validate_time(mEdit2_trim)) ) {
                Toast.makeText(this, "Enter date in 20/07/1982 format and time in 7:30 format ", Toast.LENGTH_SHORT).show();
            }

            else if(!validate_time(mEdit2_trim)) {
                Toast.makeText(this, "Enter time in 7:30 format ", Toast.LENGTH_SHORT).show();
            }

            else if(!isAValidDDMMYYYYDate(mEdit1_trim)) {
                Toast.makeText(this, "Enter date in 20/07/1982 format ", Toast.LENGTH_SHORT).show();
            }

        }
        else {
            Toast.makeText(this, "Enter all the fields", Toast.LENGTH_SHORT).show();
        }




      //  onActivityResult();
    }

    public void go_to_options_a1(View view) {
        startActivity(new Intent(page3.this, page2.class));
    }


    public void discover_device() {
        System.out.println("entered discover");

        ArrayList<String> deviceList = new ArrayList<String>();


        //adapter.startDiscovery();

        System.out.println("entered discover1");

        Set<BluetoothDevice> pairedDevices = adapter3.getBondedDevices();
        no  = pairedDevices.size();

        if (pairedDevices.size() > 0) {


            for (BluetoothDevice device : pairedDevices) {


               // deviceList.add(device.getName() + "\n" + device.getAddress());
               // System.out.println(device.getName() + "\n" +  device.getAddress());

                if( (device.getAddress() ).equals ("38:2C:4A:1F:E4:AB" ) ) {

                    System.out.println("entered the 38:2C:4A:1F:E4:AB connect  aaaaaaaaa");
                    Connect_client ob2 = new Connect_client(device);
                    ob2.run();

                    // adapter3.cancelDiscovery();
                    System.out.println("cancelled discovery  aaaaaaaaa");
                    return;
                }
            }
        }

      /*  adapter3.startDiscovery();

        BroadcastReceiver mReceiver = new BroadcastReceiver() {

            ArrayList<String> deviceList1 = new ArrayList<String>();
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                // When discovery finds a device

                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    // Get the BluetoothDevice object from the Intent

                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                    deviceList1.add(device.getName() + "\n" + device.getAddress());

                  //  System.out.println(device.getName() + "\n" + device.getAddress());

                    if( (device.getAddress() ).equals ("38:2C:4A:1F:E4:AB" ) ) {

                        System.out.println("entered the 38:2C:4A:1F:E4:AB connect  aaaaaaaaa");
                        Connect_client ob2 = new Connect_client(device);
                        ob2.run();

                       // adapter3.cancelDiscovery();
                        System.out.println("cancelled discovery  aaaaaaaaa");
                        return;
                    }



                }
            }
        };

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);*/
    }



    public class Connect_client extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        private ConnectedThread connectionThread;
        UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

        public Connect_client(BluetoothDevice device) {

            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            mmDevice = device;

            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                // MY_UUID is the app's UUID string, also used by the server code
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);

            } catch (IOException e) { }
            mmSocket = tmp;
        }

        public void run() {
            adapter3.cancelDiscovery();

            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                mmSocket.connect();
                //  onActivityResult();

            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out
                try {
                    mmSocket.close();

                } catch (IOException closeException) { }
                return;
            }

            // Do work to manage the connection (in a separate thread)
            manageConnectedSocket(mmSocket);
        }

        /** Will cancel an in-progress connection, and close the socket */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }

        private void manageConnectedSocket(BluetoothSocket socket) {
            Log.d("ServerThread", "trying to start connection thread");
            if (connectionThread!=null) connectionThread.cancel();
            connectionThread = new ConnectedThread(socket);
            System.out.println("entered threaddd aaaaaa");
            connectionThread.start();
            //    String msg = "Hi ! I am there ";

            System.out.println("entered threaddd aaaaaa ccccccc");

            System.out.println(MainActivity.insert_new_person_string);

            if (MainActivity.insert_new_person_string != null && !MainActivity.insert_new_person_string.isEmpty()) {
                System.out.println(MainActivity.insert_new_person_string);
                byte[] send = MainActivity.insert_new_person_string.getBytes();
                connectionThread.write(send);
            }
            MainActivity.insert_new_person_string = "";
            Log.d("ServerThread", "Finished work on server thread aaaa");

            if (connectionThread!=null) connectionThread.cancel();

            return;

        }

    }

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            //    while (true) {
            try {
                // Read from the InputStream
                bytes = mmInStream.read(buffer);
                // Send the obtained bytes to the UI activity
                String recieveMessage = new String(buffer);
                System.out.println("got the message : " + recieveMessage);
                dummy_file a = new dummy_file();
                a.decode(recieveMessage);
                System.out.println("end");

            } catch (IOException e) {
                // break;
            }
            //  }
        }


        public void run2() {
            byte[] buffer = new byte[2048];  // buffer store for the stream
            int bytes; // bytes returned from read()
            System.out.println("run2 a");
            // Keep listening to the InputStream until an exception occurs
              while (true) {
            try {
                // Read from the InputStream
                System.out.println("run2 b");
                bytes = mmInStream.read(buffer);
                System.out.println("run2 c");
                // Send the obtained bytes to the UI activity
                String recieveMessage = new String(buffer);
                System.out.println("got the message : " + recieveMessage);
                copy_to_client_file a = new copy_to_client_file();
                a.decode(recieveMessage);
                System.out.println("end");
                System.out.println("run2 d");
                break;

            } catch (IOException e) {
                // break;
            }

             }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) { }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }






}