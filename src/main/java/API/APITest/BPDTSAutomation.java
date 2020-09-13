package API.APITest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;


public class BPDTSAutomation {

	public static Property props = new Property("Config.properties");
	public static String apiServiceURL=props.getProperty("service");
	public static String endPointUsers=props.getProperty("endPointUsers");
	public static String endPointCityUsers=props.getProperty("endPointCityUsers");
	public static String endPointUsersByID=props.getProperty("endPointUsersByID");
	
	/**
	 * This method used to get the response from the request for the end point.
	 * @param endPoint
	 * @throws Throwable
	 */
	public static void GetMethod(String endPoint) throws Throwable {
		try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

            HttpUriRequest request = new HttpGet(apiServiceURL+endPoint);
            HttpResponse response = client.execute(request);

            BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = bufReader.readLine()) != null) {
                
                builder.append(line);
                builder.append(System.lineSeparator());
            }

            System.out.println(builder);
	}
	}
	
	/**
	 * This method used to get the response from the request for the end point.
	 * @param endPoint
	 * @throws IOException
	 */
	public static void MyGETRequest(String endPoint) throws IOException {
	    URL urlForGetRequest = new URL(apiServiceURL+endPoint);
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET");
	    int responseCode = conection.getResponseCode();
	    System.out.println("Response Code is: " + responseCode);
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        StringBuffer response = new StringBuffer();
	        while ((readLine = in .readLine()) != null) {
	            response.append(readLine);
	        } in .close();
	        // print result
	        System.out.println("JSON String Result " + response.toString());
	        
	    } else {
	        System.out.println("Unable to get response from Get for end point : "+endPoint);
	    }
	}

	public static void main(String ar[]) throws Throwable {
		System.out.println("Solution 1 Results : ");
		GetMethod(endPointUsers);
		GetMethod(endPointCityUsers);
		GetMethod(endPointUsersByID);
		
		System.out.println("Solution 2 Results : ");
		MyGETRequest(endPointUsers);
		MyGETRequest(endPointCityUsers);
		MyGETRequest(endPointUsersByID);
	}
}
