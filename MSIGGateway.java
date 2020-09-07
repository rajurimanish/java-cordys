package com.msig.gateway;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.zip.GZIPOutputStream;

import javax.xml.bind.DatatypeConverter;


import com.cordys.cpc.bsf.soap.SOAPRequestObject;
import com.eibus.localization.message.internal.WebGatewayMessages;
import com.eibus.soap.fault.Fault;
import com.eibus.util.Base64;
import com.eibus.util.cache.exception.CacheLoadingException;
import com.eibus.util.logger.CordysLogger;
import com.eibus.util.logger.Severity;
import com.eibus.util.system.EIBProperties;
import com.eibus.util.system.Native;
import com.eibus.web.gateway.SOAPTransaction;
import com.eibus.web.gateway.interceptor.identity.OsIdentityContext;
import com.eibus.web.isapi.ExtensionControlBlock;
import com.eibus.web.isapi.Request;
import com.eibus.web.isapi.Response;
import com.eibus.web.soap.Gateway;
import com.eibus.xml.nom.Document;
import com.eibus.xml.nom.Node;
import com.eibus.xml.nom.XMLException;
import com.eibus.xml.xpath.XPath;
import com.eibus.xml.xpath.XPathMetaInfo;


@SuppressWarnings("deprecation")
public class MSIGGateway extends Gateway {
	private static Document doc = new Document();
	// private String transactionUser;
	// private String methodName;
	// private String methodNameSpace;
	// private String channel="";
	// private String branchId="";
	// private Long requestStartTime;

	static int securityHeaderNode  = 0;
	private static XPathMetaInfo oMeta = new XPathMetaInfo();
	static final CordysLogger msiggateway_soapLogger = CordysLogger
			.getCordysLogger(MSIGGateway.class);
	static int soapFault = 0;
	static String faultSegment = "<fault xmlns=\"http://schemas.insurance.com/fault/1.0/\"><number>619</number><description>Service Timeout</description><system>CORDYS</system></fault>";

	// Below property is a , separated property. This is to determine what all
	// operations should not transmit data over the network layer.

	static {
		oMeta.addNamespaceBinding(
				"wsse",
				"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
	
		oMeta.addNamespaceBinding("SOAP",
				"http://schemas.xmlsoap.org/soap/envelope/");
		oMeta.addNamespaceBinding("fault",
				"http://schemas.insurance.com/fault/1.0/");
		oMeta.addNamespaceBinding("bpm",
				"http://schemas.cordys.com/bpm/instance/1.0");
		
		String securityString = "<wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">"
		+"<wsse:UsernameToken xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">"
		+"<wsse:Username></wsse:Username>"
      	+ "<wsse:Password></wsse:Password>"
      	+"</wsse:UsernameToken>"
      	+"</wsse:Security>";
		
		try
		{
			securityHeaderNode = doc.parseString(securityString);
				
		}catch (XMLException e) {
			// e.printStackTrace();
			msiggateway_soapLogger.error("Gateway Static Block: Error", e);
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			msiggateway_soapLogger.error("Gateway Static Block: Error", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			msiggateway_soapLogger.error(
					"Gateway Static Block for Property Load: Error", e);
		}
	}

	public MSIGGateway() {
		/*
		 * this.transactionUser = ""; this.channel=""; this.methodName="";
		 * this.methodNameSpace=""; this.branchId="";
		 */
	}

	@Override
	public byte[] getRequestXML(SOAPTransaction soapTransaction) {
		ExtensionControlBlock ecb = soapTransaction.getExtensionControlBlock();
		Request request = ecb.getRequest();
	
		byte[] modifiedRequest = null;
		int originalRequestXML = 0;
		int headerNode = 0;
		int newServiceHeaderNode = 0;

		try {
			originalRequestXML = doc.load(request.binaryRead());
			
			int methodNode = getRequestNode(Node.getRoot(originalRequestXML));
		
		
			int UserNode = XPath.getFirstMatch(
					"SOAP:Header/wsse:Security/UsernameToken/Username", oMeta,
					originalRequestXML);

			String organization = (String) request
					.getParameter("organization")
					.subSequence(
							request.getParameter("organization").indexOf("=") + 1,
							request.getParameter("organization").indexOf(","));
			

			String organizationcontext = request.getParameter("organization");
			if (organization.length() == 0) {
				throw new MSIGGatewayException(
						"The Organization  context is null cannot process the transaction for the user");
			}
		
			headerNode = XPath.getFirstMatch("Header", oMeta,
					originalRequestXML);
			
			if (headerNode == 0) {
				headerNode = Node.createElementWithParentNS("Header",
						"", originalRequestXML);
				Node.insertInChildren(headerNode, headerNode,
						originalRequestXML);
			
			}
			
			int securityHeaderNodeClone  =  Node.clone(securityHeaderNode, true);
			int userNode =  XPath.getFirstMatch("//UsernameToken/Username", oMeta, securityHeaderNodeClone);
			int passNode = XPath.getFirstMatch("//UsernameToken/Password", oMeta, securityHeaderNodeClone);
			int userNameNode =  XPath.getFirstMatch("//username", oMeta, originalRequestXML);
			int passwordNode =  XPath.getFirstMatch("//password", oMeta, originalRequestXML);
		
			String userNameString  = Node.getData(userNameNode);
			String passwordString  = Node.getData(passwordNode);

			Node.setDataElement(userNode, "", userNameString);
			Node.setDataElement(passNode, "", passwordString);

			Node.appendToChildren(securityHeaderNodeClone, headerNode);
			
		
			modifiedRequest = Node.write(originalRequestXML, false);

		
		} catch (XMLException e) {
			msiggateway_soapLogger.log(Severity.FATAL, "Error in loading xml'"
					+ soapTransaction.getExtensionControlBlock()
							.getAssociatedData(Gateway.class));

		} catch (MSIGGatewayException e) {
			soapTransaction
					.raiseSOAPFault(
							Fault.Codes.SERVER,
							500,
							WebGatewayMessages.WG_SOAPTRANSACTION_NOT_ORGANIZATIONAL_USER,
							null, e);
			msiggateway_soapLogger.log(Severity.FATAL, e);
		}finally {
			if (Node.isValidNode(originalRequestXML))
				Node.delete(originalRequestXML);
		}
	
		return modifiedRequest;
	}



	public int getRequestNode(int envelope)// static
	{
		int requestNode = XPath.getFirstMatch(
				"descendant-or-self::soap:Envelope/soap:Body/node()", oMeta,
				envelope);
	
		return requestNode;

	}


	private String compressMessage(String message) throws IOException {
		if (null == message)
			return message;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = null;
		gzip = new GZIPOutputStream(out);
		gzip.write(message.getBytes());
		gzip.close();

		String encodedString = Base64.encodeToStr(out.toByteArray());
		return encodedString;
	}
	private static String getLocale(Request request)
	{
		
		
		String language = request.getParameter("language");
		
	    if (com.eibus.util.Util.isStringEmpty(language)) {
	      language = getLanguageFromBrowser(request);
	    }
	    if (com.eibus.util.Util.isStringEmpty(language)) {
	      return EIBProperties.get_SystemDefaultLocale().toString();
	    }
	    return com.eibus.web.util.Util.getLocaleFromString(language).toString();
	}
	private static String getLanguageFromBrowser(Request request)
	  {
	    String language = request.getServerVariable("accept-language");
	    if (language != null)
	    {
	      int firstDelimiter = language.indexOf(',');
	      if (firstDelimiter != -1) {
	        language = language.substring(0, firstDelimiter);
	      }
	    }
	    return language;
	  }
}