package com.msig.bmsintegrationapp;

import java.io.UnsupportedEncodingException;

import com.cordys.cpc.bsf.busobject.BSF;
import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.eibus.util.logger.CordysLogger;
import com.eibus.xml.nom.XMLException;

public class ExtraTextTransformation extends ExtraTextTransformationBase {
	
	public static final String LINE_SEPARATOR = "\\r?\\n";
	public static final int EXTRATEXT_LINE_LENGTH = 60;
	public static final int ECLUSIONTXT_LINE_LENGHT = 60;
	
	static final CordysLogger cordysLogger = CordysLogger.getCordysLogger(ExtraTextTransformation.class);
	
    public ExtraTextTransformation() {
    	this((BusObjectConfig)null);
    }
    public ExtraTextTransformation(BusObjectConfig config) {
    	super(config);
    }
    public static int exclusionPageDT(String extraText)throws XMLException, UnsupportedEncodingException {
    	cordysLogger.debug("EXCLUSION TEXT:: "+extraText);
    	
    	String GENXml = "<GEN>";
		String GENPLNESXml = "";
		//String SEQNOGPSXml = "<SEQNOGPS>";
		if(extraText  != null && extraText != "" ){
			//String[] etParagraphsArr= extraText.split(System.getProperty(LINE_SEPARATOR));
			String[] etParagraphsArr= extraText.split(LINE_SEPARATOR);
			
			int itr = 1;

			for(String etParagraph : etParagraphsArr) {
				
				if(etParagraph.length() > ECLUSIONTXT_LINE_LENGHT ) {
					String str = etParagraph;
					while( str.length() > ECLUSIONTXT_LINE_LENGHT ){
						
						String str1 = str.substring(0, ECLUSIONTXT_LINE_LENGHT);
						String str2 = str.substring(ECLUSIONTXT_LINE_LENGHT);
						
						if( !str1.endsWith(" ") && !str2.startsWith(" ") && str1.lastIndexOf(" ") != -1) {
							
							str1 = str1.substring(0, str1.lastIndexOf(" "));
							String temp_str = str.substring(str1.length());
							if(temp_str.startsWith(" ")) {
								str = str.substring(str1.length()+1);
							} else { 
								str = temp_str;
							}
						}
						else
							str = str2;
		
						if(str1.indexOf("^") >=0){
							str1 = xmlEscapeText(str1);
						}
						else if(("").equals(str1.trim())){
							str1 = ".";
						}
						
						GENPLNESXml += "<SFL><GENPLNE><![CDATA["+str1+"]]></GENPLNE></SFL>";
						itr ++;
					}
					if(str != "" && str.length() <= ECLUSIONTXT_LINE_LENGHT) {

						if(str.indexOf("^") >=0){
							str = xmlEscapeText(str);
						}
						else if(("").equals(str.trim())){
							str = ".";
						}
						
						GENPLNESXml += "<SFL><GENPLNE><![CDATA["+str+"]]></GENPLNE></SFL>";
						itr ++;
					}
				}
				else if(("").equals(etParagraph.trim())){
				
					GENPLNESXml += "<SFL><GENPLNE>.</GENPLNE></SFL>";
					itr++;
				}
				else {
				
					if(etParagraph.indexOf("^") >=0){
						etParagraph = xmlEscapeText(etParagraph);
					}
					else if(("").equals(etParagraph.trim())){
						etParagraph = ".";
					}
						
					GENPLNESXml += "<SFL><GENPLNE><![CDATA["+etParagraph+"]]></GENPLNE></SFL>";
					itr++;
				}
			}
		}    	
		GENPLNESXml += "";
		
		GENXml += GENPLNESXml;
		
		GENXml += "</GEN>";
		
		cordysLogger.debug("Exclusion Text:: "+GENXml);
		
		try {
			return BSF.getXMLDocument().parseString(GENXml);
		} catch(XMLException e){
			throw new XMLException("Exclusion Text XMLEXCEPTION :: GENXML -- "+GENXml+"  :: "+e.getMessage());
		} catch(UnsupportedEncodingException e){
			throw new UnsupportedEncodingException("UnsupportedEncodingException ::GENXML -- "+GENXml+" ::"+e.getMessage());			
		}	
				
		
    }

