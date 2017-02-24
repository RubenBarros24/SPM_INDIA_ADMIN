

package com.sap.requestTypes;

//
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import com.sap.requestTypes.QueryParams;


import com.sap.model.JSONObject;
import com.sap.model.PropertiesData;
import com.sap.model.XML;

import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class HttpRequest extends Thread {
	
	private final String URLPARAM = "&$expand=" + QueryParams.PERSONNAV + "&$select=" + QueryParams.FIRSTNAME + ","
			+ QueryParams.LASTNAME + "," + QueryParams.PERSONNAV + "/" + QueryParams.DATEOFBIRTH + ","
			+ QueryParams.PERSONNAV + "/" + QueryParams.COUNTRYOFBIRTH;

	/**
	 * this variable represents the absolute Url
	 */
	private String absoluteUrl;
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

	private String result = " ";
	
	public HttpRequest(String personId, String target, String startDate, Boolean startDateFlag) {
		personIdExternal = personId;
		//uiReference = ui;
		attributeData = new PropertiesData(personId, target, startDate, startDateFlag);
		switch(target){
		
			case "PerPersonal":
				absoluteUrl = attributeData.getPerPersonalDest();
			break;
			
			case "PayComp":
				absoluteUrl = attributeData.getPayCompDest();
			break;
			
			case "EmpJob":
				absoluteUrl = attributeData.getEmpJobDest();
			break;
			
			case "EmpEmployment":
				absoluteUrl = attributeData.getEmpEmploymentDest();
			break;
			
			case "EmpCompensation":
				absoluteUrl = attributeData.getEmpCompensationDest();
			break;
			
			case "PerPerson":
				absoluteUrl = attributeData.getPerPersonDest();
			break;
			
			case "PerEmail":
				absoluteUrl = attributeData.getPerEmailDest();
			break;
				
		default: break;
		}
		
		System.out.println(absoluteUrl);
	}

	/**
	 * this method represents the overwritten run method of the class Thread. It
	 * is launched by the start() method. This method fetches the Data, the
	 * terminates the progress bar and finally writes the result on the ui
	 */
	@Override
	public void run() {

		fetchData();
		
		//uiReference.endBar();
		//uiReference.setUiData(attributeData);
	}
	
	public String getResult(){
	    return result;
	  }

	
	private void fetchData() {
		try {
			BufferedReader in = buildConn();
			result = parseData(in);
		} catch (WrongPersonIdException e) {
			//uiReference.unvalidUserIdError(personIdExternal, attributeData);
		} catch (IOException ie) {
			//uiReference.serverOverstrainedError();
			ie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private BufferedReader buildConn() throws IOException {	
		
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
		con.setRequestMethod("GET");
		con.setRequestProperty("Authorization", code);
		System.err.println(con.getResponseCode());
		return new BufferedReader(new InputStreamReader(con.getInputStream()));
		
	 }catch(Exception e){
		 System.out.println(e.getMessage());
		 return null;
	 }	
	}

	
	private String parseData(BufferedReader in) throws Exception {
		StringBuffer response = new StringBuffer();
		String line;
		while ((line = in.readLine()) != null) {
			response.append(line);
		}

		String xml = response.toString();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		JSONObject xmlJSONObj = XML.toJSONObject(xml);
		System.out.println(xmlJSONObj.toString());
	    /*
		if (xml.contains("entry") == false) {
			throw new WrongPersonIdException();
		} else {
			getInfo(doc, xpath, "/feed/entry/content/properties/firstName", QueryParams.FIRSTNAME);
			getInfo(doc, xpath, "/feed/entry/content/properties/lastName", QueryParams.LASTNAME);
			getInfo(doc, xpath, "/feed/entry/link/inline/entry/content/properties/countryOfBirth",
					QueryParams.COUNTRYOFBIRTH);
			getInfo(doc, xpath, "/feed/entry/link/inline/entry/content/properties/dateOfBirth", QueryParams.DATEOFBIRTH);
		}
		*/
		return xmlJSONObj.toString();
	}

	private void getInfo(Document doc, XPath xpath, String path, String key) throws XPathExpressionException,
			WrongPersonIdException {
		XPathExpression exp = xpath.compile(path);
		String value = (String) exp.evaluate(doc, XPathConstants.STRING);
		attributeData.setNodeValue(key, value);
		if (QueryParams.DATEOFBIRTH.equals(key)) {
			attributeData.setNodeValue(key, value.replaceAll("T00:00:00", ""));
		}
	}
}
