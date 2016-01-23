package com.example.kumuda.wifidirect1;

/**
 * Created by kumuda on 15/12/15.
 */

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
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/*
    To display options page which has options such as insert a cab, view all cabs and update cabs

    IF USER PRESES OK WITH VALID INFORMATION THEN CLIENT WILL OPEN A SOCKET TO SEND THE ENTERED INFORMATION AS A STRING
    TO SERVER USING Connect_client

    TRANSFER OF INFORMATION IS DONE IN ConnectedThread

 */

public class page2 extends Activity {


    public static int no = 0;
    public BluetoothAdapter adapter1 = BluetoothAdapter.getDefaultAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);


    }



    public void insert_cab(View view) {

        startActivity(new Intent(page2.this, page3.class));
    }

    public void view_cabs(View view) {

        startActivity(new Intent(page2.this,page4.class));
    }

    public void update_cablist(View view) {

        discover_device();

    }


    public void discover_device() { // TO CHECK THE PAIRED DEVICES
        System.out.println("entered discover bbbbb");

        ArrayList<String> deviceList = new ArrayList<String>();


        //adapter.startDiscovery();

        System.out.println("entered discover1 bbbbb");

        Set<BluetoothDevice> pairedDevices = adapter1.getBondedDevices();
        no  = pairedDevices.size();

        if (pairedDevices.size() > 0) {


            for (BluetoothDevice device : pairedDevices) {


               // deviceList.add(device.getName() + "\n" + device.getAddress());
               // System.out.println(device.getName() + "\n" +  device.getAddress());

                if ((device.getAddress()).equals("38:2C:4A:1F:E4:AB" ) ) {

                    System.out.println("entered the 38:2C:4A:1F:E4:AB connect");
                    System.out.println("send1");


                    Connect_client ob2 = new Connect_client(device);
                    //  ob2.start();
                    ob2.run();
                    System.out.println("send2");


                }


            }
        }


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
            adapter1.cancelDiscovery();

            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                System.out.println("connecteddddddddd 1");
                mmSocket.connect();
                System.out.println("connecteddddddddd 2");
                //  onActivityResult();

            } catch (IOException connectException) {
                System.out.println("connecteddddddddd 3");
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
            Log.d("ServerThread", "trying to start connection thread bbbbb");
            if (connectionThread!=null) connectionThread.cancel();
            connectionThread = new ConnectedThread(socket);
            System.out.println("entered threaddd bbbb ");
            connectionThread.start();
            connectionThread.run2();
            Log.d("ServerThread", "Finished work on server thread bbbbb ");

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
                System.out.println("got the message bbbb wrong run : " + recieveMessage);
                dummy_file a = new dummy_file();
                a.decode(recieveMessage);
                System.out.println("end");

            } catch (IOException e) {
                // break;
            }
            //  }
        }

        public void run2() {
            byte[] buffer = new byte[4096];  // buffer store for the stream
            int bytes; // bytes returned from read()
            System.out.println("run2 a");
            // Keep listening to the InputStream until an exception occurs
              //  while (true) {
            try {
                // Read from the InputStream
                System.out.println("run2 b");
                bytes = mmInStream.read(buffer);
                System.out.println("run2 c");
                // Send the obtained bytes to the UI activity
                String recieveMessage = new String(buffer);

                all_comma an = new all_comma();
               String recieveMessage1 = an.remove_allcomma(recieveMessage);
                System.out.println("got the message from run 2: " + recieveMessage1);

                copy_to_client_file a = new copy_to_client_file();
                a.decode(recieveMessage1);
                System.out.println("end");
                System.out.println("run2 d");

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