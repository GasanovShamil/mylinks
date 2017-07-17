package mylinks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.json.Json;

import org.json.JSONObject;

public class IpInfo {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		
		InputStreamReader in = null;
		
			URL u;
			try {
				u = new URL("http://freegeoip.net/json/77.154.202.102");
				HttpURLConnection.setFollowRedirects(true);
				HttpURLConnection huc = (HttpURLConnection) u.openConnection();
				huc.setRequestMethod("GET");
				huc.connect();
				in = new InputStreamReader(huc.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
				in.close();
				System.out.println(sb);
				JSONObject jsonObj = new JSONObject(sb.toString());
				System.out.println(jsonObj.getString("country_name"));
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		

	}

}
