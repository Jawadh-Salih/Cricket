package com.example.jawadh.cricket.Other.Controller;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Connection {

    private String baseURL = "http://192.168.43.185/cricky";//43.185/cricky/";//http://criclub.esy.es/Server_API_1.0/ up to now
    private static Connection conn = new Connection();

    private Connection(){}

    public static Connection getInstance(){
        if(conn == null){
            conn = new Connection();
        }
        return conn;
    }
    public String getBaseURL() {
        return baseURL;
    }
    public URI getURI(String file) throws URISyntaxException {
        if (baseURL.charAt(baseURL.length() - 1) != '/') {
            return new URI(baseURL + "/" + file);
        }
        return new URI(baseURL + file);
    }

    // get method for communication
    public String get(String file, HashMap<String, String> para) throws URISyntaxException, IOException {

        //HttpParams paras = new BasicHttpParams();

        if (para != null) {
            file += "?";
            Iterator<String> keys = para.keySet().iterator();
            while (keys.hasNext()) {
                String key = keys.next();
                file += (key + "=" + para.get(key) + "&");
                //paras.setParameter(key, para.get(key));
            }
        }
        URI uri = getURI(file);


        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(uri);
        //get.setURI(uri);
        //get.setParams(paras);

        HttpResponse response = client.execute(get);//IO exception
        InputStream stream = response.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line, data = "";
        while ((line = reader.readLine()) != null) {
            data += line;
        }
        return data;
    }
    // post method for communication
    public String post(String file, HashMap<String, String> para) throws URISyntaxException, IOException {


        List<NameValuePair> paras = new ArrayList<>();

        if (para != null) {

            Iterator<String> keys = para.keySet().iterator();

            while (keys.hasNext()) {
                String key = keys.next();
                paras.add(new BasicNameValuePair(key, para.get(key)));
            }
        }
        URI uri = getURI(file);


        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(uri);
        post.setEntity(new UrlEncodedFormEntity(paras));


        HttpResponse response = client.execute(post);//IO exception
        InputStream stream = response.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line, data = "";
        while ((line = reader.readLine()) != null) {
            data += line;
        }
        Log.d("response", data);
        return data;
    }

}
