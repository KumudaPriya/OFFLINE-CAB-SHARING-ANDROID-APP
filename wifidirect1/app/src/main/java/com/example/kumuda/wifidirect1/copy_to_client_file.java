package com.example.kumuda.wifidirect1;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by kumuda on 30/12/15.
 */

//To copy the string sent by server theough socket into wifi text fileof user

public class copy_to_client_file {

    public static void decode(String msg) {

        String input = "",line = "";

        int i = 0 , j = 0,k = 0;

        //input = "/home/kumuda/Desktop/dummy.txt";
        //File input_file = new File(input);
        System.out.println("Entered read fileeeeeeeeeeeeeeee");
        File root = Environment.getExternalStorageDirectory();
        File dir = new File (root.getAbsolutePath() + "/bluetooth");
        dir.mkdirs();

        File file = new File(dir,"wififile.txt");
        boolean exist = true;





        try {
            exist = file.createNewFile();
        }catch (Exception e) {

        }




        try {

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.append(msg);

            bufferedWriter.close();

        }


        catch(Exception e) {
            System.out.println("error 1");
        }




    }


}
