package com.example.kumuda.wifidirect1;

/**
 * Created by kumuda on 30/12/15.
 */

import android.os.Environment;

import java.io.*;
import java.util.*;


public class dummy_to_wifi { //to copy string from dummy file to wifi text file in server app

    public static void copy_delete()throws IOException {
        File root = Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/bluetooth");
        dir.mkdirs();
        InputStream in = new FileInputStream(new File(dir, "dummyfile.txt"));
        // File file = new File(dir,"wififile.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        String line1 = "";
        String old = "";
        String z = "";
        int no;
        System.out.println("enyered dummy to wifi: ");

        //getting data from dummy file

        while ((line = reader.readLine()) != null) {
            old = line;
            System.out.println("after dummy to wifi : " + old);


        }

        //adding data from dummy file to wifi text file to the corresponding cab in wifi text file

        System.out.println("entered to wifi no : ");
        no = 0;
        StringTokenizer st = new StringTokenizer(old, ",");
        no = st.countTokens();

        System.out.println(no);
        System.out.println(old);

        if (no > 6) {

            comma ab = new comma();
            try {
                old = ab.trim(old);
            } catch (Exception e) {

            }
            System.out.println(old);

            String data = old + "\n";

            File file11 = new File(dir, "wififile.txt");
            boolean exist = true;
           /* try {
                exist = file.createNewFile();
            } catch (Exception e) {

            }*/
            //writing to wifi text file

            System.out.println("heyyyyyyyyyyyyyyyyyy : " + data);
            FileWriter fileWritter = new FileWriter(file11, true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.append(data);
            bufferWritter.close();

            //getting data of wifi text file into a string
            InputStream in1 = new FileInputStream(new File(dir, "wififile.txt"));
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(in1));
            while ((line1 = reader1.readLine()) != null) {
                z = z + line1 + "\n";
                //  System.out.println("here");
            }
            int l;
            l = z.length();
            //    z = z.substring(0, l - 1);
            MainActivity.server_file_string = z;
            System.out.println("shrutiii file :" + MainActivity.server_file_string);

            //clearing dummy file
            File file12 = new File(dir, "dummyfile.txt");
            FileWriter fileWritter2 = new FileWriter(file12);
            BufferedWriter bufferWritter2 = new BufferedWriter(fileWritter2);
            bufferWritter2.append("");
            bufferWritter2.close();

            System.out.println("shrutiii file :" + MainActivity.server_file_string);
        }

        else {
            cab_validation i = new cab_validation();
            i.cab();
        }
    }



}
