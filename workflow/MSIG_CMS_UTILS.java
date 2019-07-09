/**
 *
 */
package com.cms.workflow;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import com.cordys.cpc.bsf.busobject.BSF;
import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.QueryObject;
import com.cordys.cpc.bsf.busobject.QueryParameter;
import com.eibus.xml.nom.Node;
import com.eibus.xml.nom.XMLException;
import com.eibus.xml.xpath.XPath;

/**
 * @author MYITVSR
 *
 */
public class MSIG_CMS_UTILS extends MSIG_CMS_UTILSBase {

	public MSIG_CMS_UTILS() {
		this((BusObjectConfig) null);
	}

	public MSIG_CMS_UTILS(BusObjectConfig config) {
		super(config);
	}

	public void onInsert() {
	}

	public void onUpdate() {
	}

	public void onDelete() {
	}
	
	public static final Map<String, String> operatorMap;
	static {
		Map<String, String> opMap = new HashMap<String, String>();
		opMap.put("EQ", "=");
		opMap.put("GT", ">");
		opMap.put("GTEQ", ">=");
		opMap.put("LT", "<");
		opMap.put("LTEQ", "<=");
		opMap.put("LIKE", "LIKE");
		operatorMap = Collections.unmodifiableMap(opMap);
	}

	public static String getUserName() {
		String userDN = getUserDN();
		String userName = userDN.substring(3, userDN.indexOf(","));
		return userName;
	}

	public static String getUserDN() {
		return BSF.getUser();
	}

	public String fetchSearchFields(Vector<QueryParameter> params, int[] isearchFieldNodes)
			throws UnsupportedEncodingException, XMLException {
		String searchClause = "";
		int paramCount = 0;
		XPath oXpath;
		String sXpath;
		String tempSearchClause;
		String dataType;
		String sFieldId;
		String sFieldValue = "", sOperand = "";
		for (int criteria : isearchFieldNodes) {
			sXpath = ".//name";
			oXpath = XPath.getXPathInstance(sXpath);
			sFieldId = Node.getData(oXpath.selectElementNodes(criteria)[0]);

			sXpath = ".//value";
			oXpath = XPath.getXPathInstance(sXpath);
			sFieldValue = Node.getData(oXpath.selectElementNodes(criteria)[0]);

			sXpath = ".//operand";
			oXpath = XPath.getXPathInstance(sXpath);
			sOperand = Node.getData(oXpath.selectElementNodes(criteria)[0]);
			sOperand = (sOperand.equals("0") || sOperand.equals("")) ? "=" : operatorMap.get(sOperand);

			sXpath = ".//dataType";
			oXpath = XPath.getXPathInstance(sXpath);
			dataType = Node.getData(oXpath.selectElementNodes(criteria)[0]);

			tempSearchClause = this.frameSearchQuery(params, sFieldId, sFieldValue, dataType, sOperand, paramCount);
			if (tempSearchClause != null && !"".equalsIgnoreCase(tempSearchClause) && !"null".equalsIgnoreCase(tempSearchClause)) {
				searchClause = searchClause + tempSearchClause.split("#separator#")[0];
				paramCount = Integer.parseInt(tempSearchClause.split("#separator#")[1]);
			} else {
				return null;
			}
		}
		searchClause = StringUtils.replaceOnce(searchClause, "AND", "");
		return searchClause;
	}

	public String frameSearchQuery(Vector<QueryParameter> params, String fieldName, String fieldValue, String dataType,
			String operand, int paramCount) throws UnsupportedEncodingException, XMLException {
		String searchClause = null;
		if (StringUtils.equalsIgnoreCase(dataType, "datetime")) {
			// For Date type
			searchClause = this.frameQueryforDate(params, fieldName, fieldValue, operand, paramCount);
		} else if (StringUtils.equalsIgnoreCase(dataType, "string")
				|| StringUtils.equalsIgnoreCase(dataType, "select")) {
			// For String type
			searchClause = this.frameQueryforString(params, fieldName, fieldValue, operand, paramCount);
		} else {
			// For Numeric type
			searchClause = this.frameQueryforNumber(params, fieldName, fieldValue, operand, paramCount);
		}
		return searchClause;
	}

