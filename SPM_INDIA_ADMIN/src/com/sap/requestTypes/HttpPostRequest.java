

package com.sap.requestTypes;

//

import java.io.IOException;

import java.io.OutputStreamWriter;
import java.net.URL;


import javax.net.ssl.HttpsURLConnection;

import com.sap.requestTypes.QueryParams;
import com.sap.model.PropertiesData;

import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class HttpPostRequest extends Thread {
	
	private final String URLPARAM = "&$expand=" + QueryParams.PERSONNAV + "&$select=" + QueryParams.FIRSTNAME + ","
			+ QueryParams.LASTNAME + "," + QueryParams.PERSONNAV + "/" + QueryParams.DATEOFBIRTH + ","
			+ QueryParams.PERSONNAV + "/" + QueryParams.COUNTRYOFBIRTH;

	/**
	 * this variable represents the absolute Url
	 */
	private String absoluteUrl;
	
	private String body;
	/**
	 * this variable represents an uiReference so the fetched data can be parsed
	 * to the output
	 */
	//private ODataRequestUserInterface uiReference;
	/**
	 * this variable is a PropertiesData object which storages the result of the
	 * call
	 */
	private PropertiesData attributeData;

	private String personIdExternal;

	private String result;
	
	String encodedMessage = null;
	
	public HttpPostRequest(String body) {
	
		absoluteUrl = QueryParams.POSTSERVICEURL;
		
		this.body = body;
		
		System.out.println(absoluteUrl);
	}
	
	
	public String getBody() {
		return this.body;
	}

	/**
	 * this method represents the overwritten run method of the class Thread. It
	 * is launched by the start() method. This method fetches the Data, the
	 * terminates the progress bar and finally writes the result on the ui
	 */
	@Override
	public void run() {

		try {
			postData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getResult(){
	    return result;
	  }

	
	private void postData() throws IOException {	
		
		URL absU = new URL(absoluteUrl);		
		
	try{
		TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
    };

    // Install the all-trusting trust manager
    SSLContext sc = SSLContext.getInstance(QueryParams.SSL);
    sc.init(null, trustAllCerts, new java.security.SecureRandom());
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

    // Create all-trusting host name verifier
    HostnameVerifier allHostsValid = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    // Install the all-trusting host verifier
    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        String encoding = new sun.misc.BASE64Encoder().encode((QueryParams.USER + ":" + QueryParams.PW).getBytes());
		String code = "Basic " + encoding;
		HttpsURLConnection con = (HttpsURLConnection) absU.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		con.setRequestProperty("Authorization", code);
		con.setDoOutput(true);
		
		String message = this.getBody();
		
		if (message != null) {
			
			message = message.replaceAll("\\r\\n", "");
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(message);
			wr.flush();
			System.out.println("Message inside Post:" + message);
			// Get the response
			System.err.println(con.getResponseCode());
			result = "Response Status: " + con.getResponseCode();
			wr.close();
			}
		
		System.out.println(con.getResponseCode());
		
		
	 }catch(Exception e){
		 System.out.println(e.getMessage());
	 }	
	}

}
