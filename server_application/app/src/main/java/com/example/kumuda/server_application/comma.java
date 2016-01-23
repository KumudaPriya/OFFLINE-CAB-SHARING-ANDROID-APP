package com.example.kumuda.server_application;

import java.io.IOException;


public class comma { //to remove the extra part of declared string which does not contain any data of client

    public static String trim(String line)throws IOException
    {


        int l,v,m, n;

        l = line.length();
        char[] s = new char[l];
        s = line.toCharArray();

        String r="";
        String st = "";

        n=0;

        String res = "";

        for(m=0;m<l;m++)
        {
            r = Character.toString(s[m]);
            //System.out.println(r);

            if(r.equals(","))
                n++;

            st = Integer.toString(n);

            //System.out.println(st);
            //System.out.println("heyy");

            if(st.equals("10"))
            {
                //System.out.println(m);
                res = line.substring(0,m);//getting part of string that has various fields enterd by user
                //System.out.println("res");
                //System.out.println(res);
                break;
            }

        }
        res = res + ",";        //adding comma at the end of updated string
        System.out.println(res);
        return res;
    }



}
