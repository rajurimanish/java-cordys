/**
 * 
 */
package com.cms.workflow;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.cordys.cpc.bsf.busobject.BSF;
import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.query.Cursor;
import com.eibus.xml.nom.Document;
import com.eibus.xml.nom.Node;
import com.eibus.xml.xpath.XPath;

/**
 * @author MYITVSR
 *
 */
public class MSIG_CMS_VIEW extends MSIG_CMS_VIEWBase {

	public MSIG_CMS_VIEW() {
		this((BusObjectConfig) null);
	}

	public MSIG_CMS_VIEW(BusObjectConfig config) {
		super(config);
	}

	public void onInsert() {
	}

	public void onUpdate() {
	}

	public void onDelete() {
	}

	private static Document doc = BSF.getXMLDocument();

	/**
	 * This is the main web service method for the loading the CMS Screens
	 * 
	 * @param searchDefinition
	 * @param sortDefinition
	 * @param formName
	 * @param view
	 * @param cursor
	 * @return
	 * @throws MSIG_CMS_EXCEPTION
	 */
	public static BusObjectIterator<?> fetchCMSRecords(int searchDefinition, int sortDefinition, String formName,
			String view, Cursor cursor) throws MSIG_CMS_EXCEPTION {

		if (StringUtils.isBlank(formName)) {
			throw new MSIG_CMS_EXCEPTION("Invalid Form Name.");
		}
		String errorMessage = "Error occured while fetching database configuration.";
		int viewDefinition = 0;
		int[] response = null;
		try {
			List<String> searchCriteriaColumns = new ArrayList<String>();
			// Fetch view definition from database
			MSIG_PROPERTIES propObject = MSIG_PROPERTIES.getMsigPropertiesObject("CMS_VIEW_CONFIG");;
			String viewName;
			response = XPath.getMatchingNodes(".//reportView", null, doc.parseString(propObject.getVALUE()));
			if(response != null) {
				for (int node : response) {
					viewName = Node.getAttribute(node, "id");
					if (StringUtils.equalsIgnoreCase(formName, viewName)) {
						viewDefinition = node;
						break;
					}
				}
			}
			if (viewDefinition == 0) {
				throw new MSIG_CMS_EXCEPTION("Invalid Request.");
			}
			int[] isearchFieldNodes = XPath.getMatchingNodes(".//criteria", null, searchDefinition);

			if (isearchFieldNodes.length > 0 && isearchFieldNodes != null) {
				// Fetch search Filters from view Definition
				searchCriteriaColumns = fetchSearchCriteriaColumns(viewDefinition);
				// Validate the search filters passed from UI
				validateSearchCriteria(isearchFieldNodes, searchCriteriaColumns);
			}
			return MSIG_CMS_APP.fetchCases(viewDefinition, searchDefinition, sortDefinition, formName, view, cursor);
		}catch (Exception e) {
			throw new MSIG_CMS_EXCEPTION(errorMessage);
		} finally {
			if(Node.isValidNode(viewDefinition)) {
				Node.delete(viewDefinition);
				response = null;
			}
		}
	}

	/**
	 * This method is to fetch the view definition for CMS screens from database
	 * 
	 * @param node
	 * @return veiwDefinition
	 * @throws MSIG_CMS_EXCEPTION
	 */
	public static int fetchViewDefinition(String formName) throws MSIG_CMS_EXCEPTION {
		String errorMessage = "Error occured while fetching database configuration.";
		MSIG_PROPERTIES propObject = null;
		try {
			String viewName;
			propObject = MSIG_PROPERTIES.getMsigPropertiesObject("CMS_VIEW_CONFIG");
			int[] viewDefinition = XPath.getMatchingNodes(".//reportView", null, doc.parseString(propObject.getVALUE()));
			for (int node : viewDefinition) {
				viewName = Node.getAttribute(node, "id");
				if (StringUtils.equalsIgnoreCase(formName, viewName)) {
					return node;
				}
			}
			return 0;
		} catch (Exception e) {
			throw new MSIG_CMS_EXCEPTION(errorMessage);
		}
	}

	/**
	 * This method is used to fetch the search criteria columns for filter
	 * validation
	 * 
	 * @param viewDefinition
	 * @return
	 */
	public static List<String> fetchSearchCriteriaColumns(int viewDefinition) {
		XPath oXpath = XPath.getXPathInstance(".//field");
		String searchField = "";
		List<String> searchCriteriaColumn = new ArrayList<String>();
		int[] fields = oXpath.selectElementNodes(viewDefinition);
		for (int field : fields) {
			searchField = Node.getAttribute(field, "searchFilter");
			if (StringUtils.isNotBlank(searchField)) {
				searchCriteriaColumn.add(searchField);
			}
		}
		return searchCriteriaColumn;
	}

	/**
	 * This method is used to validate the search filter passed from UI against the
	 * search filters in view Definition
	 * 
	 * @param searchDefinition
	 * @param columnList
	 * @throws MSIG_CMS_EXCEPTION
	 */
	private static void validateSearchCriteria(int[] isearchFieldNodes, List<String> columnList)
			throws MSIG_CMS_EXCEPTION {
		String columnName = null;
		String operand = null;
		if (isearchFieldNodes.length > 0 && isearchFieldNodes != null) {
			boolean validColumnName = false, validOperand = false;
			for (int a : isearchFieldNodes) {
				columnName = Node.getData(XPath.getFirstMatch(".//name", null, a));
				operand = Node.getData(XPath.getFirstMatch(".//operand", null, a));
				if (StringUtils.isNotBlank(columnName) && columnList.contains(columnName)) {
					validColumnName = true;
				} else {
					validColumnName = false;
				}
				if (StringUtils.isNotBlank(operand)
						&& StringUtils.isNotBlank(MSIG_CMS_UTILS.operatorMap.get(operand))) {
					validOperand = true;
				} else {
					validOperand = false;
				}
			}

			if (!validColumnName) {
				throw new MSIG_CMS_EXCEPTION("Invalid column Name.");
			}

			if (!validOperand) {
				throw new MSIG_CMS_EXCEPTION("Invalid Operand");
			}
		}
	}
}
