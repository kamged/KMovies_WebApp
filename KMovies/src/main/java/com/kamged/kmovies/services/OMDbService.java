package com.kamged.kmovies.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamged.kmovies.models.Movie;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public final class OMDbService {

    private static String jsonResult;
    private static String _url = "https://www.omdbapi.com/";
    private static String APIKEY = "xxxxxxxx";
    //private static OMDbService _instance;

    private OMDbService() { }

    /*public static OMDbService getInstance() {
        if(_instance == null)
            _instance = new OMDbService();

        return _instance;
    }*/

    private static String getOMDbResult(String title, String year) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(buildRESTUrl(title, year));
        try {
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            HttpEntity res = response1.getEntity();
            jsonResult = EntityUtils.toString(res);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return jsonResult;
    }

    public static String[] OMDbRequest(String title, String year) {
        String json = getOMDbResult(title, year);
        System.out.println(json);
        JSONParser jp = new JSONParser();
        String[] movieDetails = {};

        try {
            Object o = jp.parse(json);
            JSONObject jo = (JSONObject)o;


            if(jo.get("Response").toString().equals("True"))
                movieDetails = new String[]{jo.get("Response").toString(), jo.get("Type").toString(), jo.get("Poster").toString()};
            else
                movieDetails = new String[]{"False"};

        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }

        return movieDetails;
    }

    private static String buildRESTUrl(String par1, String par2) {
        StringBuilder sb = new StringBuilder();

        sb.append(_url);
        sb.append("?apikey=");
        sb.append(APIKEY);
        sb.append("&t=");
        sb.append(par1.replace(" ", "%20"));
        sb.append("&y=");
        sb.append(par2);

        return sb.toString();
    }
}
