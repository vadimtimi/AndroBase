package com.refresh.pos.telegabot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class clsTelegram {
    private final int CONNECTION_TIMEOUT = 5000;
    private String token;
    public clsTelegram(String token_) {
        this.token = token_;
    }

    public void postRequest() throws Exception{
        final URL url = new URL("http://jsonplaceholder.typicode.com/posts");
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setConnectTimeout(CONNECTION_TIMEOUT);
        con.setReadTimeout(CONNECTION_TIMEOUT);

        // collect parameters
        final Map<String, String> parameters = new HashMap<>();
        parameters.put("title", "foo");
        parameters.put("body", "bar");
        parameters.put("userId", "1");

        con.setDoOutput(true);
        final DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(getParamsString(parameters));
        out.flush();
        out.close();

        final String content = readInputStream(con);
        con.disconnect();

        System.out.println(content);
    }

    public  void getRequest() throws Exception{
        final URL url = new URL("http://jsonplaceholder.typicode.com/posts?_limit=10");
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(CONNECTION_TIMEOUT);
        con.setReadTimeout(CONNECTION_TIMEOUT);

        final String content = readInputStream(con);
        con.disconnect();

        System.out.println(content);
    }

    public String readInputStream(final HttpURLConnection con){
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public String getParamsString(final Map<String, String> params) {
        final StringBuilder result = new StringBuilder();


        for (Map.Entry<String, String> entry : params.entrySet()) {
            try {
                result.append( URLEncoder.encode( entry.getKey(), "UTF-8" ) );
                result.append( '=' );
                result.append( URLEncoder.encode( entry.getValue(), "UTF-8" ) );
                result.append( '&' );
            }catch (final UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

//        params.forEach((name, value) -> {
//            try {
//                result.append(URLEncoder.encode(name, "UTF-8"));
//                result.append('=');
//                result.append(URLEncoder.encode(value, "UTF-8"));
//                result.append('&');
//            } catch (final UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        });

        final String resultString = result.toString();
        return !resultString.isEmpty()
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
