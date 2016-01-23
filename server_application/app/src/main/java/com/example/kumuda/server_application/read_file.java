package com.example.kumuda.server_application;


/**
 * Created by kumuda on 15/12/15.
 */


import android.os.Environment;

import java.util.*;
import java.io.*;
import java.io.File;

public  class  read_file {


    public static void decode() {//to get data from wifi text file and to store it in double array

        String input = "",line = "";

        int i = 0 , j = 0,k = 0;

        //input = "/home/kumuda/Desktop/dummy.txt";
        //File input_file = new File(input);

        //opening wifi text file
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


        if (exist){
            System.out.println("File is created!");
        }else{
            // System.out.println("File already exists.");


            try {

                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);

                while( (line = reader.readLine()) != null) {

                    StringTokenizer st = new StringTokenizer(line,",");
                    String[] array = new String[st.countTokens()];

                    k = 0;

                    while (st.hasMoreTokens() )  {
                        array[k] = st.nextToken();
                        k = k+1;
                    }

                    MainActivity.output[i][0] = Integer.toString(i+1);
                    MainActivity.output[i][1] = Integer.toString(k+2);

                    for( j = 0 ; j < k ; j++) {
                        MainActivity.output[i][j+2] = array[j];

                    }


                    i++;
                    MainActivity.no_of_cabs = i;

                }

            }
            catch(Exception e) {
                System.out.println("error 1");
            }

        }


    }



}
