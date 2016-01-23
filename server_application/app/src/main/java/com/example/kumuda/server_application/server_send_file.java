package com.example.kumuda.server_application;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * Created by kumuda on 30/12/15.
 */
public class server_send_file extends Thread{      // //to broadcast server file to clients in the form of string

    public static int no = 0;
    public BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

    public void server_call() {//to discover a client device and to open connection with client

        discover_device();      //descovering devices

        AcceptThread ob = new AcceptThread();       //accepting connection with client
        // ob.start();
        ob.run();

    }




    public void discover_device() {//descovering devices and storing in a list



        System.out.println("entered discover");

        ArrayList<String> deviceList = new ArrayList<String>();


        //adapter.startDiscovery();

        System.out.println("entered discover1");

        Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
        no = pairedDevices.size();

        if (pairedDevices.size() > 0) {


            for (BluetoothDevice device : pairedDevices) {


                deviceList.add(device.getName() + "\n" + device.getAddress());
                System.out.println(device.getName() + "\n" + device.getAddress());
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




                }
            }
        };

    }

    private class AcceptThread extends Thread { //accepting connection with client
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
                System.out.println("entered accept thread run 2");
                try {

                    System.out.println("entered accept thread run 3 ");
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




            System.out.println(MainActivity.server_file_string);

            if (MainActivity.server_file_string != null && !MainActivity.server_file_string.isEmpty()) {
                System.out.println(MainActivity.server_file_string);
                byte[] send = MainActivity.server_file_string.getBytes();
                connectionThread.write(send);
            }
            Log.d("ServerThread", "Finished work on server thread");

            return;



        }

    }


    private class ConnectedThread extends Thread {//to transfer in strings through sockets
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