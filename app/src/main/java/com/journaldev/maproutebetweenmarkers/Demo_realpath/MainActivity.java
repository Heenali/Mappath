package com.journaldev.maproutebetweenmarkers.Demo_realpath;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GoogleMap googleMap;
    public Polyline line, line1;
    boolean islineFlag = false;
    public static String direction_key = "AIzaSyDDih9M2ANQtlOZRfSRW53rpJ5TXvZ7nqk";  // live API key for distace & direction
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

        googleMap.setMyLocationEnabled(false);// Showing / hiding your current location

        googleMap.getUiSettings().setAllGesturesEnabled(false);

        HashMap<String, String> map = new HashMap<>();
        ArrayList<HashMap<String, String>> LatLongWaypointList = new ArrayList<>();
        for (int i = 1; i < 5 ; i++)
        {


           if(i==1)
           {
               map.put("truck_lat", "23.027050");
               map.put("truck_lng", "72.508613");
           }
            if(i==2)
            {
                map.put("truck_lat", "23.027445");
                map.put("truck_lng", "72.515777");
            }
            if(i==3)
            {
                map.put("truck_lat", "23.023654");
                map.put("truck_lng", "72.533860");
            }
            if(i==4)
            {
                map.put("truck_lat", "23.021205");
                map.put("truck_lng", "72.551884");
            }
            LatLongWaypointList.add(map);

        }
        String urlfollowed = getDirectionsUrlWayPoints(new LatLng(23.030727, 72.508111), new LatLng(23.014095, 72.562858), LatLongWaypointList);
        DownloadTask1 downloadTask1 = new DownloadTask1();
        downloadTask1.execute(urlfollowed);
        final LatLngBounds bounds;
        final LatLng lat_lng1 = new LatLng(23.030727, 72.508111);
        final LatLng lat_lng2 = new LatLng(23.014095, 72.562858);


        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        boundsBuilder.include(lat_lng1);
        boundsBuilder.include(lat_lng2);
        bounds = boundsBuilder.build();

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 30));
            }
        });


    }
    private String getDirectionsUrlWayPoints(LatLng origin, LatLng dest, ArrayList<HashMap<String, String>> waypointlist) {

        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String sensor = "sensor=false";
        String waypoints = "";
        waypoints = "waypoints=";
        // waypoints = "waypoints=23.0192808,72.5189742|23.0211166,72.51610359999995";
        if (waypointlist.size() > 0) {
            for (int i = 0; i < waypointlist.size(); i++) {
                waypoints += waypointlist.get(i).get("truck_lat") + "," + waypointlist.get(i).get("truck_lng") + "|";
            }
        }
        // waypoints += point.latitude + "," + point.longitude + "|";
        String key = "key=" + direction_key;
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + waypoints + "&" + key;
        String output = "json";

        String url = "https://maps.googleapis.com/maps/api/directions/json?" + parameters;
        Log.e("direction url", url);
        return url;
    }
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            // Log.d("Exception url", "" + e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
    private class DownloadTask1 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                // Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {

                ParserTask1 parserTask1 = new ParserTask1();

                // Invokes the thread for parsing the JSON data
                parserTask1.execute(result);
            } catch (Exception e) {

            }

        }
    }
    private class ParserTask1 extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            try {
                ArrayList<LatLng> points = null;
                PolylineOptions lineOptions = null;
                MarkerOptions markerOptions = new MarkerOptions();

                // Traversing through all the routes
                if (result != null) {
                    if (result.size() > 0) {

                        for (int i = 0; i < result.size(); i++) {
                            points = new ArrayList<LatLng>();
                            lineOptions = new PolylineOptions();

                            // Fetching i-th route
                            List<HashMap<String, String>> path = result.get(i);

                            // Fetching all the points in i-th route
                            for (int j = 0; j < path.size(); j++) {
                                HashMap<String, String> point = path.get(j);

                                double lat = Double.parseDouble(point.get("lat"));
                                double lng = Double.parseDouble(point.get("lng"));
                                LatLng position = new LatLng(lat, lng);

                                points.add(position);
                            }

                            // Adding all the points in the route to LineOptions
                            lineOptions.addAll(points);
                            if (!islineFlag) {
                                lineOptions.width(15);
                                lineOptions.color(Color.parseColor("#000000")).geodesic(true);
                                islineFlag = true;
                            } else {
                                lineOptions.width(15);
                                lineOptions.color(Color.parseColor("#000000")).geodesic(true);
                            }
                            //lineOptions.color(Color.parseColor("#59c1e3")).geodesic(true);
                        }
                        line = googleMap.addPolyline(lineOptions);    // Drawing polyline in the Google Map for the i-th route
                    }
                }
            } catch (Exception e)
            {

            }
        }
    }

}
