package com.example.kumuda.server_application;


/****************************
    MAIN ACTIVITY FOR SERVER APPLICATION
    IT WILL RECIEVE INFORMATION AND BRODCASTS THE RECIEVERD INFORMATION TO ALL USERS
 ****************************/

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;



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

    // our request code (must be greater than zero)
    private static final int REQUEST_BLU = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*************************
            THE BELOW CODE IS TO SET INFORMATION FOR THE ANIMATION AT THE BEGINING OF THE APP
         *************************/

        Thread timer = new Thread() {
            public void run() {
                try {

                    sleep(2500);
                } catch (InterruptedException e)

                {
                    e.printStackTrace();
                } finally {

                  //  READ_FILE JAVA CLASS IS TO READ THE TXT FILE
                    read_file ob1 = new read_file();
                    read_file.decode();



                    startActivity(new Intent(MainActivity.this, page2.class));

                    enableBlu();

                    while(true) {       // RECIEVING FROM INFORMATION AND SENDING TO CLIENTS IS SET IN WHILE LOOP
                        discover_device();  //DISCOVERING NEARBY DEVICES
                        AcceptThread ob = new AcceptThread();   //OPENING A CONNECTION TO RECIEVE INFORMATION
                        ob.start();
                        ob.run();

                       //  for(int j = 0 ; j<(adapter.getBondedDevices()).size() ;j++) {
                        server_send_file b = new server_send_file();    //OPENING A CONNECTION TO BRODCAST
                        b.start();
                        b.server_call();
                        // }

                            startActivity(new Intent(MainActivity.this, page2.class));

                    }


                }
            }
        };
        timer.start();


    }









    public void enableBlu(){
        // THIS WILL ENABLE BLUETOOTH AUTOMATICALLY WHEN SERVER APP IS STARTED

        Intent discoveryIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);

        discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
                0 );

        startActivityForResult(discoveryIntent, REQUEST_BLU);
    }


    public void discover_device() {
        System.out.println("entered discover");

        ArrayList<String> deviceList = new ArrayList<String>();


        //adapter.startDiscovery();

        System.out.println("entered discover1");

        Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
        no  = pairedDevices.size();

        if (pairedDevices.size() > 0) {


            for (BluetoothDevice device : pairedDevices) {


                deviceList.add(device.getName() + "\n" + device.getAddress());
                System.out.println(device.getName() + "\n" +  device.getAddress());
            }
        }

        adapter.startDiscovery();

        BroadcastReceiver mReceiver = new BroadcastReceiver() {

            ArrayList<String> deviceList1 = new ArrayList<String>();
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                // When discovery finds a device

                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    // Get the BluetoothDevice object from the Intent

                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                    deviceList1.add(device.getName() + "\n" + device.getAddress());

                    System.out.println(device.getName() + "\n" + device.getAddress());

                     /*   AcceptThread ob = new AcceptThread();
                        ob.run();*/



                }
            }
        };

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
    }


    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;
        private ConnectedThread connectionThread;
        UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

        public AcceptThread() {
            System.out.println("entered accept thread");
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code

                tmp = adapter.listenUsingRfcommWithServiceRecord("wifidirect1", MY_UUID);

            } catch (IOException e) { }
            mmServerSocket = tmp;
        }

        public void run() {
            System.out.println("entered accept thread run ");
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    socket = mmServerSocket.accept();

                    // if (socket == null) {
                    // Do work to manage the connection (in a separate thread)

                    manageConnectedSocket(socket);
                    try {
                        mmServerSocket.close();
                        break;
                    }catch (Exception e){

                    }

                    //  }
                    //   break;
                } catch (IOException e) {

                    break;
                }
                // If a connection was accepted

            }
        }

        /** Will cancel the listening socket, and cause the thread to finish */
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) { }
        }

        private void manageConnectedSocket(BluetoothSocket socket) {
            Log.d("ServerThread", "trying to start connection thread");


            if (connectionThread!=null) connectionThread.cancel();
            connectionThread  = new ConnectedThread(socket);
            System.out.println("entered threaddd");
            connectionThread.start();
            System.out.println("entered start");
            connectionThread.run();
            Log.d("ServerThread", "Finished work on server thread");

            if (connectionThread!=null) connectionThread.cancel();

            return ;





          /*  Log.d("ServerThread", "trying to start connection thread2");


            if (connectionThread!=null) connectionThread.cancel();
            connectionThread  = new ConnectedThread(socket);
            System.out.println("entered threaddd2");
            connectionThread.start();
            System.out.println("entered start2");
            System.out.println(MainActivity.server_file_string);

            if (MainActivity.server_file_string != null && !MainActivity.server_file_string.isEmpty()) {
                System.out.println(MainActivity.server_file_string);
                byte[] send = MainActivity.server_file_string.getBytes();
                connectionThread.write(send);
            }
            Log.d("ServerThread", "Finished work on server thread2");*/


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
                System.out.println("before dummy to wifi: ");

                dummy_to_wifi ob = new dummy_to_wifi();
                ob.copy_delete();


                  /*
                     cab_validation i = new cab_validation();
                    i.cab();*/

                System.out.println("after dummy to wifi: ");

                  /*  server_send_file b = new server_send_file();
                    b.start();
                    b.server_call();*/



                System.out.println("end");

            } catch (IOException e) {
                // break;
            }
            //  }
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