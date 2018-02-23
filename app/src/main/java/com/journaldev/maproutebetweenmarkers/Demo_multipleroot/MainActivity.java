package com.journaldev.maproutebetweenmarkers.Demo_multipleroot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;



public class MainActivity extends FragmentActivity
{

	GoogleMap map;
	ArrayList<LatLng> markerPoints;
	ArrayList<LatLng> points;
	ArrayList<LatLng> points1;
	private Hashtable<String, String> markers;
	double[] pointa;
	double[] pointb;
	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list=(ListView)findViewById(R.id.listView1);


		markerPoints = new ArrayList<LatLng>();
		points = new ArrayList<LatLng>();
		points1 = new ArrayList<LatLng>();

		SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);

		map = fm.getMap();
		map.setMyLocationEnabled(true);
		
		 double[] pointX;
		 double[] pointY;
		 pointX= new double[4];
		 pointY= new double[4]; 
		 pointa= new double[4];
		 pointb= new double[4];
		
		for(int j=0;j<2;j++)
		{
			
		 for(int i=0;i< pointX.length;i++)
		 { 
			
				if(j==0)
				{
					 if(i==0)
					 {
						 	pointX[i]=23.021750;
							pointY[i]=72.512212;
					 }
					 else if(i==1)
					 {
						 pointX[i]=23.030277;
							pointY[i]=72.507868;
					 }
					 else if(i==2)
					 {
						   pointX[i]=23.007082;
							pointY[i]=72.536981;
					 }
					 else if(i==3)
					 {
							pointX[i]=23.070481;
							pointY[i]=72.623977;
					 }
					 else
					 {
						 pointX[i]=23.012114;
							pointY[i]=72.511186;
					 } 
					 
					 PolylineOptions polylineOptions = new PolylineOptions();
						
							polylineOptions.color(Color.RED);
						
						MarkerOptions markerOptions = new MarkerOptions();
						markerOptions.position(new LatLng(pointX[i],pointY[i]));
						markerOptions.title("Position");
						markerOptions.snippet("Latitude:"+new LatLng(pointX[i],pointY[i]).latitude+","+"Longitude:"+new LatLng(pointX[i],pointY[i]).longitude);
						
						 markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_red));
						 
						 	map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(pointX[i],pointY[i]), 15));
				        	map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
						
						polylineOptions.width(5);
						points.add(new LatLng(pointX[i],pointY[i]));
						polylineOptions.addAll(points);
						map.addPolyline(polylineOptions);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
						map.addMarker(markerOptions);	
				}
				if(j==1)
				{
					 
						 if(i==0)
						 {
							 	pointX[i]=24.0274926; 
								pointY[i]=73.5714283;
						 }
						 else if(i==1)
						 {
								pointX[i]=24.1274926; 
								pointY[i]=73.3714293;
						 }
						 else if(i==2)
						 {
								pointX[i]=24.6274926; 
								pointY[i]=73.4714293;
						 }
						 else if(i==3)
						 {
							 pointX[i]=24.6374926; 
								pointY[i]=73.5714293;
						 }
						 else 
						 {
							 	pointX[i]=24.6574926; 
								pointY[i]=73.5514293;
						 }
							
							
							
							MarkerOptions markerOptions = new MarkerOptions();
							markerOptions.position(new LatLng(pointX[i],pointY[i]));
							markerOptions.title("Position");
							markerOptions.snippet("Latitude:"+new LatLng(pointX[i],pointY[i]).latitude+","+"Longitude:"+new LatLng(pointX[i],pointY[i]).longitude);
							PolylineOptions polylineOptions = new PolylineOptions();
							
							polylineOptions.color(Color.BLUE);
							
							markerOptions.title("Position");
							markerOptions.snippet("Latitude:"+new LatLng(pointX[i],pointY[i]).latitude+","+"Longitude:"+new LatLng(pointX[i],pointY[i]).longitude);
							
							 markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map));
							 
							 	map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(pointX[i],pointY[i]), 15));
					        	map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
							
				
							
							
							polylineOptions.width(5);
							points1.add(new LatLng(pointX[i],pointY[i]));
							polylineOptions.addAll(points1);
							map.addPolyline(polylineOptions);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
							map.addMarker(markerOptions);			
				}
				
					
		 }	
			
		
		 
		
				
				
					
		 }
			list.setOnItemClickListener(new OnItemClickListener() 
			{

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position,
						long id) 
				{
					if(position==1)
					{
						
					}
					
				}
			});
		 Toast.makeText(getApplicationContext(), points1.toString(), 20).show();
		 Log.i("points1.toString()",points1.toString());
		 
		/*map.setOnMapClickListener(new OnMapClickListener()
		{
			
			@Override
			public void onMapClick(LatLng point)
			{
				
				markerPoints.add(point);
				
				MarkerOptions options = new MarkerOptions();
				options.position(point);
				options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				
				map.addMarker(options);			
				
			}
		});
		*/
		
		Toast.makeText(getApplicationContext(), markerPoints.toString(), 20).show();
		Log.i("mark",markerPoints.toString());
		map.setOnMapLongClickListener(new OnMapLongClickListener() {
			
			@Override
			public void onMapLongClick(LatLng point) {
				
				map.clear();
				
				
				markerPoints.clear();
				
			}
		});
		
		
	
	
		
	}
	
	private String getDirectionsUrl(LatLng origin,LatLng dest){
					
		// Origin of route
		String str_origin = "origin="+origin.latitude+","+origin.longitude;
		
		// Destination of route
		String str_dest = "destination="+dest.latitude+","+dest.longitude;		
					
		// Sensor enabled
		String sensor = "sensor=false";			
				
		// Waypoints
		String waypoints = "";
		for(int i=2;i<markerPoints.size();i++){
			LatLng point  = (LatLng) markerPoints.get(i);
			if(i==2)
				waypoints = "waypoints=";
			waypoints += point.latitude + "," + point.longitude + "|";
		}
		
					
		// Building the parameters to the web service
		String parameters = str_origin+"&"+str_dest+"&"+sensor+"&"+waypoints;
					
		// Output format
		String output = "json";
		
		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
		
		
		return url;
	}
	
	/** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
                URL url = new URL(strUrl);

                // Creating an http connection to communicate with url 
                urlConnection = (HttpURLConnection) url.openConnection();

                // Connecting to url 
                urlConnection.connect();

                // Reading data from url 
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb  = new StringBuffer();

                String line = "";
                while( ( line = br.readLine())  != null){
                        sb.append(line);
                }
                
                data = sb.toString();

                br.close();

        }catch(Exception e){

        }finally{
                iStream.close();
                urlConnection.disconnect();
        }
        return data;
     }

	
	
	// Fetches data from url passed
	private class DownloadTask extends AsyncTask<String, Void, String>{			
				
		// Downloading data in non-ui thread
		@Override
		protected String doInBackground(String... url) {
				
			// For storing data from web service
			String data = "";
					
			try{
				// Fetching the data from web service
				data = downloadUrl(url[0]);
			}catch(Exception e){
				Log.d("Background Task",e.toString());
			}
			return data;		
		}
		
		// Executes in UI thread, after the execution of
		// doInBackground()
		@Override
		protected void onPostExecute(String result) {			
			super.onPostExecute(result);			
			
			ParserTask parserTask = new ParserTask();
			
			// Invokes the thread for parsing the JSON data
			parserTask.execute(result);
				
		}		
	}
	
	/** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
    	
    	// Parsing the data in non-ui thread    	
		@Override
		protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
			
			JSONObject jObject;	
			List<List<HashMap<String, String>>> routes = null;			           
            
            try{
            	jObject = new JSONObject(jsonData[0]);
            	DirectionsJSONParser parser = new DirectionsJSONParser();
            	
            	// Starts parsing data
            	routes = parser.parse(jObject);    
            }catch(Exception e){
            	e.printStackTrace();
            }
            return routes;
		}
		
		// Executes in UI thread, after the parsing process
		@Override
		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
			
			ArrayList<LatLng> points = null;
			PolylineOptions lineOptions = null;
			
			// Traversing through all the routes
			for(int i=0;i<result.size();i++){
				points = new ArrayList<LatLng>();
				lineOptions = new PolylineOptions();
				List<HashMap<String, String>> path = result.get(i);
				for(int j=0;j<path.size();j++){
					HashMap<String,String> point = path.get(j);					
					
					double lat = Double.parseDouble(point.get("lat"));
					double lng = Double.parseDouble(point.get("lng"));
					LatLng position = new LatLng(lat, lng);	
					
					points.add(position);						
				}
				
				// Adding all the points in the route to LineOptions
				lineOptions.addAll(points);
				lineOptions.width(6);
				lineOptions.color(Color.RED);				
			}
			
			// Drawing polyline in the Google Map for the i-th route
			map.addPolyline(lineOptions);							
		}			
    }   
    
    

}