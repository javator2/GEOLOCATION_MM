package pl.sdacademy.geolokacja;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("What place you want to know?");
        Scanner scanner = new Scanner(System.in);
        String place = scanner.nextLine();
        scanner.close();
        String encoderUrl = null;
        try{
            encoderUrl = URLEncoder.encode(place,"UTF-8");
        } catch (UnsupportedEncodingException ignored){ }

        JSONObject json = null;
        try {
            json = new JSONObject(IOUtils.toString(new URL("https://maps.googleapis.com/maps/api/geocode/json?address="
                    + encoderUrl
                    + "&key=AIzaSyA7hMn0BBzFxBcFtn-QYPe4p_bCjlJRf3w"), Charset.forName("UTF-8")));


        } catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println("Hey, "
                + place
                + " is place with latitude: "
                + json.getJSONArray("results").getJSONObject(Integer.parseInt("0")).getJSONObject("geometry").getJSONObject("location").get("lat")
                + ", and longitude: "
                + json.getJSONArray("results").getJSONObject(Integer.parseInt("0")).getJSONObject("geometry").getJSONObject("location").get("lng")
        );

    }
}
