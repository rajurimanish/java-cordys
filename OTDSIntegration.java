/*
This class has been generated by the Code Generator
 */

package com.opentext.integration.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cordys.cpc.bsf.busobject.BSF;
import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.soap.SOAPRequestObject;
import com.eibus.util.logger.CordysLogger;
import com.eibus.xml.nom.Node;
import com.eibus.xml.nom.XMLException;
import com.eibus.xml.xpath.XPath;
import com.opentext.otds.OtdsException;
import com.opentext.otds.client.OtdsAdminClient;
import com.opentext.otds.client.OtdsPartition;
import com.opentext.otds.client.OtdsUser;
import com.opentext.otds.ws.v2.usergroup.Attribute;

@SuppressWarnings("unused")
public class OTDSIntegration extends OTDSIntegrationBase {

	private OtdsAdminClient otdsAdminClient;
	private static CordysLogger logger = CordysLogger.getCordysLogger(OTDSIntegration.class);

	private static String OTDS_URL; // "http://myvmsdevbpmdb.my.msig.com:9999/";
	private static String OTDS_ADMIN_USER; // "otadmin@otds.admin";
	private static String OTDS_ADMIN_PASSWORD; // "admin";
	private static String OTDS_USER_PARTITION; // "MSIG_DEV_PARTITION";
	private static String OTDS_RESOURCE_ID; // "aca6a703-9763-40fb-9aee-a65c3353221f";
	private static String OTDS_RESOURCE_NAME;
	private static String AUTHENTICATION_URL;
	private static String CONSOLIDATION_URL;
	private static String IS_PASSWORD_RESET_REQUIRED;
	private static String DEFAULT_PASSWORD;
	private static String OTDS_LOG_LOCATION;
	private static String ENFORCE_COMPLEX_PASSWORD;
	private static String[] SPECIAL_CHARS = {"~", "!","&", "#", "$", "%", "^"};


	private static OtdsAdminClient getOTDSAdminClient() throws Exception {
		if (OTDS_URL == null || OTDS_ADMIN_USER == null
				|| OTDS_ADMIN_PASSWORD == null || OTDS_USER_PARTITION == null || OTDS_RESOURCE_NAME == null) {
			getOTDSConfigurations();
		}
		return _getOTDSAdminClient(getOTDS_URL(), getOTDS_ADMIN_USER(), getOTDS_ADMIN_PASSWORD());
	}

