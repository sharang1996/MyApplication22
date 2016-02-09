package com.test.sharang.myapplication2;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by sharang on 1/17/16.
 */
public class GetMethodEx {

    public String gtInternetData()
    {
        BufferedReader in=null;
        String data=null;
        try{
            HttpClient hpp=new DefaultHttpClient();
            URI website = new URI("http://www.mybringback.com");
            HttpGet request = new HttpGet();
            request.setURI(website);
            HttpResponse response=hpp.execute(request);
            in=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String l ="";
            String nl =System.getProperty("line.separator");
            while((l=in.readLine())!=null)
            {
                sb.append(l+nl);
            }
            in.close();
            data=sb.toString();
            return data;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                    return data;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return data;
    }
}
