package com.opentext.integration.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import com.cordys.cpc.bsf.busobject.BSF;
import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.eibus.util.logger.CordysLogger;
import com.eibus.util.system.EIBProperties;
import com.eibus.xml.nom.Node;
import com.eibus.xml.xpath.XPath;
import com.opentext.otds.client.OtdsRichClient;

@SuppressWarnings("unused")
public class Authenticator extends AuthenticatorBase {
	
	private static String CORDYS_GATEWAY_URL = null;
	
	private static CordysLogger logger = CordysLogger.getCordysLogger(Authenticator.class);
	
	public static int authenticate(String userName, String password)throws Exception {
		return getSAMLArtifact(getOTDSTicket(userName, password), userName);
	}

	
	/* Login to OTDS with given Username and Password and generate OTDS ticket for Cordys authentication purpose */
	private static String getOTDSTicket(String userName, String password)throws Exception {
		OTDSIntegration.getOTDSConfigurations();
		String otdsServerURL = OTDSIntegration.getOTDS_URL(); // "http://srv-ind-ncb3l.vanenburg.com:8080/"
		String otdsCordysResourceId = OTDSIntegration.getOTDS_RESOURCE_ID();

		if (otdsServerURL == null || otdsCordysResourceId == null) {
			OTDSIntegration.getOTDSConfigurations();
			otdsServerURL = OTDSIntegration.getOTDS_URL();
			otdsCordysResourceId = OTDSIntegration.getOTDS_RESOURCE_ID();
		}

		OtdsRichClient richOtdsClient = new OtdsRichClient(otdsServerURL);
		richOtdsClient.loginWithPassword(userName, password);
		return richOtdsClient.requestTicketForResource(otdsCordysResourceId);
	}

	@SuppressWarnings("deprecation")
	/* 
	 * Generating the SAMLart using the OTDS ticket generated before
	 * 
	 * */
	private static int getSAMLArtifact(String otdsTicket, String userName)throws Exception {

		if (CORDYS_GATEWAY_URL == null)
			CORDYS_GATEWAY_URL = "http://" + (InetAddress.getLocalHost()).getCanonicalHostName() + ":" + EIBProperties.getProperty("web.server.portnumber") + "/cordys/com.eibus.web.soap.Gateway.wcp?organization=" + URLEncoder.encode(BSF.getOrganization()) + "&messageOptions=0";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	    String utcTime = sdf.format(new Date());

		String strSAML_SOAP_Request = "<SOAP:Envelope xmlns:SOAP=\"http://schemas.xmlsoap.org/soap/envelope/\">"
			 + "<SOAP:Header>"
			 + "<OTAuthentication  xmlns=\"urn:api.ecm.opentext.com\">"
			 + "<AuthenticationToken>" + otdsTicket + "</AuthenticationToken>"
			 + "</OTAuthentication>"
			 + "</SOAP:Header>"
			 + "<SOAP:Body>"
			 + "<samlp:Request xmlns:samlp=\"urn:oasis:names:tc:SAML:1.0:protocol\" IssueInstant=\"" + utcTime + "\" MajorVersion=\"1\" MinorVersion=\"1\" RequestID=\"CRD-" + UUID.randomUUID().toString() + "\">"
			 + "<samlp:AuthenticationQuery>"
			 + "<saml:Subject xmlns:saml=\"urn:oasis:names:tc:SAML:1.0:assertion\">"
			 + "<saml:NameIdentifier Format=\"urn:oasis:names:tc:SAML:1.1:nameid-format:unspecified\">" + userName + "</saml:NameIdentifier>"
			 + "</saml:Subject>"
			 + "</samlp:AuthenticationQuery>"
			 + "</samlp:Request>" + "</SOAP:Body>" + "</SOAP:Envelope>";
		
		URL u = new URL(CORDYS_GATEWAY_URL);
		HttpURLConnection httpurlconn = (HttpURLConnection)u.openConnection();
		httpurlconn.setRequestMethod("POST");
		httpurlconn.setDoOutput(true);
		httpurlconn.setDoInput(true);
		OutputStream os = httpurlconn.getOutputStream();
		os.write(strSAML_SOAP_Request.getBytes());
		os.close();

		if (httpurlconn.getResponseCode() != 200) {
			throw new Exception(httpurlconn.getResponseCode() + "-" + httpurlconn.getResponseMessage());
		}

		InputStreamReader isr = new InputStreamReader(httpurlconn.getInputStream());
		BufferedReader in = new BufferedReader(isr);

		String strLine = "";
		String strSAML_SOAP_Response = "";
		while ((strLine = in.readLine()) != null) {
			strSAML_SOAP_Response = strLine;
		}
		in.close();

		int xResponseNode = 0;
		try {
			xResponseNode = BSF.getXMLDocument().parseString(strSAML_SOAP_Response);
			int xFault = XPath.getFirstMatch(".//Fault", null, xResponseNode);
			if (xFault != 0) {
				throw new Exception(Node.getData(XPath.getFirstMatch(".//faultstring", null, xFault)));
			}
			return Node.clone(XPath.getFirstMatch(".//Response", null, xResponseNode), true);
		}
		finally {
			if (Node.isValidNode(xResponseNode)) {
				Node.delete (xResponseNode);
				xResponseNode = 0;
			}
		}
	}
	
	public Authenticator()
    {
        this((BusObjectConfig)null);
    }

    public Authenticator(BusObjectConfig config)
    {
        super(config);
    }
    
    public void onInsert()
    {
    }

    public void onUpdate()
    {
    }

    public void onDelete()
    {
    }
}