package com.example.kumuda.wifidirect1;

import java.io.IOException;

/**
 * Created by kumuda on 1/1/16.
 */
public class comma { //to remove part of the string that is after last comma

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
                res = line.substring(0,m);
                //System.out.println("res");
                //System.out.println(res);
                break;
            }

        }
        res = res + ",";
        System.out.println(res);
        return res;
    }



}