	public String frameQueryforDate(Vector<QueryParameter> params, String sFieldId, String sFieldValue, String sOperand,
			int paramCount) throws UnsupportedEncodingException, XMLException {
		String sStartValue = "", sFinalValue = "", paramVariable = "", searchClause = "";
		QueryParameter qParam;
		sFieldValue = sFieldValue.replace('T', ' ');
		if (!StringUtils.contains(sFieldValue, "IMP")) {
			// For normal filters
			sStartValue = sFieldValue.split("\\.")[0];
			sFinalValue = sFieldValue.split("\\.")[0];
		} else {
			// one that comes from quick filter say like "Last 7 days"
			sFieldValue = sFieldValue.split("IMP")[0].split("\\.")[0];
			sStartValue = sFieldValue;
		}
		if (StringUtils.equalsIgnoreCase(sOperand, "=")) {
			paramVariable = "param" + (paramCount++);
			searchClause =  searchClause + "AND " + sFieldId + " BETWEEN CONVERT(DATETIME2,:" + paramVariable + ") ";
			qParam = new QueryParameter(paramVariable, sFieldId, QueryObject.PARAM_STRING, sStartValue);
			params.add(qParam);
			paramVariable = "param" + (paramCount++);
			searchClause = searchClause + " AND DATEADD(d,(1- 1/(24*60*60)),CONVERT(DATE,:" + paramVariable + ",120))";
			qParam = new QueryParameter(paramVariable, sFieldId, QueryObject.PARAM_STRING, sFinalValue);
			params.add(qParam);
		} else if (StringUtils.equalsIgnoreCase(sOperand, ">") || StringUtils.equalsIgnoreCase(sOperand, "<=")) {
			paramVariable = "param" + (paramCount++);
			searchClause = searchClause + "AND " + sFieldId + sOperand + "DATEADD(d,(1- 1/(24*60*60)),CONVERT(DATE,:" + paramVariable + ",120))";
			qParam = new QueryParameter(paramVariable, sFieldId, QueryObject.PARAM_STRING, sFinalValue);
			params.add(qParam);
		} else if (StringUtils.equalsIgnoreCase(sOperand, "<") || StringUtils.equalsIgnoreCase(sOperand, ">=")) {
			// "<" or ">="
			paramVariable = "param" + (paramCount++);
			searchClause = searchClause + "AND " + sFieldId + sOperand + "CONVERT(DATETIME2,:" + paramVariable + ")";
			qParam = new QueryParameter(paramVariable, sFieldId, QueryObject.PARAM_STRING, sStartValue);
			params.add(qParam);
		} else {
			// "!="
			paramVariable = "param" + (paramCount++);
			searchClause = searchClause + "AND (" + sFieldId + " NOT BETWEEN CONVERT(DATETIME2,:" + paramVariable + ")";
			qParam = new QueryParameter(paramVariable, sFieldId, QueryObject.PARAM_STRING, sStartValue);
			params.add(qParam);
			paramVariable = "param" + (paramCount++);
			searchClause = searchClause + " AND DATEADD(d,(1- 1/(24*60*60)),CONVERT(DATE,:" + paramVariable + ",120))";
			qParam = new QueryParameter(paramVariable, sFieldId, QueryObject.PARAM_STRING, sFinalValue);
			params.add(qParam);
			searchClause = searchClause + " OR " + sFieldId + " IS NULL ) ";
		}
		return searchClause + "#separator#" + paramCount;
	}

