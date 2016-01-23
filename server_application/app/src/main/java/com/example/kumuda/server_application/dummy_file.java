package com.example.kumuda.server_application;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class dummy_file {       //to create a dummy file in server app that is used to get data entered by user which is added later to wifi text file

    public static void decode(String msg) {

        String input = "",line = "";

        int i = 0 , j = 0,k = 0;
    //creating a dummy file
        //input = "/home/kumuda/Desktop/dummy.txt";
        //File input_file = new File(input);
        System.out.println("Entered read fileeeeeeeeeeeeeeee");
        File root = Environment.getExternalStorageDirectory();
        File dir = new File (root.getAbsolutePath() + "/bluetooth");
        dir.mkdirs();

        File file = new File(dir,"dummyfile.txt");
        boolean exist = true;

        // remove_file ob7 = new remove_file();
        //  ob7.remove();



        try {
            exist = file.createNewFile();
        }catch (Exception e) {

        }

    //writing string eneterd by user into dummy file


        try {

            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.append(msg);

            bufferedWriter.close();

        }


        catch(Exception e) {
            System.out.println("error 1");
        }




    }



}