	private static OtdsAdminClient _getOTDSAdminClient(String serverURL, String userName, String password) throws Exception {
		try {
			return new OtdsAdminClient(serverURL, userName, password);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private static OtdsUser getOTDSUser(String userId) throws Exception {
		OtdsUser otdsUser = null;
		OtdsAdminClient otdsAdminClient = getOTDSAdminClient();

		try {
			otdsUser = otdsAdminClient.getUser(userId);
		} catch (OtdsException e) {
			// Nothing here
		} finally {
			if (otdsAdminClient != null) {
				otdsAdminClient.close();
				otdsAdminClient = null;
			}
		}
		return otdsUser;
	}

	public static boolean isUserExists(String userId) throws Exception {
		OtdsUser otdsUser = getOTDSUser(userId);

		return otdsUser != null;
	}

	public static void getOTDSConfigurations() throws XMLException, UnsupportedEncodingException {
		SOAPRequestObject sro = new SOAPRequestObject("http://schemas.cordys.com/1.0/xmlstore", "GetXMLObject", new String[]{"key"}, new String[]{"/OpenText/Integration/otds-config.xml"});

		int response = 0;

		try {
			response = sro.execute();

			setOTDS_URL(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::OTDS_URL", null, response)));
			setOTDS_ADMIN_USER(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::OTDS_ADMIN_USER", null, response)));
			setOTDS_ADMIN_PASSWORD(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::OTDS_ADMIN_PASSWORD", null, response)));
			setOTDS_USER_PARTITION(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::OTDS_USER_PARTITION", null, response)));
			setOTDS_RESOURCE_ID(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::OTDS_RESOURCE_ID", null, response)));
			setOTDS_RESOURCE_NAME(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::OTDS_RESOURCE_NAME", null, response)));
			setIS_PASSWORD_RESET_REQUIRED(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::IS_PASSWORD_RESET_REQUIRED", null, response)));
			setDEFAULT_PASSWORD(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::DEFAULT_PASSWORD", null, response)));
			setENFORCE_COMPLEX_PASSWORD(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::ENFORCE_COMPLEX_PASSWORD", null, response)));
		} finally {
			if (Node.isValidNode(response)) {
				Node.delete(response);
				response = 0;
			}
		}
	}

	public static OtdsUser getOTDSUserById(String userId) throws Exception {

		OtdsAdminClient otdsAdminClient = getOTDSAdminClient();

		if (otdsAdminClient == null) {
			throw new Exception("Failed to login into OTDS using admin credentials");
		}

		OtdsPartition partition = otdsAdminClient.getUserPartitionByName(getOTDS_USER_PARTITION());

		if (otdsAdminClient != null) {
			otdsAdminClient.close();
			otdsAdminClient = null;
		}

		return getOTDSUser(userId + "@" + partition.getOtdsPartitionId());
	}

	public static String createOrUpdateUserInOTDS(String userId, String userName, String userEmail) throws Exception {

		if (userId == null || userName == null || userEmail == null) {
			throw new Exception("All parameters are required");
		}
		if (userId.equals("")) {
			throw new Exception("User ID cannot be empty");
		}
		if (userName.equals("")) {
			throw new Exception("User Name cannot be empty");
		}
		if (userEmail.equals("")) {
			throw new Exception("Email cannot be empty");
		}

		OtdsAdminClient otdsAdminClient = getOTDSAdminClient();

		OtdsPartition otdsPartition = otdsAdminClient.getUserPartitionByName(getOTDS_USER_PARTITION());

		OtdsUser otdsUser = getOTDSUser(userId + "@" + otdsPartition.getOtdsPartitionId());

		List<Attribute> attrs = new ArrayList<>();
		Attribute attr;
		attr = new Attribute();
		attr.setName("displayName");
		attr.getValues().add(userName);
		attrs.add(attr);
		attr = new Attribute();
		attr.setName("description");
		attr.getValues().add(userName);
		attrs.add(attr);
		attr = new Attribute();
		attr.setName("mail");
		attr.getValues().add(userEmail);
		attrs.add(attr);

		if (otdsUser == null) {
			// Create User In OTDS
			otdsUser = otdsAdminClient.createUser(otdsPartition.getEntryDN(), userId, userName, attrs);

		} else {
			// Update User In OTDS
			otdsUser = otdsAdminClient.updateUser(userId + "@" + otdsPartition.getOtdsPartitionId(), userId, userName, attrs);
		}

		if (otdsAdminClient != null) {
			otdsAdminClient.close();
			otdsAdminClient = null;
		}

		return otdsUser.getEntryDN();
	}

	public static void deleteUserInPartition(String userId) throws Exception {
		if (userId == null) {
			throw new Exception("User Id cannot be null");
		}
		if (userId.equals("")) {
			throw new Exception("User Id cannot be empty");
		}

		OtdsAdminClient otdsAdminClient = getOTDSAdminClient();

		OtdsPartition otdsPartition = otdsAdminClient.getUserPartitionByName(getOTDS_USER_PARTITION());

		OtdsUser otdsUser = getOTDSUser(userId + "@" + otdsPartition.getOtdsPartitionId());

		if (otdsUser == null) {
			throw new Exception("User does not exist in Partition");
		}

		otdsAdminClient.deleteUser(userId + "@" + otdsPartition.getOtdsPartitionId());

		if (otdsAdminClient != null) {
			otdsAdminClient.close();
			otdsAdminClient = null;
		}
	}

	public static void consolidatePartitionRESTful() throws Exception {
		if (AUTHENTICATION_URL == null || CONSOLIDATION_URL == null) {
			getOTDSConfigurations();
		}

		String input = "{\"resourceList\":[\"cn=" + OTDS_RESOURCE_NAME + ",ou=Resources,dc=identity,dc=opentext,dc=net\"],\"consolidateWithIdentityProvider\":false,\"repair\":true,\"objectToConsolidate\":\"" + OTDS_USER_PARTITION + "\"}";

		String otdsSSOTicket = getOTDSSSOTicketRESTful();

		HttpURLConnection connectionObj = null;
		try {

			URL url = new URL(CONSOLIDATION_URL);
			connectionObj = (HttpURLConnection) url.openConnection();

			connectionObj.setRequestMethod("POST");
			connectionObj.setRequestProperty("Content-Type", "application/json");
			connectionObj.setRequestProperty("Cookie", "OTDSTicket=" + otdsSSOTicket);

			connectionObj.setDoOutput(true);
			OutputStream os = connectionObj.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			os.close();

			int response = connectionObj.getResponseCode();
			if (response != 204) {
				throw new Exception("Failed to consolidate the partition");
			}

		} finally {
			if (connectionObj != null) {
				connectionObj.disconnect();
			}
		}
	}

	private static boolean serachUserInOTDSRESTful(String userId, String otdsTicket) throws Exception {
		getOTDSConfigurations();

		HttpURLConnection connectionObj = null;

		try {
			URL url = new URL(getOTDS_URL() + "otdsws/rest/users/" + userId);
			connectionObj = (HttpURLConnection) url.openConnection();

			connectionObj.setRequestMethod("GET");
			connectionObj.setRequestProperty("Cookie", "OTDSTicket=" + otdsTicket);

			connectionObj.setDoOutput(true);

			int responseCode = connectionObj.getResponseCode();
			if (responseCode != 200) {
				return false;
			}
			return true;
		} finally {
			if (connectionObj != null) {
				connectionObj.disconnect();
			}
		}
	}

	public static JSONObject getPasswordPolicyFromPartition(String ssoTicket) {
		HttpURLConnection connection = null;

		try {
			URL url = new URL(getOTDS_URL() + "otdsws/rest/partitions" + getOTDS_USER_PARTITION() + "/passwordpolicy");
			connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Cookie", "OTDSTicket=" + ssoTicket);

			if (connection.getResponseCode() != 200) {
				return null;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			String outputJSON = br.readLine();

			return new JSONObject(outputJSON);
		} catch(Exception e) {

		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}

		return null;
	}

	public static String createOrUpdateUserInPartitionRESTful(String userId, String userName, String email, String designation, String orgDN) throws Exception {
		return _createOrUpdateUserInPartitionRESTful(userId, userName, email, orgDN, "agent".equalsIgnoreCase(designation), false);
	}
	
	public static String resetPasswordForUser(String USER_ID, String USER_NAME, String USER_EMAIL, String DESIGNATION) throws Exception {
		return _createOrUpdateUserInPartitionRESTful(USER_ID, USER_NAME, USER_EMAIL, BSF.getOrganization(), true, true);
	}

	private static String _createOrUpdateUserInPartitionRESTful(String userId, String userName, String email, String orgDN, boolean mustChangePassword, boolean isReset) throws Exception {
		_getOTDSConfigurations(orgDN);

		String otdsSSOTicket = getOTDSSSOTicketRESTful();
		String password = "";

		int minChars = 0;
		int minUpperCase = 1;
		int minLowerCase = 1;
		int minDigit = 1;
		int minSpecialChars = 1;

		JSONObject passwordPolicy = getPasswordPolicyFromPartition(otdsSSOTicket);

		if (passwordPolicy != null) {
			minChars = passwordPolicy.getInt("minimumNumberOfCharacters");
			minUpperCase = passwordPolicy.getInt("minimumNumberOfUppercase");
			minLowerCase = passwordPolicy.getInt("minimumNumberOfLowercase");
			minDigit = passwordPolicy.getInt("minimumNumberOfDigits");
			minSpecialChars = passwordPolicy.getInt("minimumNumberOfSymbols");
		}
		if (mustChangePassword) {
			String spclChar = SPECIAL_CHARS[(int)Math.round(Math.random() * 7)];
			password = userId.substring(0, 1).toUpperCase().concat(userId.substring(1, 2).toLowerCase()).concat(spclChar).concat(Double.toString(Math.round(Math.random() * 1000))).concat(userName.substring(1, 2));
			if (password.length() < minChars) {
				int l = minChars - password.length();
				password = password.concat(userId.substring(0, l));
			}
		} else {
			password = getDEFAULT_PASSWORD();
		}

		HttpURLConnection connectionObj = null;

		Date currDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String date = sdf.format(currDate);

		String pswdChangeRequired = (mustChangePassword) ? "true" : "false";

		FileWriter writerObj = null;

		String inputJSON = "{\"values\":[{\"name\":\"displayName\",\"values\":[\"" + userName + "\"]},"
				+ "{\"name\":\"mail\",\"values\":[\"" + email + "\"]},"
				+ "{\"name\":\"UserMustChangePasswordAtNextSignIn\",\"values\":[\"" + pswdChangeRequired + "\"]},"
				+ "{\"name\":\"PasswordNeverExpires\",\"values\":[\"true\"]}";
		//+ "{\"name\":\"UserCannotChangePassword\",\"values\":[\"true\"]},"
		if ("Y".equals(getIS_PASSWORD_RESET_REQUIRED())) {
			inputJSON += ",{\"name\":\"userPassword\",\"values\":[\"" + password + "\"]}";
		}
		inputJSON += "],\"customAttributes\":[],"
				+ "\"userPartitionID\":\"" + OTDS_USER_PARTITION + "\","
				+ "\"location\":\"ou=Root,ou=" + OTDS_USER_PARTITION + ",ou=IdentityProviders,dc=identity,dc=opentext,dc=net\","
				+ "\"name\":\"" + userId + "\",\"description\":\"" + userName + "\"}";

		String updateUserJSON = "{\"userPartitionID\":\"" + OTDS_USER_PARTITION + "\","
				+ "\"name\":\"" + userId + "\",\"location\":\"cn=" + userId + ",ou=Root,ou=" + OTDS_USER_PARTITION + ",ou=IdentityProviders,dc=identity,dc=opentext,dc=net\","
				+ "\"id\":\"" + userId + "@" + OTDS_USER_PARTITION + "\","
				+ "\"values\":[{\"name\":\"cn\",\"values\":[\"" + userId + "\"]},"
				+ "{\"name\":\"displayName\",\"values\":[\"" + userName + "\"]},"
				+ "{\"name\":\"description\",\"values\":[\"" + userName + "\"]},"
				+ "{\"name\":\"entryDN\",\"values\":[\"cn=" + userId + ",ou=Root,ou=" + OTDS_USER_PARTITION + ",ou=IdentityProviders,dc=identity,dc=opentext,dc=net\"]},"
				+ "{\"name\":\"UserMustChangePasswordAtNextSignIn\",\"values\":[\"" + pswdChangeRequired + "\"]},";
		//+ "{\"name\":\"UserCannotChangePassword\",\"values\":[\"true\"]},"
		if ("Y".equals(getIS_PASSWORD_RESET_REQUIRED())) {
			if (!mustChangePassword) {
				//updateUserJSON += "{\"name\":\"userPassword\",\"values\":[\"" + password + "\"]},";
			}
			if (isReset && mustChangePassword) {
				updateUserJSON += "{\"name\":\"userPassword\",\"values\":[\"" + password + "\"]},";
			}
		}
		updateUserJSON += "{\"name\":\"PasswordNeverExpires\",\"values\":[\"true\"]},"
				+ "{\"name\":\"accountDisabled\",\"values\":[\"false\"]},{\"name\":\"accountLockedOut\",\"values\":[\"false\"]},"
				+ "{\"name\":\"mail\",\"values\":[\"" + email + "\"]}],\"description\":\"" + userName + "\",\"customAttributes\":[],"
				+ "\"objectClass\":\"oTPerson\",\"urlId\":\"" + userId + "@" + OTDS_USER_PARTITION + "\","
				+ "\"urlLocation\":\"cn=" + userId + ",ou=Root,ou=" + OTDS_USER_PARTITION + ",ou=IdentityProviders,dc=identity,dc=opentext,dc=net\"}";

		boolean userFound = serachUserInOTDSRESTful(userId, otdsSSOTicket);
		String serverURL = getOTDS_URL() + "otdsws/rest/users";
		String methodType = "";

		serverURL += (userFound) ? "/cn=" + userId + ",ou=Root,ou=" + OTDS_USER_PARTITION + ",ou=IdentityProviders,dc=identity,dc=opentext,dc=net" : "";
		methodType = (userFound) ? "PUT" : "POST";

		try {

			File logFileObj = new File(getOTDS_LOG_LOCATION() + "\\OTDS_LOG" + date + ".log");

			writerObj = new FileWriter(logFileObj, true);

			URL url = new URL(serverURL);

			connectionObj = (HttpURLConnection) url.openConnection();
			connectionObj.setRequestMethod(methodType);
			connectionObj.setRequestProperty("Content-Type", "application/json");
			connectionObj.setRequestProperty("Cookie", "OTDSTicket=" + otdsSSOTicket);

			connectionObj.setDoOutput(true);

			OutputStream os = connectionObj.getOutputStream();
			if (!userFound) {
				os.write(inputJSON.getBytes());
			} else {
				os.write(updateUserJSON.getBytes());
			}
			os.flush();
			os.close();

			int responseCode = connectionObj.getResponseCode();
			
			if (responseCode != 201 && !userFound) {
				writerObj.write("Failed to create to user " + userId + " in OTDS\n");
				return "";
			}
			if (responseCode != 200 && userFound) {
				writerObj.write("Failed to update to user " + userId + " in OTDS\n");
				return "";
			}
			if (responseCode == 201) {
				writerObj.write("User " + userId + " is successfully created in OTDS\n");
			} else if (responseCode == 200) {
				writerObj.write("User " + userId + " is successfully updated in OTDS\n");
			}

			return (mustChangePassword) ? password : "";
		} finally {
			if (connectionObj != null) {
				connectionObj.disconnect();
			}
			if (writerObj != null) {
				writerObj.write("-------File Close at " + new Date() + "-----------\n");
				writerObj.flush();
				writerObj.close();
			}
		}
	}

	@SuppressWarnings("deprecation")
	private static void _getOTDSConfigurations(String orgDN) {
		SOAPRequestObject sro = new SOAPRequestObject("http://schemas.cordys.com/1.0/xmlstore", "GetXMLObject", new String[]{"key"}, new String[]{"/OpenText/Integration/otds-config.xml"});

		int response = 0;

		try {
			response = sro.execute();

			setOTDS_URL(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::OTDS_URL", null, response)));
			setOTDS_ADMIN_USER(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::OTDS_ADMIN_USER", null, response)));
			setOTDS_ADMIN_PASSWORD(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::OTDS_ADMIN_PASSWORD", null, response)));
			setOTDS_USER_PARTITION(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::OTDS_USER_PARTITION", null, response)));
			setOTDS_RESOURCE_ID(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::OTDS_RESOURCE_ID", null, response)));
			setOTDS_RESOURCE_NAME(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::OTDS_RESOURCE_NAME", null, response)));
			setIS_PASSWORD_RESET_REQUIRED(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::IS_PASSWORD_RESET_REQUIRED", null, response), "N"));
			setENFORCE_COMPLEX_PASSWORD(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::ENFORCE_COMPLEX_PASSWORD", null, response), ""));
			setDEFAULT_PASSWORD(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::DEFAULT_PASSWORD", null, response), ""));
			setOTDS_LOG_LOCATION(Node.getData(XPath.getFirstMatch("descendant-or-self::Configuration/descendant-or-self::OTDS_LOG_LOCATION", null, response)));
		} finally {
			if (Node.isValidNode(response)) {
				Node.delete(response);
				response = 0;
			}
		}
	}

	private static String getOTDSSSOTicketRESTful() throws Exception {
		HttpURLConnection connectionObj = null;
		String otdsSSOTicket = "";
		String input = "{\"userName\":\"" + OTDS_ADMIN_USER + "\",\"password\":\"" + OTDS_ADMIN_PASSWORD + "\"}";
		try {
			URL url = new URL(AUTHENTICATION_URL);

			connectionObj = (HttpURLConnection) url.openConnection();

			connectionObj.setRequestMethod("POST");
			connectionObj.setRequestProperty("Content-Type", "application/json");

			connectionObj.setDoOutput(true);
			OutputStream os = connectionObj.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			os.close();	

			if (connectionObj.getResponseCode() != 200 ) {
				throw new RuntimeException("Failed : HTTP error code : " + connectionObj.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((connectionObj.getInputStream())));
			String outputJSON = br.readLine();
			System.out.println(  outputJSON );
			otdsSSOTicket = outputJSON.substring(outputJSON.indexOf("\"ticket\":") + 10, outputJSON.indexOf("\",\"resourceID\":"));

		} finally {
			if (connectionObj != null) {
				connectionObj.disconnect();
			}
		}
		return otdsSSOTicket;
	}

	public OTDSIntegration() {
		this((BusObjectConfig) null);
	}

	public OTDSIntegration(BusObjectConfig config) {
		super(config);
	}

	public void onInsert() {
	}

	public void onUpdate() {
	}

	public void onDelete() {
	}

	public static String getOTDS_USER_PARTITION() {
		return OTDS_USER_PARTITION;
	}

	public static void setOTDS_USER_PARTITION(String oTDS_USER_PARTITION) {
		OTDS_USER_PARTITION = oTDS_USER_PARTITION;
	}

	public static String getOTDS_ADMIN_PASSWORD() {
		return OTDS_ADMIN_PASSWORD;
	}

	public static void setOTDS_ADMIN_PASSWORD(String oTDS_ADMIN_PASSWORD) {
		OTDS_ADMIN_PASSWORD = oTDS_ADMIN_PASSWORD;
	}

	public static String getOTDS_ADMIN_USER() {
		return OTDS_ADMIN_USER;
	}

	public static void setOTDS_ADMIN_USER(String oTDS_ADMIN_USER) {
		OTDS_ADMIN_USER = oTDS_ADMIN_USER;
	}

	public static String getOTDS_URL() {
		return OTDS_URL;
	}

	public static void setOTDS_URL(String oTDS_URL) {
		OTDS_URL = oTDS_URL;
		AUTHENTICATION_URL = OTDS_URL + "otdsws/rest/authentication/credentials";
		CONSOLIDATION_URL = OTDS_URL + "otdsws/rest/consolidation";
	}

	public static String getOTDS_RESOURCE_ID() {
		return OTDS_RESOURCE_ID;
	}

	public static void setOTDS_RESOURCE_ID(String oTDS_RESOURCE_ID) {
		OTDS_RESOURCE_ID = oTDS_RESOURCE_ID;
	}

	public static void setOTDS_RESOURCE_NAME(String otdsResourceName) {
		OTDS_RESOURCE_NAME = otdsResourceName;
	}

	public static String getIS_PASSWORD_RESET_REQUIRED() {
		return IS_PASSWORD_RESET_REQUIRED;
	}

	public static void setIS_PASSWORD_RESET_REQUIRED(
			String iS_PASSWORD_RESET_REQUIRED) {
		IS_PASSWORD_RESET_REQUIRED = iS_PASSWORD_RESET_REQUIRED;
	}

	public static String getENFORCE_COMPLEX_PASSWORD() {
		return ENFORCE_COMPLEX_PASSWORD;
	}

	public static void setENFORCE_COMPLEX_PASSWORD(String eNFORCE_COMPLEX_PASSWORD) {
		ENFORCE_COMPLEX_PASSWORD = eNFORCE_COMPLEX_PASSWORD;
	}

	public static String getDEFAULT_PASSWORD() {
		return DEFAULT_PASSWORD;
	}

	public static void setDEFAULT_PASSWORD(String dEFAULT_PASSWORD) {
		if (!"Y".equals(getIS_PASSWORD_RESET_REQUIRED())) {
			DEFAULT_PASSWORD = "";
		} else {
			DEFAULT_PASSWORD = dEFAULT_PASSWORD;
		}
	}

	public static String getOTDS_LOG_LOCATION() {
		return OTDS_LOG_LOCATION;
	}

	public static void setOTDS_LOG_LOCATION(String oTDS_LOG_LOCATION) {
		OTDS_LOG_LOCATION = oTDS_LOG_LOCATION;
	}

	private static class AsyncOperation implements Callable<String> {

		private String userId;
		private String userName;
		private String email;
		private String designation;
		private String orgDN;

		public AsyncOperation(String userId, String userName, String email, String designation, String orgDN) {
			this.userId = userId;
			this.userName = userName;
			this.email = email;
			this.orgDN = orgDN;
		}

		@SuppressWarnings("deprecation")
		@Override
		public String call() throws Exception {
			String result = "";
			try {
				OTDSIntegration.createOrUpdateUserInPartitionRESTful(userId, userName, email, designation, orgDN);
			} catch (Exception e) {
				result = e.getMessage();
				logger.error("Exception::" + result);
				throw new Exception(result);
			}
			return result;
		}
	}

	private static class OTDSExecutor {
		@SuppressWarnings("deprecation")
		public static void createOrUpdateUserAsync(String userId, String userName, String email, String designation, String orgDN) {
			AsyncOperation asyncObj = new AsyncOperation(userId, userName, email, designation, orgDN);

			FutureTask<String> task = new FutureTask<>(asyncObj);

			ExecutorService executor = Executors.newFixedThreadPool(2);

			executor.execute(task);

			while (true) {
				if (task.isDone()) {
					logger.error("Created Successfully");
					executor.shutdown();
					break;
				}
			}
		}
	}

	public static void createOrUpdateUserInPartitionAsync(String userId, String userName, String email, String designation, String orgDN) {
		OTDSExecutor.createOrUpdateUserAsync(userId, userName, email, designation, orgDN);
	}

	public static String changePassword(String userId, String oldPassword, String newPassword,String accptedDate) throws Exception {

		String otdsSSOTicket;
		HttpURLConnection connectionObj = null;
		String inputJSON = "{\"oldPassword\":\""+oldPassword+"\",\"newPassword\":\""+newPassword+"\"}";
		String outputJSON=null;	
		String pwdPolicyresponseString = null;
		try {
			getOTDSConfigurations();
			otdsSSOTicket = getOTDSSSOTicketRESTful();

			if(validateCredentials(userId,oldPassword,otdsSSOTicket).equalsIgnoreCase("INVALID_CREDENTIALS")){

				return "Invalid Old Password";
			} else {
				if(!accptedDate.equals(null) && !accptedDate.equalsIgnoreCase("")){
					SOAPRequestObject sro = new SOAPRequestObject(
							"http://schemas.cordys.com/msig/agentportal/1.0", "UpdateAgentInfo",
							new String[] {"USER_ID", "PARAM", "isPassword"}, new String[] {userId, accptedDate + ".000", "false"});

					sro.execute();
					sro = null;
				}
				URL url = new URL(getOTDS_URL() + "otdsws/rest/users/" + userId+"/password");
				connectionObj = (HttpURLConnection) url.openConnection();

				connectionObj.setRequestMethod("POST");
				connectionObj.setRequestProperty("Content-Type", "application/json");
				connectionObj.setRequestProperty("Cookie", "OTDSTicket=" + otdsSSOTicket);

				connectionObj.setDoOutput(true);
				OutputStream os = connectionObj.getOutputStream();
				os.write(inputJSON.getBytes());
				os.flush();
				os.close();

				int response = connectionObj.getResponseCode();
				if (response == 204) {
					return "Success";
				} else {
					BufferedReader br = new BufferedReader(new InputStreamReader(connectionObj.getErrorStream()));
					outputJSON = br.readLine();
					return outputJSON.substring(outputJSON.indexOf("\"error\"")+9, outputJSON.indexOf("}")-1);
				}
			}
		}
		finally {
			if (connectionObj != null) {
				connectionObj.disconnect();
			}
		}
	}

	private static String validateCredentials(String userId,String password,String otdsSSOTicket) throws Exception{

		HttpURLConnection connectionObj = null;
		String inputJSON = "{\"userName\":\""+userId+"\",\"password\":\""+password+"\"}";

		String responseString=null;

		URL url = new URL(getOTDS_URL() + "otdsws/rest/authentication/credentials");
		connectionObj = (HttpURLConnection) url.openConnection();	

		connectionObj.setRequestMethod("POST");
		connectionObj.setRequestProperty("Content-Type", "application/json");
		connectionObj.setRequestProperty("Cookie", "OTDSTicket=" + otdsSSOTicket);

		connectionObj.setDoOutput(true);
		OutputStream os = connectionObj.getOutputStream();
		os.write(inputJSON.getBytes());
		os.flush();
		os.close();

		if (connectionObj.getResponseCode() !=200) {
			BufferedReader br = new BufferedReader(new InputStreamReader(connectionObj.getErrorStream()));
			String outputJSON = br.readLine();
			responseString = outputJSON.substring(outputJSON.indexOf("\"error\"")+9, outputJSON.indexOf("}")-1);
		} else {
			responseString = "Success";
		}
		return responseString;
	}
	
	public static boolean isUserLockedInOTDS(String userId) throws Exception {
		String ssoTicket = getOTDSSSOTicketRESTful();
		
		return _isUserLocked(userId, ssoTicket);
	}
	
	private static boolean _isUserLocked(String userId, String otdsTicket) throws Exception {
		HttpURLConnection connectionObj = null;
		
		try {
			URL url = new URL(getOTDS_URL() + "otdsws/rest/users/" + userId);
			connectionObj = (HttpURLConnection) url.openConnection();
			
			connectionObj.setRequestMethod("GET");
			connectionObj.setRequestProperty("Cookie", "OTDSTicket=" + otdsTicket);
			
			connectionObj.setDoOutput(true);
			
			BufferedReader br = new BufferedReader(new InputStreamReader((connectionObj.getInputStream())));
			String outputJSON = br.readLine();

			JSONObject obj = new JSONObject(outputJSON);
			
			JSONArray valuesArr = (JSONArray) obj.get("values");
			
			JSONArray arr = (JSONArray) valuesArr.query("/19/values");
			
			return arr.getBoolean(0);
			
		} finally {
			if (connectionObj != null) {
				connectionObj.disconnect();
			}
		}
	}
}