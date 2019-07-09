/**
 * 
 */
package com.cms.workflow;

import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.QueryObject;
import com.cordys.cpc.bsf.busobject.QueryParameter;
import com.cordys.cpc.bsf.query.Cursor;
import com.cordys.cpc.bsf.query.xqy.XqyException;
import com.eibus.util.logger.CordysLogger;
import com.eibus.xml.nom.Node;
import com.eibus.xml.xpath.XPath;

/**
 * @author MYITVSR
 *
 */
@SuppressWarnings("deprecation")
public class MSIG_CMS_APP extends MSIG_CMS_APPBase
{
    public MSIG_CMS_APP()
    {
        this((BusObjectConfig)null);
    }

    public MSIG_CMS_APP(BusObjectConfig config)
    {
        super(config);
    }

	public void onInsert() {
	}

	public void onUpdate() {
	}

	public void onDelete() {
	}

	private static final com.eibus.util.logger.CordysLogger logger = CordysLogger.getCordysLogger(MSIG_CMS_APP.class);

	private static String errorMessage;

	public static BusObjectIterator<MSIG_CMS_APP> fetchCases(int viewDefinition, int searchDefinition,
			int sortDefinition, String formName, String viewName, Cursor cursor) throws MSIG_CMS_EXCEPTION {

		MSIG_CMS_UTILS utils = new MSIG_CMS_UTILS();
		String queryText = null, columns = null, searchClause = null, orderByClause = null, defaultQueryString = "";
		QueryObject query = null;
		Vector<QueryParameter> params = new Vector<QueryParameter>();
		QueryParameter qParam;
		
		String tableName = Node.getAttribute(viewDefinition, "view");
		errorMessage = "Invalid Table Name";
		if(StringUtils.isBlank(tableName)) {
			throw new MSIG_CMS_EXCEPTION(errorMessage);
		}
		
		String countIdentifier = Node.getAttribute(viewDefinition, "countIdentifier");
		if(StringUtils.isBlank(countIdentifier)) {
			countIdentifier = "1";
		}
		
		// Fetch User Id
		String userId = MSIG_CMS_UTILS.getUserName();
		
		errorMessage = "Invalid User";
		// User Check
		if (StringUtils.isBlank(userId)) {
			throw new MSIG_CMS_EXCEPTION(errorMessage);
		}

		int[] iFieldNodes = utils.fetchFieldsFromDefinition(viewDefinition);

		errorMessage = "Error Occured during query preparation";
		if (iFieldNodes != null) {
			columns = utils.frameColumnsInQuery(iFieldNodes); // prepare select column for the query
		} else {
			throw new MSIG_CMS_EXCEPTION(errorMessage);
		}

		errorMessage = "Error occured while framing query";
		defaultQueryString = utils.prepareDefaultQueryString(params, formName, viewName, userId);

		if (StringUtils.isBlank(defaultQueryString)) {
			throw new MSIG_CMS_EXCEPTION(errorMessage);
		}

		int[] isearchFieldNodes = XPath.getMatchingNodes(".//criteria", null, searchDefinition);

		// Prepare the order by clause
		orderByClause = utils.formOrderByClause(sortDefinition);

		try {
			if (isearchFieldNodes.length > 0 && isearchFieldNodes != null) {
				searchClause = utils.fetchSearchFields(params, isearchFieldNodes);
			}
			
			queryText = "SELECT COUNT(" + countIdentifier + ") OVER() totalRecords, * FROM" + " ( SELECT " + columns
					+ " FROM " + tableName + defaultQueryString + ") AS cases ";

			if (StringUtils.isNotBlank(searchClause)) {
				queryText = queryText + "WHERE " + searchClause;
			}

			if (StringUtils.isNotBlank(orderByClause)) {
				queryText = queryText + orderByClause;
			}

			query = new QueryObject(queryText);
			query.setCursor(cursor);

			Iterator<QueryParameter> itr = params.iterator();
			while (itr.hasNext()) {
				qParam = itr.next();
				query.addParameter(qParam.getParameterName(), qParam.getColumnName(), qParam.getType(), qParam.getValue());
			}

			errorMessage = "Error occured while retrieving data from database.";
			return query.getObjects();

		} catch (NullPointerException e) {
			if (query != null)
				logger.error(errorMessage + query.getXqyQuery() + formName + "-CMS", e);
			else
				logger.error(errorMessage + formName + "-CMS", e);
			throw new MSIG_CMS_EXCEPTION(errorMessage, e);
		} catch (XqyException e) {
			if (query != null)
				logger.error(errorMessage + query.getXqyQuery() + formName + "-CMS", e);
			else
				logger.error(errorMessage + formName + "-CMS", e);
			throw new MSIG_CMS_EXCEPTION(errorMessage, e);
		} catch (Exception e) {
			if (query != null)
				logger.error(errorMessage + query.getXqyQuery() + formName + "-CMS", e);
			else
				logger.error(errorMessage + formName + "-CMS", e);
			throw new MSIG_CMS_EXCEPTION(errorMessage, e);
		} finally {
			viewDefinition = 0;
			searchDefinition = 0;
			sortDefinition = 0;
			orderByClause = null;
			searchClause = null;
			errorMessage = null;
		}
	}
}