	public String frameQueryforString(Vector<QueryParameter> params, String sFieldId, String sFieldValue,
			String sOperand, int paramCount) {
		String paramVariable = "", searchClause = "";
		QueryParameter qParam;
		if (StringUtils.equalsIgnoreCase(sFieldValue, "blank")) {
			// blank implies NULL value
			if (StringUtils.equalsIgnoreCase(sOperand, "=")) {
				// Equal to operation
				searchClause = searchClause + "AND " + sFieldId + " IS NULL ";
			} else {
				// Not equal to operation
				searchClause = searchClause + "AND " + sFieldId + " IS NOT NULL ";
			}
		} else {
			if (StringUtils.equalsIgnoreCase(sOperand, "LIKE")) {
				// LIKE operation
				paramVariable = "param" + (paramCount++);
				searchClause = searchClause + "AND " + sFieldId + " " + sOperand + " :" + paramVariable + " ";
				qParam = new QueryParameter(paramVariable, sFieldId, QueryObject.PARAM_STRING, "%" + sFieldValue + "%");
				params.add(qParam);
			} else if (StringUtils.equalsIgnoreCase(sOperand, "IN")) {
				// IN operation - framing values for IN query happens
				String[] value = sFieldValue.split(",");
				searchClause = searchClause + "AND " + sFieldId + " " + sOperand + "(";
				for (int k = 0; k < value.length; k++) {
					paramVariable = "param" + (paramCount++);
					if (k + 1 != value.length)
						searchClause = searchClause + ":" + paramVariable + ",";
					else
						searchClause = searchClause + ":" + paramVariable;
					qParam = new QueryParameter(paramVariable, sFieldId, QueryObject.PARAM_STRING, value[k].trim());
					params.add(qParam);
				}
				searchClause = searchClause + ") ";
			} else {
				paramVariable = "param" + (paramCount++);
				if (StringUtils.equalsIgnoreCase(sOperand, "=")) {
					searchClause = searchClause + "AND " + sFieldId + sOperand + ":" + paramVariable + " ";
					qParam = new QueryParameter(paramVariable, sFieldId, QueryObject.PARAM_STRING, sFieldValue);
					params.add(qParam);
				} else {
					// != Operator
					searchClause = searchClause + "AND (" + sFieldId + sOperand + ":" + paramVariable + " ";
					searchClause = searchClause + " OR " + sFieldId + " IS NULL )";
					qParam = new QueryParameter(paramVariable, sFieldId, QueryObject.PARAM_STRING, sFieldValue);
					params.add(qParam);
				}
			}
		}
		return searchClause + "#separator#" + paramCount;
	}

	public String frameQueryforNumber(Vector<QueryParameter> params, String sFieldId, String sFieldValue,
			String sOperand, int paramCount) {
		String paramVariable = "", searchClause = "";
		QueryParameter qParam;
		paramVariable = "param" + (paramCount++);
		if (StringUtils.equalsIgnoreCase(sOperand, "=")) {
			searchClause = searchClause + "AND " + sFieldId + sOperand + ":" + paramVariable + " ";
			qParam = new QueryParameter(paramVariable, sFieldId, QueryObject.PARAM_BIGDECIMAL, sFieldValue);
			params.add(qParam);
		} else if (StringUtils.equalsIgnoreCase(sOperand, "!=")) {
			// != Operator
			searchClause = searchClause + "AND (" + sFieldId + " " + sOperand + " :" + paramVariable + " ";
			searchClause = searchClause + " OR " + sFieldId + " IS NULL )";
			qParam = new QueryParameter(paramVariable, sFieldId, QueryObject.PARAM_BIGDECIMAL, sFieldValue);
			params.add(qParam);
		} else {
			searchClause = searchClause + "AND " + sFieldId + " " + sOperand + " :" + paramVariable + " ";
			qParam = new QueryParameter(paramVariable, sFieldId, QueryObject.PARAM_BIGDECIMAL, sFieldValue);
			params.add(qParam);
		}
		return searchClause + "#separator#" + paramCount;
	}
	
	public String formOrderByClause(int sortDefinition) throws MSIG_CMS_EXCEPTION {
		String sXpath = ".//sortFields";
		XPath oXpath = XPath.getXPathInstance(sXpath);
		String orderByClause = "";
		int[] sortColumns = oXpath.selectElementNodes(sortDefinition);
		if (sortColumns != null && sortColumns.length > 0) {
			String colName = null, operator = null;
			for (int sortCriteria : sortColumns) {
				colName = Node.getAttribute(sortCriteria, "fieldName");
				if (StringUtils.isNotBlank(colName)) {
					orderByClause = orderByClause + " " + colName + " ";
					operator = Node.getAttribute(sortCriteria, "order");
					operator = (StringUtils.isBlank(operator)) ? "ASC" : operator;
					orderByClause = orderByClause + operator + ",";
				} else {
					throw new MSIG_CMS_EXCEPTION("Invalid Order By Clause");
				}
			}
			orderByClause = " ORDER BY " + orderByClause.trim();
			orderByClause = orderByClause.substring(0, orderByClause.length() - 1);
		}
		return orderByClause;
	}
	