    public static int coverageExtraTextDT(String extraText) throws XMLException, UnsupportedEncodingException {
    	cordysLogger.debug("EXTRA TEXT:: "+extraText);
    	
    	/*if( extraText != null &&  extraText.indexOf("^") >=0)
    		extraText = extraText.replaceAll("^", " ");*/
    	
    	String GENXml = "<GEN>";
		String GENPLNESXml = "<GENPLNES>";
		String SEQNOGPSXml = "<SEQNOGPS>";
		
		if(extraText  != null && extraText != "" ){
			//String[] etParagraphsArr= extraText.split(System.getProperty(LINE_SEPARATOR));
			String[] etParagraphsArr= extraText.split(LINE_SEPARATOR);
			
			int itr = 1;

			for(String etParagraph : etParagraphsArr) {
				
				if(etParagraph.length() > EXTRATEXT_LINE_LENGTH ) {
					String str = etParagraph;
					while( str.length() > EXTRATEXT_LINE_LENGTH ){
						
						String str1 = str.substring(0, EXTRATEXT_LINE_LENGTH);
						String str2 = str.substring(EXTRATEXT_LINE_LENGTH);
						
						if( !str1.endsWith(" ") && !str2.startsWith(" ") && str1.lastIndexOf(" ") != -1) {
							
							//str1 = str1.substring(0, str1.lastIndexOf(" ")).trim();
							//str = str.substring(str1.length()).trim();
							
							str1 = str1.substring(0, str1.lastIndexOf(" "));
							String temp_str = str.substring(str1.length());
							if(temp_str.startsWith(" ")) {
								str = str.substring(str1.length()+1);
							} else { 
								str = temp_str;
							}
						}
						else {
							//str = str2.trim();
							str = str2;
						}
		
						if(str1.indexOf("^") >=0){
							str1 = xmlEscapeText(str1);
						}
						else if(("").equals(str1.trim())){
							str1 = ".";
						}
						
						GENPLNESXml += "<GENPLNE><![CDATA["+str1+"]]></GENPLNE>";
						SEQNOGPSXml += "<SEQNOGP>"+(itr)+"</SEQNOGP>";
						itr ++;
					}
					if(str != "" && str.length() <= EXTRATEXT_LINE_LENGTH) {

						if(str.indexOf("^") >=0){
							str = xmlEscapeText(str);
						}
						else if(("").equals(str.trim())){
							str = ".";
						}
						
						GENPLNESXml += "<GENPLNE><![CDATA["+str+"]]></GENPLNE>";
						SEQNOGPSXml += "<SEQNOGP>"+(itr)+"</SEQNOGP>";
						itr ++;
					}
				}
				else if(("").equals(etParagraph.trim())){
				
					GENPLNESXml += "<GENPLNE>.</GENPLNE>";
					SEQNOGPSXml += "<SEQNOGP>"+(itr)+"</SEQNOGP>";
					itr++;
				}
				else {
				
					if(etParagraph.indexOf("^") >=0){
						etParagraph = xmlEscapeText(etParagraph);
					}
					else if(("").equals(etParagraph.trim())){
						etParagraph = ".";
					}
						
					GENPLNESXml += "<GENPLNE><![CDATA["+etParagraph+"]]></GENPLNE>";
					SEQNOGPSXml += "<SEQNOGP>"+(itr)+"</SEQNOGP>";
					itr++;
				}
			}
		}
        
		SEQNOGPSXml += "</SEQNOGPS>";
		GENPLNESXml += "</GENPLNES>";
		
		GENXml += SEQNOGPSXml;
		GENXml += GENPLNESXml;
		
		GENXml += "</GEN>";
		
		cordysLogger.debug("EXTRA TEXT:: "+GENXml);
		
		try {
			
			return BSF.getXMLDocument().parseString(GENXml);
		} catch(XMLException e){
			throw new XMLException("XMLEXCEPTION :: GENXML -- "+GENXml+"  :: "+e.getMessage());
		} catch(UnsupportedEncodingException e){
			throw new UnsupportedEncodingException("UnsupportedEncodingException ::GENXML -- "+GENXml+" ::"+e.getMessage());			
		}	
		
        //return (int) 0;
    }

    public static BusObjectIterator<com.msig.bmsintegrationapp.ExtraTextTransformation> getExtraTextTransformationObjects(com.cordys.cpc.bsf.query.Cursor cursor)
    {
        // TODO implement body
        return null;
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

    public static String xmlEscapeText(String t) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < t.length(); i++){
			char c = t.charAt(i);
			switch(c){
				/*case '<': sb.append("&lt;"); break;
				case '>': sb.append("&gt;"); break;
				case '\"': sb.append("&quot;"); break;
				case '&': sb.append("&amp;"); break;
				case '\'': sb.append("&apos;"); break;*/
				case '^': sb.append(" "); break;
				default:					
					sb.append(c);
			}
		}
		
		return sb.toString();
	}
	
}
