/*
  This class has been generated by the Code Generator
*/

package com.msig.masterdata;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.QueryObject;
import com.cordys.cpc.bsf.classinfo.AttributeInfo;
import com.cordys.cpc.bsf.classinfo.ClassInfo;
import com.cordys.cpc.bsf.classinfo.RelationInfo_FK;
import com.cordys.cpc.bsf.listeners.constraint.NumberValidator;
import com.cordys.cpc.bsf.listeners.constraint.StringValidator;


public abstract class MSIG_APPROVAL_MATRIX_LINESBase extends com.cordys.cpc.bsf.busobject.StateBusObject
{
    // tags used in the XML document
    public final static String ATTR_APR_MATRIX_HDR_ID = "APR_MATRIX_HDR_ID";
    public final static String ATTR_LEVEL = "LEVEL";
    public final static String ATTR_ROLE_CODE = "ROLE_CODE";
    public final static String ATTR_ACTION = "ACTION";
    public final static String ATTR_ROUTING_ORDER = "ROUTING_ORDER";
    public final static String ATTR_RULE_ENABLED = "RULE_ENABLED";
    public final static String ATTR_RULE_NAME = "RULE_NAME";
    public final static String ATTR_RULE_XML = "RULE_XML";
    public final static String ATTR_RULE_XPATH_EXPRESSION = "RULE_XPATH_EXPRESSION";
    public final static String ATTR_CREATED_BY = "CREATED_BY";
    public final static String ATTR_CREATED_ON = "CREATED_ON";
    public final static String ATTR_MODIFIED_BY = "MODIFIED_BY";
    public final static String ATTR_MODIFIED_ON = "MODIFIED_ON";
    private final static String REL_APR_MATRIX_HDR_IDObject = "FK:MSIG_APPROVAL_MATRIX_LINES[APR_MATRIX_HDR_ID]:MSIG_APPROVAL_MATRIX_HEADER[APR_MATRIX_HDR_ID]";
    private static ClassInfo s_classInfo = null;
    public static ClassInfo _getClassInfo()//NOPMD framework ensures this is thread safe
    {
        if ( s_classInfo == null )//NOPMD
        {
            s_classInfo = newClassInfo(MSIG_APPROVAL_MATRIX_LINES.class);
            s_classInfo.setTableName("MSIG_APPROVAL_MATRIX_LINES");
            s_classInfo.setUIDElements(new String[]{ATTR_APR_MATRIX_HDR_ID, ATTR_LEVEL, ATTR_ROLE_CODE, ATTR_ACTION});
            {
                AttributeInfo ai = new AttributeInfo(ATTR_APR_MATRIX_HDR_ID);
                ai.setJavaName(ATTR_APR_MATRIX_HDR_ID);
                ai.setColumnName(ATTR_APR_MATRIX_HDR_ID);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_APR_MATRIX_HDR_ID);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_LEVEL);
                ai.setJavaName(ATTR_LEVEL);
                ai.setColumnName(ATTR_LEVEL);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_LEVEL);
                v.setMaxLength(20);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_ROLE_CODE);
                ai.setJavaName(ATTR_ROLE_CODE);
                ai.setColumnName(ATTR_ROLE_CODE);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_ROLE_CODE);
                v.setMaxLength(20);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_ACTION);
                ai.setJavaName(ATTR_ACTION);
                ai.setColumnName(ATTR_ACTION);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_ACTION);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_ROUTING_ORDER);
                ai.setJavaName(ATTR_ROUTING_ORDER);
                ai.setColumnName(ATTR_ROUTING_ORDER);
                ai.setAttributeClass(int.class);
                NumberValidator v = new NumberValidator(ATTR_ROUTING_ORDER);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_RULE_ENABLED);
                ai.setJavaName(ATTR_RULE_ENABLED);
                ai.setColumnName(ATTR_RULE_ENABLED);
                ai.setAttributeClass(boolean.class);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_RULE_NAME);
                ai.setJavaName(ATTR_RULE_NAME);
                ai.setColumnName(ATTR_RULE_NAME);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_RULE_NAME);
                v.setMaxLength(100);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_RULE_XML);
                ai.setJavaName(ATTR_RULE_XML);
                ai.setColumnName(ATTR_RULE_XML);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_RULE_XML);
                v.setMaxLength(2147483647);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_RULE_XPATH_EXPRESSION);
                ai.setJavaName(ATTR_RULE_XPATH_EXPRESSION);
                ai.setColumnName(ATTR_RULE_XPATH_EXPRESSION);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_RULE_XPATH_EXPRESSION);
                v.setMaxLength(2147483647);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_CREATED_BY);
                ai.setJavaName(ATTR_CREATED_BY);
                ai.setColumnName(ATTR_CREATED_BY);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_CREATED_BY);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_CREATED_ON);
                ai.setJavaName(ATTR_CREATED_ON);
                ai.setColumnName(ATTR_CREATED_ON);
                ai.setAttributeClass(java.util.Date.class);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_MODIFIED_BY);
                ai.setJavaName(ATTR_MODIFIED_BY);
                ai.setColumnName(ATTR_MODIFIED_BY);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_MODIFIED_BY);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_MODIFIED_ON);
                ai.setJavaName(ATTR_MODIFIED_ON);
                ai.setColumnName(ATTR_MODIFIED_ON);
                ai.setAttributeClass(java.util.Date.class);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                // relation APR_MATRIX_HDR_IDObject (FK:MSIG_APPROVAL_MATRIX_LINES[APR_MATRIX_HDR_ID]:MSIG_APPROVAL_MATRIX_HEADER[APR_MATRIX_HDR_ID])
                RelationInfo_FK ri = new RelationInfo_FK(REL_APR_MATRIX_HDR_IDObject);
                ri.setName("APR_MATRIX_HDR_IDObject");
                ri.setLocalAttributes(new String[]{ATTR_APR_MATRIX_HDR_ID});
                ri.setLocalIsPK(false);
                ri.setRelatedClass(com.msig.masterdata.MSIG_APPROVAL_MATRIX_HEADER.class);
                ri.setRelatedAttributes(new String[]{"APR_MATRIX_HDR_ID"});//NOPMD
                ri.setRelatedIdentifier("FK:MSIG_APPROVAL_MATRIX_HEADER[APR_MATRIX_HDR_ID]:MSIG_APPROVAL_MATRIX_LINES[APR_MATRIX_HDR_ID]");
                ri.setLoadMethod("loadAPR_MATRIX_HDR_IDObject");
                s_classInfo.addRelationInfo(ri);
            }
        }
        return s_classInfo;
    }

    public MSIG_APPROVAL_MATRIX_LINESBase(BusObjectConfig config)
    {
        super(config);
    }

    public String getAPR_MATRIX_HDR_ID()
    {
        return getStringProperty(ATTR_APR_MATRIX_HDR_ID);
    }

    public void setAPR_MATRIX_HDR_ID(String value)
    {
        String[] relations = new String[]{REL_APR_MATRIX_HDR_IDObject};
        this.updateSingleRelations(relations, false);
        setProperty(ATTR_APR_MATRIX_HDR_ID, value, 0);
        this.updateSingleRelations(relations, true);
    }

    public String getLEVEL()
    {
        return getStringProperty(ATTR_LEVEL);
    }

    public void setLEVEL(String value)
    {
        setProperty(ATTR_LEVEL, value, 0);
    }

    public String getROLE_CODE()
    {
        return getStringProperty(ATTR_ROLE_CODE);
    }

    public void setROLE_CODE(String value)
    {
        setProperty(ATTR_ROLE_CODE, value, 0);
    }

    public String getACTION()
    {
        return getStringProperty(ATTR_ACTION);
    }

    public void setACTION(String value)
    {
        setProperty(ATTR_ACTION, value, 0);
    }

    public int getROUTING_ORDER()
    {
        return getIntProperty(ATTR_ROUTING_ORDER);
    }

    public void setROUTING_ORDER(int value)
    {
        setProperty(ATTR_ROUTING_ORDER, value, 0);
    }

    public boolean getRULE_ENABLED()
    {
        return getBooleanProperty(ATTR_RULE_ENABLED);
    }

    public void setRULE_ENABLED(boolean value)
    {
        setProperty(ATTR_RULE_ENABLED, value, 0);
    }

    public String getRULE_NAME()
    {
        return getStringProperty(ATTR_RULE_NAME);
    }

    public void setRULE_NAME(String value)
    {
        setProperty(ATTR_RULE_NAME, value, 0);
    }

    public String getRULE_XML()
    {
        return getStringProperty(ATTR_RULE_XML);
    }

    public void setRULE_XML(String value)
    {
        setProperty(ATTR_RULE_XML, value, 0);
    }

    public String getRULE_XPATH_EXPRESSION()
    {
        return getStringProperty(ATTR_RULE_XPATH_EXPRESSION);
    }

    public void setRULE_XPATH_EXPRESSION(String value)
    {
        setProperty(ATTR_RULE_XPATH_EXPRESSION, value, 0);
    }

    public String getCREATED_BY()
    {
        return getStringProperty(ATTR_CREATED_BY);
    }

    public void setCREATED_BY(String value)
    {
        setProperty(ATTR_CREATED_BY, value, 0);
    }

    public java.util.Date getCREATED_ON()
    {
        return getDateTimestampProperty(ATTR_CREATED_ON);
    }

    public void setCREATED_ON(java.util.Date value)
    {
        setProperty(ATTR_CREATED_ON, value, 0);
    }

    public String getMODIFIED_BY()
    {
        return getStringProperty(ATTR_MODIFIED_BY);
    }

    public void setMODIFIED_BY(String value)
    {
        setProperty(ATTR_MODIFIED_BY, value, 0);
    }

    public java.util.Date getMODIFIED_ON()
    {
        return getDateTimestampProperty(ATTR_MODIFIED_ON);
    }

    public void setMODIFIED_ON(java.util.Date value)
    {
        setProperty(ATTR_MODIFIED_ON, value, 0);
    }

    public MSIG_APPROVAL_MATRIX_HEADER getAPR_MATRIX_HDR_IDObject()
    {
        return (MSIG_APPROVAL_MATRIX_HEADER)getSingleRelationObject(REL_APR_MATRIX_HDR_IDObject);
    }
    public MSIG_APPROVAL_MATRIX_HEADER loadAPR_MATRIX_HDR_IDObject()
    {
        String queryText = "select * from \"MSIG_APPROVAL_MATRIX_HEADER\" where \"APR_MATRIX_HDR_ID\" = :APR_MATRIX_HDR_ID";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APR_MATRIX_HDR_ID", "MSIG_APPROVAL_MATRIX_HEADER.APR_MATRIX_HDR_ID", QueryObject.PARAM_STRING, getAPR_MATRIX_HDR_ID());//NOPMD
        query.setResultClass(MSIG_APPROVAL_MATRIX_HEADER.class);
        return (MSIG_APPROVAL_MATRIX_HEADER)query.getObject();
    }


    public void setAPR_MATRIX_HDR_IDObject(MSIG_APPROVAL_MATRIX_HEADER a_MSIG_APPROVAL_MATRIX_HEADER)
    {
        if (a_MSIG_APPROVAL_MATRIX_HEADER == null)
        {
            this.setNull("APR_MATRIX_HDR_ID");
        }
        else
        {
            this.setAPR_MATRIX_HDR_ID(a_MSIG_APPROVAL_MATRIX_HEADER.getAPR_MATRIX_HDR_ID());
        }
    }

    public void setNull(String element)
    {
        super.setNull(element);
        if (ATTR_APR_MATRIX_HDR_ID.equals(element))
        {
            this.updateSingleRelation(REL_APR_MATRIX_HDR_IDObject, false);
        }
    }





    public static com.msig.masterdata.MSIG_APPROVAL_MATRIX_LINES getMsigApprovalMatrixLinesObject(String APR_MATRIX_HDR_ID, String LEVEL, String ROLE_CODE, String ACTION)
    {
        String queryText = "select * from \"MSIG_APPROVAL_MATRIX_LINES\" where \"APR_MATRIX_HDR_ID\" = :APR_MATRIX_HDR_ID and \"LEVEL\" = :LEVEL and \"ROLE_CODE\" = :ROLE_CODE and \"ACTION\" = :ACTION";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APR_MATRIX_HDR_ID", "MSIG_APPROVAL_MATRIX_LINES.APR_MATRIX_HDR_ID", QueryObject.PARAM_STRING, APR_MATRIX_HDR_ID);//NOPMD
        query.addParameter("LEVEL", "MSIG_APPROVAL_MATRIX_LINES.LEVEL", QueryObject.PARAM_STRING, LEVEL);//NOPMD
        query.addParameter("ROLE_CODE", "MSIG_APPROVAL_MATRIX_LINES.ROLE_CODE", QueryObject.PARAM_STRING, ROLE_CODE);//NOPMD
        query.addParameter("ACTION", "MSIG_APPROVAL_MATRIX_LINES.ACTION", QueryObject.PARAM_STRING, ACTION);//NOPMD
        query.setResultClass(MSIG_APPROVAL_MATRIX_LINES.class);
        return (MSIG_APPROVAL_MATRIX_LINES)query.getObject();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_APPROVAL_MATRIX_LINES> getMsigApprovalMatrixLinesObjects(String fromAPR_MATRIX_HDR_ID, String toAPR_MATRIX_HDR_ID, String fromLEVEL, String toLEVEL, String fromROLE_CODE, String toROLE_CODE, String fromACTION, String toACTION, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_APPROVAL_MATRIX_LINES\" where \"APR_MATRIX_HDR_ID\" between :fromAPR_MATRIX_HDR_ID and :toAPR_MATRIX_HDR_ID and \"LEVEL\" between :fromLEVEL and :toLEVEL and \"ROLE_CODE\" between :fromROLE_CODE and :toROLE_CODE and \"ACTION\" between :fromACTION and :toACTION";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("fromAPR_MATRIX_HDR_ID", "MSIG_APPROVAL_MATRIX_LINES.APR_MATRIX_HDR_ID", QueryObject.PARAM_STRING, fromAPR_MATRIX_HDR_ID);
        query.addParameter("toAPR_MATRIX_HDR_ID", "MSIG_APPROVAL_MATRIX_LINES.APR_MATRIX_HDR_ID", QueryObject.PARAM_STRING, toAPR_MATRIX_HDR_ID);
        query.addParameter("fromLEVEL", "MSIG_APPROVAL_MATRIX_LINES.LEVEL", QueryObject.PARAM_STRING, fromLEVEL);
        query.addParameter("toLEVEL", "MSIG_APPROVAL_MATRIX_LINES.LEVEL", QueryObject.PARAM_STRING, toLEVEL);
        query.addParameter("fromROLE_CODE", "MSIG_APPROVAL_MATRIX_LINES.ROLE_CODE", QueryObject.PARAM_STRING, fromROLE_CODE);
        query.addParameter("toROLE_CODE", "MSIG_APPROVAL_MATRIX_LINES.ROLE_CODE", QueryObject.PARAM_STRING, toROLE_CODE);
        query.addParameter("fromACTION", "MSIG_APPROVAL_MATRIX_LINES.ACTION", QueryObject.PARAM_STRING, fromACTION);
        query.addParameter("toACTION", "MSIG_APPROVAL_MATRIX_LINES.ACTION", QueryObject.PARAM_STRING, toACTION);
        query.setResultClass(MSIG_APPROVAL_MATRIX_LINES.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_APPROVAL_MATRIX_LINES> getMsigApprovalMatrixLinesObjects(String fromAPR_MATRIX_HDR_ID, String toAPR_MATRIX_HDR_ID, String fromLEVEL, String toLEVEL, String fromROLE_CODE, String toROLE_CODE, String fromACTION, String toACTION)
    {
        String queryText = "select * from \"MSIG_APPROVAL_MATRIX_LINES\" where \"APR_MATRIX_HDR_ID\" between :fromAPR_MATRIX_HDR_ID and :toAPR_MATRIX_HDR_ID and \"LEVEL\" between :fromLEVEL and :toLEVEL and \"ROLE_CODE\" between :fromROLE_CODE and :toROLE_CODE and \"ACTION\" between :fromACTION and :toACTION";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("fromAPR_MATRIX_HDR_ID", "MSIG_APPROVAL_MATRIX_LINES.APR_MATRIX_HDR_ID", QueryObject.PARAM_STRING, fromAPR_MATRIX_HDR_ID);
        query.addParameter("toAPR_MATRIX_HDR_ID", "MSIG_APPROVAL_MATRIX_LINES.APR_MATRIX_HDR_ID", QueryObject.PARAM_STRING, toAPR_MATRIX_HDR_ID);
        query.addParameter("fromLEVEL", "MSIG_APPROVAL_MATRIX_LINES.LEVEL", QueryObject.PARAM_STRING, fromLEVEL);
        query.addParameter("toLEVEL", "MSIG_APPROVAL_MATRIX_LINES.LEVEL", QueryObject.PARAM_STRING, toLEVEL);
        query.addParameter("fromROLE_CODE", "MSIG_APPROVAL_MATRIX_LINES.ROLE_CODE", QueryObject.PARAM_STRING, fromROLE_CODE);
        query.addParameter("toROLE_CODE", "MSIG_APPROVAL_MATRIX_LINES.ROLE_CODE", QueryObject.PARAM_STRING, toROLE_CODE);
        query.addParameter("fromACTION", "MSIG_APPROVAL_MATRIX_LINES.ACTION", QueryObject.PARAM_STRING, fromACTION);
        query.addParameter("toACTION", "MSIG_APPROVAL_MATRIX_LINES.ACTION", QueryObject.PARAM_STRING, toACTION);
        query.setResultClass(MSIG_APPROVAL_MATRIX_LINES.class);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_APPROVAL_MATRIX_LINES> getMsigApprovalMatrixLinesObjectsForAprMatrixHdrId(String APR_MATRIX_HDR_ID)
    {
        String queryText = "select * from \"MSIG_APPROVAL_MATRIX_LINES\" where \"APR_MATRIX_HDR_ID\" = :APR_MATRIX_HDR_ID";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APR_MATRIX_HDR_ID", "MSIG_APPROVAL_MATRIX_LINES.APR_MATRIX_HDR_ID", QueryObject.PARAM_STRING, APR_MATRIX_HDR_ID);//NOPMD
        query.setResultClass(MSIG_APPROVAL_MATRIX_LINES.class);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_APPROVAL_MATRIX_LINES> getNextMsigApprovalMatrixLinesObjects(String APR_MATRIX_HDR_ID, String LEVEL, String ROLE_CODE, String ACTION, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_APPROVAL_MATRIX_LINES\" where (\"APR_MATRIX_HDR_ID\" > :APR_MATRIX_HDR_ID) or (\"APR_MATRIX_HDR_ID\" = :APR_MATRIX_HDR_ID and \"LEVEL\" > :LEVEL) or (\"APR_MATRIX_HDR_ID\" = :APR_MATRIX_HDR_ID and \"LEVEL\" = :LEVEL and \"ROLE_CODE\" > :ROLE_CODE) or (\"APR_MATRIX_HDR_ID\" = :APR_MATRIX_HDR_ID and \"LEVEL\" = :LEVEL and \"ROLE_CODE\" = :ROLE_CODE and \"ACTION\" > :ACTION) order by \"APR_MATRIX_HDR_ID\" asc, \"LEVEL\" asc, \"ROLE_CODE\" asc, \"ACTION\" asc";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APR_MATRIX_HDR_ID", "MSIG_APPROVAL_MATRIX_LINES.APR_MATRIX_HDR_ID", QueryObject.PARAM_STRING, APR_MATRIX_HDR_ID);//NOPMD
        query.addParameter("LEVEL", "MSIG_APPROVAL_MATRIX_LINES.LEVEL", QueryObject.PARAM_STRING, LEVEL);//NOPMD
        query.addParameter("ROLE_CODE", "MSIG_APPROVAL_MATRIX_LINES.ROLE_CODE", QueryObject.PARAM_STRING, ROLE_CODE);//NOPMD
        query.addParameter("ACTION", "MSIG_APPROVAL_MATRIX_LINES.ACTION", QueryObject.PARAM_STRING, ACTION);//NOPMD
        query.setResultClass(MSIG_APPROVAL_MATRIX_LINES.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_APPROVAL_MATRIX_LINES> getPreviousMsigApprovalMatrixLinesObjects(String APR_MATRIX_HDR_ID, String LEVEL, String ROLE_CODE, String ACTION, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_APPROVAL_MATRIX_LINES\" where (\"APR_MATRIX_HDR_ID\" < :APR_MATRIX_HDR_ID) or (\"APR_MATRIX_HDR_ID\" = :APR_MATRIX_HDR_ID and \"LEVEL\" < :LEVEL) or (\"APR_MATRIX_HDR_ID\" = :APR_MATRIX_HDR_ID and \"LEVEL\" = :LEVEL and \"ROLE_CODE\" < :ROLE_CODE) or (\"APR_MATRIX_HDR_ID\" = :APR_MATRIX_HDR_ID and \"LEVEL\" = :LEVEL and \"ROLE_CODE\" = :ROLE_CODE and \"ACTION\" < :ACTION) order by \"APR_MATRIX_HDR_ID\" desc, \"LEVEL\" desc, \"ROLE_CODE\" desc, \"ACTION\" desc";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APR_MATRIX_HDR_ID", "MSIG_APPROVAL_MATRIX_LINES.APR_MATRIX_HDR_ID", QueryObject.PARAM_STRING, APR_MATRIX_HDR_ID);//NOPMD
        query.addParameter("LEVEL", "MSIG_APPROVAL_MATRIX_LINES.LEVEL", QueryObject.PARAM_STRING, LEVEL);//NOPMD
        query.addParameter("ROLE_CODE", "MSIG_APPROVAL_MATRIX_LINES.ROLE_CODE", QueryObject.PARAM_STRING, ROLE_CODE);//NOPMD
        query.addParameter("ACTION", "MSIG_APPROVAL_MATRIX_LINES.ACTION", QueryObject.PARAM_STRING, ACTION);//NOPMD
        query.setResultClass(MSIG_APPROVAL_MATRIX_LINES.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

}