	/**
	 * This method frames the columns to be fetched in the query
	 * 
	 * @param iFieldNodes
	 * @return
	 */
	public String frameColumnsInQuery(int[] iFieldNodes) {
		String columnName = "", columnns = "", aliasName = "", dataType;
		for (int node : iFieldNodes) {
			columnName = Node.getAttribute(node, "name");
			aliasName = Node.getAttribute(node, "alias");
			dataType = Node.getAttribute(node, "datatype");
			if (StringUtils.isNotBlank(aliasName)) {
				if(StringUtils.equalsIgnoreCase("string", dataType)) {
					columnns = columnns + columnName + " AS " + aliasName + ", ";
				} else if(StringUtils.equalsIgnoreCase("number", dataType)) {
					columnns = columnns + "TRY_CONVERT (decimal(18,2), " + columnName + ") AS " + aliasName + ", ";
				}
			}
		}
		columnns = columnns.substring(0, columnns.length() - 2);
		return columnns;
	}

	public int [] fetchFieldsFromDefinition(int veiwDefinition) {
		XPath oXpath = XPath.getXPathInstance(".//field");
		return oXpath.selectElementNodes(veiwDefinition);
	}

	/**
	 * 
	 * This method is used to prepare the default Query String for the CMS screens
	 * 
	 * @param params
	 * @param formName
	 * @param viewName
	 * @param userId
	 * @return
	 * @throws MSIG_CMS_EXCEPTION
	 */
	public String prepareDefaultQueryString(Vector<QueryParameter> params, String formName, String viewName,
			String userId) throws MSIG_CMS_EXCEPTION {
		String defaultQueryString = "";
		QueryParameter qParam;
		if (StringUtils.equalsIgnoreCase("Drafts", formName)) {

			if (StringUtils.isNotBlank(viewName) && !StringUtils.equalsIgnoreCase("TaskAdmin", viewName)) {
				throw new MSIG_CMS_EXCEPTION("Invalid View Name.");
			}

			defaultQueryString = " WHERE STATUS = :draftStatus AND APPLICATION_NAME = :application";
			qParam = new QueryParameter("draftStatus", "STATUS", QueryObject.PARAM_STRING, "Draft");
			params.add(qParam);
			qParam = new QueryParameter("application", "APPLICATION_NAME", QueryObject.PARAM_STRING, "CMS");
			params.add(qParam);

			// view check for Notification Drafts or My Drafts
			if (StringUtils.isBlank(viewName)) {
				defaultQueryString = defaultQueryString + " AND INITIATED_BY = :initiatedBy ";
				qParam = new QueryParameter("initiatedBy", "INITIATED_BY", QueryObject.PARAM_STRING, userId);
				params.add(qParam);
			}

		} else if (StringUtils.equalsIgnoreCase("MyCases", formName)) {

			defaultQueryString = " WHERE CASE_STATUS NOT IN (:draftStatus, :deletedStatus) AND CLAIM_HANDLER = :userId";

			qParam = new QueryParameter("draftStatus", "CASE_STATUS", QueryObject.PARAM_STRING, "Draft");
			params.add(qParam);
			qParam = new QueryParameter("deletedStatus", "CASE_STATUS", QueryObject.PARAM_STRING, "Deleted");
			params.add(qParam);
			qParam = new QueryParameter("userId", "CLAIM_HANDLER", QueryObject.PARAM_STRING, userId);
			params.add(qParam);

		} else if (StringUtils.equalsIgnoreCase("ApprovedClaims", formName)) {

			defaultQueryString = " WHERE CASE_STATUS NOT IN (:draftStatus, :deletedStatus) AND CASE_ID IN (SELECT CASE_ID FROM MSIG_BO_ACTIVITIES WITH (NOLOCK) WHERE USER_ID = :approverId )";

			qParam = new QueryParameter("draftStatus", "CASE_STATUS", QueryObject.PARAM_STRING, "Draft");
			params.add(qParam);
			qParam = new QueryParameter("deletedStatus", "CASE_STATUS", QueryObject.PARAM_STRING, "Deleted");
			params.add(qParam);
			qParam = new QueryParameter("approverId", "USER_ID", QueryObject.PARAM_STRING, userId);
			params.add(qParam);

		} else if (StringUtils.equalsIgnoreCase("ApprovedInvoices", formName)) {

			defaultQueryString = " WHERE A.STATUS NOT IN (:draftStatus, :deletedStatus)"
					+ " AND A.CASE_ID IN (SELECT CASE_ID FROM MSIG_BO_ACTIVITIES WITH (NOLOCK) WHERE USER_ID = :approverId )";

			qParam = new QueryParameter("draftStatus", "A.STATUS", QueryObject.PARAM_STRING, "Draft");
			params.add(qParam);
			qParam = new QueryParameter("deletedStatus", "A.STATUS", QueryObject.PARAM_STRING, "Deleted");
			params.add(qParam);
			qParam = new QueryParameter("approverId", "USER_ID", QueryObject.PARAM_STRING, userId);
			params.add(qParam);

		} else if (StringUtils.equalsIgnoreCase("OrphanBox", formName)) {

			defaultQueryString = " WHERE CASE_STATUS IN (:orphanPolicyStatus, :orphanClaimStatus)";

			qParam = new QueryParameter("orphanPolicyStatus", "CASE_STATUS", QueryObject.PARAM_STRING, "Orphan Policy");
			params.add(qParam);
			qParam = new QueryParameter("orphanClaimStatus", "CASE_STATUS", QueryObject.PARAM_STRING, "Orphan Claims");
			params.add(qParam);

		} else if (StringUtils.equalsIgnoreCase("ClosedClaims", formName)) {

			defaultQueryString = " WHERE CASE_STATUS = :closedStatus";

			qParam = new QueryParameter("closedStatus", "CASE_STATUS", QueryObject.PARAM_STRING, "Closed");
			params.add(qParam);

		} else if (StringUtils.equalsIgnoreCase("ClaimsEnquiry", formName)) {

			defaultQueryString = " WHERE CLAIM_TYPE = :claimType AND CASE_STATUS NOT IN (:draftStatus, :deletedStatus)";

			qParam = new QueryParameter("claimType", "CLAIM_TYPE", QueryObject.PARAM_STRING, viewName);
			params.add(qParam);
			qParam = new QueryParameter("draftStatus", "CASE_STATUS", QueryObject.PARAM_STRING, "Draft");
			params.add(qParam);
			qParam = new QueryParameter("deletedStatus", "CASE_STATUS", QueryObject.PARAM_STRING, "Deleted");
			params.add(qParam);

		} else if (StringUtils.equalsIgnoreCase("InvoiceEnquiry", formName)) {

			defaultQueryString = " WHERE A.STATUS NOT IN (:draftStatus, :deletedStatus)";

			qParam = new QueryParameter("draftStatus", "A.STATUS", QueryObject.PARAM_STRING, "Draft");
			params.add(qParam);
			qParam = new QueryParameter("deletedStatus", "A.STATUS", QueryObject.PARAM_STRING, "Deleted");
			params.add(qParam);

		} else if (StringUtils.equalsIgnoreCase("MyPayments", formName)) {

			defaultQueryString = " WHERE STATUS IN (:closedStatus, :pendingPayment)";

			qParam = new QueryParameter("closedStatus", "STATUS", QueryObject.PARAM_STRING, "Closed");
			params.add(qParam);
			qParam = new QueryParameter("pendingPayment", "A.STATUS", QueryObject.PARAM_STRING, "Payment Pending");
			params.add(qParam);

		} else {
			throw new MSIG_CMS_EXCEPTION("Error occured while framing query");
		}
		return defaultQueryString;
	}
	
}

