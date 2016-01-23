package com.example.kumuda.wifidirect1;

/**
 * Created by kumuda on 1/1/16.
 */

import android.os.Environment;

import java.io.*;
import java.util.*;



public class cab_validation {       //class to check whether a cab can be joined or not

    public static void cab()throws IOException {

        System.out.println("entered cab validation");

        File root = Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/bluetooth");
        dir.mkdirs();
        InputStream in = new FileInputStream(new File(dir, "dummyfile.txt"));
        // File file = new File(dir,"wififile.txt");

        BufferedReader reader1 = new BufferedReader(new InputStreamReader(in));
        String line = "";
        String line1 = "";
        String line2 = "";
        String old = "";
        String z = "";
        String[][] output = new String[100][100];
        int no;
        int[] v = new int[4];
        int[] u = new int[3];

        while ((line1 = reader1.readLine()) != null) {
            old = line1;

        }

        int l = 0, m, n;
        int k = 0, j, i = 0;

        l = old.length();
        char[] s = new char[l];
        s = old.toCharArray();
        System.out.println(s);
        String r = "";
        String st = "";

        String res = "";
        String res1 = "";
        String res2 = "";
        String res3 = "";
        //String res = "";
        n = 0;

        //to get data enterd by user/client and separate into different strings
        for (m = 0; m < l; m++) {
            r = Character.toString(s[m]);
            //System.out.println(r);

            if (r.equals(",")) {
                n++;
                if (n == 1)
                    v[0] = m;
                if (n == 2) {
                    u[0] = v[0] + 1;
                    v[1] = m;
                }
                if (n == 3) {
                    u[1] = v[1] + 1;
                    v[2] = m;
                }

                if (n == 4) {
                    u[2] = v[2] + 1;
                    v[3] = m;
                }
                //break;
            }

        }
        res = old.substring(0, v[0]);
        int z1 = Integer.parseInt(res);
        System.out.println("cab no :"+z1);
        //  System.out.println(z1);
        res1 = old.substring(u[0], v[1]);
        res2 = old.substring(u[1], v[2]);
        res3 = old.substring(u[2], v[3]);
        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);

        //adding data eneterd by user to text file in server app

        File file = new File(dir, "wififile.txt");
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);

        while ((line = reader.readLine()) != null) {

            StringTokenizer st1 = new StringTokenizer(line, ",");
            String[] array = new String[st1.countTokens()];
            // System.out.println("now");
            //  System.out.println(st1.countTokens());

            k = 0;

            while (st1.hasMoreTokens()) {
                array[k] = st1.nextToken();
                //  System.out.println( array[k]);
                k = k + 1;
            }

            output[i][0] = Integer.toString(i + 1);
            output[i][1] = Integer.toString(k + 2);

            for (j = 0; j < k; j++) {
                output[i][j + 2] = array[j];

            }


            i++;

        }

        //to read data from already existing text file in server app
        File file1 = new File(dir, "wififile.txt");
        FileReader fr1 = new FileReader(file1);
        BufferedReader reader2 = new BufferedReader(fr1);
        int t = 1, t1, t2, t3, t4;

        // while ((line = reader2.readLine()) != null) {
        System.out.println("result 1");


        //   if (t == z1) {

        t3 = Integer.parseInt(output[z1 - 1][4]);
        System.out.println("value of t3 : "+t3);
        //  System.out.println(t3);
        t1 = Integer.parseInt(output[z1 - 1][1]);
        t4 = 7 + (t3 * 3);
        if (t1 < t4) {
            System.out.println("value of t4 : "+t4);
            t2 = t1 + 3;
            output[z1 - 1][1] = Integer.toString(t2);
            output[z1 - 1][t1] = res1;
            System.out.println(output[z1-1][t1]);
            output[z1 - 1][t1 + 1] = res2;
            System.out.println(output[z1-1][t1+1]);
            output[z1 - 1][t1 + 2] = res3;
            System.out.println(output[z1-1][t1+2]);
        }

        //updating server app text file
        for (m = 0; m < i; m++) {
            for (j = 2; j < Integer.parseInt(output[m][1]); j++) {

                line2 = line2 + output[m][j] + ",";


            }
            line2 = line2 + "\n";
        }
        System.out.println("value of line");
        System.out.println(line2);

        int b;
        b = line2.length();
        line2 = line2.substring(0,b-1);

        MainActivity.server_file_string = line2;
        System.out.println("shrutiii file :" + MainActivity.server_file_string);

        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.append(line2);


            bufferedWriter.close();
        }catch (Exception e) {
            System.out.println("entered error  writing to wifi");
        }

        //clearing the file in which data eneterd by client was stored.


        File file12 = new File(dir, "dummyfile.txt");
        FileWriter fileWritter = new FileWriter(file12);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.append("");
        bufferWritter.close();

        //creating object of read_file
        read_file ob4 = new read_file();
        read_file.decode();

    }


}