package com.example.kumuda.wifidirect1;

import java.io.IOException;

/**
 * Created by kumuda on 1/1/16.
 */

//to remove part of string which does not contain data which is after last comma
public class all_comma {

    public static String remove_allcomma(String line)throws IOException
    {


        int l=0,v=0,m;

        l = line.length();
        char[] s = new char[l];
        s = line.toCharArray();

        String r="";
        String st = "";

        String res = "";

        for(m=0;m<l;m++)
        {
            r = Character.toString(s[m]);
            //System.out.println(r);

            if(r.equals(","))
            {
                v = m;
            }
        }
        res = line.substring(0,v);
        //System.out.println("res");
        res = res+",";
        System.out.println("resssss : "+res);

        return  res;
    }


}
