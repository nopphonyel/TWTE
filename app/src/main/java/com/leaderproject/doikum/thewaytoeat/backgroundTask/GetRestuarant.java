package com.leaderproject.doikum.thewaytoeat.backgroundTask;

import android.os.AsyncTask;
import android.util.Log;

import com.leaderproject.doikum.thewaytoeat.ProgramStaticContent;
import com.leaderproject.doikum.thewaytoeat.fragment.RandomResultFragment;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by NR7 on 7/15/2016.
 */
public class GetRestuarant extends AsyncTask<String, String, String> {

    public static final String NOT_FOUND = "Not Found";
    public static final String FOUND = "Found";
    public static final String CONNECTION_SUCCESS = "CONNECT_SUCCESS";
    public static final String CONNECTION_FAILED = "CONNECT_FAILED : ";
    public static final String FIRST_TIME = "First";

    private boolean isFirstTime = false;

    @Override
    protected String doInBackground(String... params) {
        try {
            // 2 --- connection part * Dont EDIT !!!
            URL url = new URL("http://dlitsource.com/leadership/filter.php"); //url to server
            String urlParameters = "filter=" + params[0]; // parameter to server
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.d("TAG_CHECK_FILTER", urlParameters);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));

            conn.setUseCaches(false);
            //Send Parameter part * Dont EDIT !!!!
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            // 3 --- Read text from server
            StringBuilder str = new StringBuilder();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                str.append(line);
            }

            conn.disconnect();
            // 4 -- แสดงผล อิอิ พิมพ์ mytag ตรงช่อง logcat เพื่อดู
            Log.d("mytag", str.toString());

            if (params.length == 2 && params[1].equals(FIRST_TIME)) {
                isFirstTime = true;
            } else {
                isFirstTime = false;
            }
            return str.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.d("mytag", "Failed : " + e.toString());
            return CONNECTION_FAILED;
        }
    }

    @Override
    protected void onPostExecute(String fetchedData) {
        ProgramStaticContent.setRestaurantObjectFromString(fetchedData);
        if (isFirstTime) RandomResultFragment.updateContent(FIRST_TIME);
        else RandomResultFragment.updateContent(FOUND);
    }
}
