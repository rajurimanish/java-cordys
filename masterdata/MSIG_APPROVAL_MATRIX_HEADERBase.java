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
import com.cordys.cpc.bsf.listeners.constraint.StringValidator;


public abstract class MSIG_APPROVAL_MATRIX_HEADERBase extends com.cordys.cpc.bsf.busobject.StateBusObject
{
    // tags used in the XML document
    public final static String ATTR_APR_MATRIX_HDR_ID = "APR_MATRIX_HDR_ID";
    public final static String ATTR_APPLICATION = "APPLICATION";
    public final static String ATTR_BRANCH_CODE = "BRANCH_CODE";
    public final static String ATTR_LOB_CODE = "LOB_CODE";
    public final static String ATTR_PRODUCT_CODE = "PRODUCT_CODE";
    public final static String ATTR_BUSINESS_FUNCTION = "BUSINESS_FUNCTION";
    public final static String ATTR_AM_STATUS = "AM_STATUS";
    public final static String ATTR_CREATED_BY = "CREATED_BY";
    public final static String ATTR_CREATED_ON = "CREATED_ON";
    public final static String ATTR_MODIFIED_BY = "MODIFIED_BY";
    public final static String ATTR_MODIFIED_ON = "MODIFIED_ON";
    private final static String REL_MSIG_APPROVAL_MATRIX_LINESObjects = "FK:MSIG_APPROVAL_MATRIX_HEADER[APR_MATRIX_HDR_ID]:MSIG_APPROVAL_MATRIX_LINES[APR_MATRIX_HDR_ID]";
    private static ClassInfo s_classInfo = null;
    public static ClassInfo _getClassInfo()//NOPMD framework ensures this is thread safe
    {
        if ( s_classInfo == null )//NOPMD
        {
            s_classInfo = newClassInfo(MSIG_APPROVAL_MATRIX_HEADER.class);
            s_classInfo.setTableName("MSIG_APPROVAL_MATRIX_HEADER");
            s_classInfo.setUIDElements(new String[]{ATTR_BRANCH_CODE, ATTR_LOB_CODE, ATTR_PRODUCT_CODE, ATTR_BUSINESS_FUNCTION});
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
                AttributeInfo ai = new AttributeInfo(ATTR_APPLICATION);
                ai.setJavaName(ATTR_APPLICATION);
                ai.setColumnName(ATTR_APPLICATION);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_APPLICATION);
                v.setMaxLength(10);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_BRANCH_CODE);
                ai.setJavaName(ATTR_BRANCH_CODE);
                ai.setColumnName(ATTR_BRANCH_CODE);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_BRANCH_CODE);
                v.setMaxLength(150);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_LOB_CODE);
                ai.setJavaName(ATTR_LOB_CODE);
                ai.setColumnName(ATTR_LOB_CODE);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_LOB_CODE);
                v.setMaxLength(150);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_PRODUCT_CODE);
                ai.setJavaName(ATTR_PRODUCT_CODE);
                ai.setColumnName(ATTR_PRODUCT_CODE);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_PRODUCT_CODE);
                v.setMaxLength(20);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_BUSINESS_FUNCTION);
                ai.setJavaName(ATTR_BUSINESS_FUNCTION);
                ai.setColumnName(ATTR_BUSINESS_FUNCTION);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_BUSINESS_FUNCTION);
                v.setMaxLength(50);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_AM_STATUS);
                ai.setJavaName(ATTR_AM_STATUS);
                ai.setColumnName(ATTR_AM_STATUS);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_AM_STATUS);
                v.setMaxLength(10);
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
                // relation MSIG_APPROVAL_MATRIX_LINESObjects (FK:MSIG_APPROVAL_MATRIX_HEADER[APR_MATRIX_HDR_ID]:MSIG_APPROVAL_MATRIX_LINES[APR_MATRIX_HDR_ID])
                RelationInfo_FK ri = new RelationInfo_FK(REL_MSIG_APPROVAL_MATRIX_LINESObjects);
                ri.setName("MSIG_APPROVAL_MATRIX_LINESObjects");
                ri.setLocalAttributes(new String[]{ATTR_APR_MATRIX_HDR_ID});
                ri.setLocalIsPK(true);
                ri.setRelatedClass(com.msig.masterdata.MSIG_APPROVAL_MATRIX_LINES.class);
                ri.setRelatedAttributes(new String[]{"APR_MATRIX_HDR_ID"});//NOPMD
                ri.setRelatedIdentifier("FK:MSIG_APPROVAL_MATRIX_LINES[APR_MATRIX_HDR_ID]:MSIG_APPROVAL_MATRIX_HEADER[APR_MATRIX_HDR_ID]");
                ri.setLoadMethod("loadMSIG_APPROVAL_MATRIX_LINESObjects");
                s_classInfo.addRelationInfo(ri);
            }
        }
        return s_classInfo;
    }

    public MSIG_APPROVAL_MATRIX_HEADERBase(BusObjectConfig config)
    {
        super(config);
    }

    public String getAPR_MATRIX_HDR_ID()
    {
        return getStringProperty(ATTR_APR_MATRIX_HDR_ID);
    }

    public void setAPR_MATRIX_HDR_ID(String value)
    {
        setProperty(ATTR_APR_MATRIX_HDR_ID, value, 0);
    }

    public String getAPPLICATION()
    {
        return getStringProperty(ATTR_APPLICATION);
    }

    public void setAPPLICATION(String value)
    {
        setProperty(ATTR_APPLICATION, value, 0);
    }

    public String getBRANCH_CODE()
    {
        return getStringProperty(ATTR_BRANCH_CODE);
    }

    public void setBRANCH_CODE(String value)
    {
        setProperty(ATTR_BRANCH_CODE, value, 0);
    }

    public String getLOB_CODE()
    {
        return getStringProperty(ATTR_LOB_CODE);
    }

    public void setLOB_CODE(String value)
    {
        setProperty(ATTR_LOB_CODE, value, 0);
    }

    public String getPRODUCT_CODE()
    {
        return getStringProperty(ATTR_PRODUCT_CODE);
    }

    public void setPRODUCT_CODE(String value)
    {
        setProperty(ATTR_PRODUCT_CODE, value, 0);
    }

    public String getBUSINESS_FUNCTION()
    {
        return getStringProperty(ATTR_BUSINESS_FUNCTION);
    }

    public void setBUSINESS_FUNCTION(String value)
    {
        setProperty(ATTR_BUSINESS_FUNCTION, value, 0);
    }

    public String getAM_STATUS()
    {
        return getStringProperty(ATTR_AM_STATUS);
    }

    public void setAM_STATUS(String value)
    {
        setProperty(ATTR_AM_STATUS, value, 0);
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

    public BusObjectIterator<MSIG_APPROVAL_MATRIX_LINES> getMSIG_APPROVAL_MATRIX_LINESObjects()
    {
        return getMultiRelationObjects(REL_MSIG_APPROVAL_MATRIX_LINESObjects);
    }
    public BusObjectIterator<MSIG_APPROVAL_MATRIX_LINES> loadMSIG_APPROVAL_MATRIX_LINESObjects()
    {
        String queryText = "select * from \"MSIG_APPROVAL_MATRIX_LINES\" where \"APR_MATRIX_HDR_ID\" = :APR_MATRIX_HDR_ID";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APR_MATRIX_HDR_ID", "MSIG_APPROVAL_MATRIX_LINES.APR_MATRIX_HDR_ID", QueryObject.PARAM_STRING, getAPR_MATRIX_HDR_ID());//NOPMD
        query.setResultClass(MSIG_APPROVAL_MATRIX_LINES.class);
        return query.getObjects();
    }


    public void addMSIG_APPROVAL_MATRIX_LINESObject(MSIG_APPROVAL_MATRIX_LINES a_MSIG_APPROVAL_MATRIX_LINES)
    {
        a_MSIG_APPROVAL_MATRIX_LINES.setAPR_MATRIX_HDR_ID(this.getAPR_MATRIX_HDR_ID());
    }

    public void removeMSIG_APPROVAL_MATRIX_LINESObject(MSIG_APPROVAL_MATRIX_LINES a_MSIG_APPROVAL_MATRIX_LINES)
    {
        a_MSIG_APPROVAL_MATRIX_LINES.setNull("APR_MATRIX_HDR_ID");//NOPMD
    }



    public static com.msig.masterdata.MSIG_APPROVAL_MATRIX_HEADER getMsigApprovalMatrixHeaderObject(String BRANCH_CODE, String LOB_CODE, String PRODUCT_CODE, String BUSINESS_FUNCTION)
    {
        String queryText = "select * from \"MSIG_APPROVAL_MATRIX_HEADER\" where \"BRANCH_CODE\" = :BRANCH_CODE and \"LOB_CODE\" = :LOB_CODE and \"PRODUCT_CODE\" = :PRODUCT_CODE and \"BUSINESS_FUNCTION\" = :BUSINESS_FUNCTION";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("BRANCH_CODE", "MSIG_APPROVAL_MATRIX_HEADER.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);//NOPMD
        query.addParameter("LOB_CODE", "MSIG_APPROVAL_MATRIX_HEADER.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);//NOPMD
        query.addParameter("PRODUCT_CODE", "MSIG_APPROVAL_MATRIX_HEADER.PRODUCT_CODE", QueryObject.PARAM_STRING, PRODUCT_CODE);//NOPMD
        query.addParameter("BUSINESS_FUNCTION", "MSIG_APPROVAL_MATRIX_HEADER.BUSINESS_FUNCTION", QueryObject.PARAM_STRING, BUSINESS_FUNCTION);//NOPMD
        query.setResultClass(MSIG_APPROVAL_MATRIX_HEADER.class);
        return (MSIG_APPROVAL_MATRIX_HEADER)query.getObject();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_APPROVAL_MATRIX_HEADER> getMsigApprovalMatrixHeaderObjects(String fromBRANCH_CODE, String toBRANCH_CODE, String fromLOB_CODE, String toLOB_CODE, String fromPRODUCT_CODE, String toPRODUCT_CODE, String fromBUSINESS_FUNCTION, String toBUSINESS_FUNCTION, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_APPROVAL_MATRIX_HEADER\" where \"BRANCH_CODE\" between :fromBRANCH_CODE and :toBRANCH_CODE and \"LOB_CODE\" between :fromLOB_CODE and :toLOB_CODE and \"PRODUCT_CODE\" between :fromPRODUCT_CODE and :toPRODUCT_CODE and \"BUSINESS_FUNCTION\" between :fromBUSINESS_FUNCTION and :toBUSINESS_FUNCTION";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("fromBRANCH_CODE", "MSIG_APPROVAL_MATRIX_HEADER.BRANCH_CODE", QueryObject.PARAM_STRING, fromBRANCH_CODE);
        query.addParameter("toBRANCH_CODE", "MSIG_APPROVAL_MATRIX_HEADER.BRANCH_CODE", QueryObject.PARAM_STRING, toBRANCH_CODE);
        query.addParameter("fromLOB_CODE", "MSIG_APPROVAL_MATRIX_HEADER.LOB_CODE", QueryObject.PARAM_STRING, fromLOB_CODE);
        query.addParameter("toLOB_CODE", "MSIG_APPROVAL_MATRIX_HEADER.LOB_CODE", QueryObject.PARAM_STRING, toLOB_CODE);
        query.addParameter("fromPRODUCT_CODE", "MSIG_APPROVAL_MATRIX_HEADER.PRODUCT_CODE", QueryObject.PARAM_STRING, fromPRODUCT_CODE);
        query.addParameter("toPRODUCT_CODE", "MSIG_APPROVAL_MATRIX_HEADER.PRODUCT_CODE", QueryObject.PARAM_STRING, toPRODUCT_CODE);
        query.addParameter("fromBUSINESS_FUNCTION", "MSIG_APPROVAL_MATRIX_HEADER.BUSINESS_FUNCTION", QueryObject.PARAM_STRING, fromBUSINESS_FUNCTION);
        query.addParameter("toBUSINESS_FUNCTION", "MSIG_APPROVAL_MATRIX_HEADER.BUSINESS_FUNCTION", QueryObject.PARAM_STRING, toBUSINESS_FUNCTION);
        query.setResultClass(MSIG_APPROVAL_MATRIX_HEADER.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_APPROVAL_MATRIX_HEADER> getMsigApprovalMatrixHeaderObjects(String fromBRANCH_CODE, String toBRANCH_CODE, String fromLOB_CODE, String toLOB_CODE, String fromPRODUCT_CODE, String toPRODUCT_CODE, String fromBUSINESS_FUNCTION, String toBUSINESS_FUNCTION)
    {
        String queryText = "select * from \"MSIG_APPROVAL_MATRIX_HEADER\" where \"BRANCH_CODE\" between :fromBRANCH_CODE and :toBRANCH_CODE and \"LOB_CODE\" between :fromLOB_CODE and :toLOB_CODE and \"PRODUCT_CODE\" between :fromPRODUCT_CODE and :toPRODUCT_CODE and \"BUSINESS_FUNCTION\" between :fromBUSINESS_FUNCTION and :toBUSINESS_FUNCTION";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("fromBRANCH_CODE", "MSIG_APPROVAL_MATRIX_HEADER.BRANCH_CODE", QueryObject.PARAM_STRING, fromBRANCH_CODE);
        query.addParameter("toBRANCH_CODE", "MSIG_APPROVAL_MATRIX_HEADER.BRANCH_CODE", QueryObject.PARAM_STRING, toBRANCH_CODE);
        query.addParameter("fromLOB_CODE", "MSIG_APPROVAL_MATRIX_HEADER.LOB_CODE", QueryObject.PARAM_STRING, fromLOB_CODE);
        query.addParameter("toLOB_CODE", "MSIG_APPROVAL_MATRIX_HEADER.LOB_CODE", QueryObject.PARAM_STRING, toLOB_CODE);
        query.addParameter("fromPRODUCT_CODE", "MSIG_APPROVAL_MATRIX_HEADER.PRODUCT_CODE", QueryObject.PARAM_STRING, fromPRODUCT_CODE);
        query.addParameter("toPRODUCT_CODE", "MSIG_APPROVAL_MATRIX_HEADER.PRODUCT_CODE", QueryObject.PARAM_STRING, toPRODUCT_CODE);
        query.addParameter("fromBUSINESS_FUNCTION", "MSIG_APPROVAL_MATRIX_HEADER.BUSINESS_FUNCTION", QueryObject.PARAM_STRING, fromBUSINESS_FUNCTION);
        query.addParameter("toBUSINESS_FUNCTION", "MSIG_APPROVAL_MATRIX_HEADER.BUSINESS_FUNCTION", QueryObject.PARAM_STRING, toBUSINESS_FUNCTION);
        query.setResultClass(MSIG_APPROVAL_MATRIX_HEADER.class);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_APPROVAL_MATRIX_HEADER> getNextMsigApprovalMatrixHeaderObjects(String BRANCH_CODE, String LOB_CODE, String PRODUCT_CODE, String BUSINESS_FUNCTION, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_APPROVAL_MATRIX_HEADER\" where (\"BRANCH_CODE\" > :BRANCH_CODE) or (\"BRANCH_CODE\" = :BRANCH_CODE and \"LOB_CODE\" > :LOB_CODE) or (\"BRANCH_CODE\" = :BRANCH_CODE and \"LOB_CODE\" = :LOB_CODE and \"PRODUCT_CODE\" > :PRODUCT_CODE) or (\"BRANCH_CODE\" = :BRANCH_CODE and \"LOB_CODE\" = :LOB_CODE and \"PRODUCT_CODE\" = :PRODUCT_CODE and \"BUSINESS_FUNCTION\" > :BUSINESS_FUNCTION) order by \"BRANCH_CODE\" asc, \"LOB_CODE\" asc, \"PRODUCT_CODE\" asc, \"BUSINESS_FUNCTION\" asc";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("BRANCH_CODE", "MSIG_APPROVAL_MATRIX_HEADER.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);//NOPMD
        query.addParameter("LOB_CODE", "MSIG_APPROVAL_MATRIX_HEADER.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);//NOPMD
        query.addParameter("PRODUCT_CODE", "MSIG_APPROVAL_MATRIX_HEADER.PRODUCT_CODE", QueryObject.PARAM_STRING, PRODUCT_CODE);//NOPMD
        query.addParameter("BUSINESS_FUNCTION", "MSIG_APPROVAL_MATRIX_HEADER.BUSINESS_FUNCTION", QueryObject.PARAM_STRING, BUSINESS_FUNCTION);//NOPMD
        query.setResultClass(MSIG_APPROVAL_MATRIX_HEADER.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_APPROVAL_MATRIX_HEADER> getPreviousMsigApprovalMatrixHeaderObjects(String BRANCH_CODE, String LOB_CODE, String PRODUCT_CODE, String BUSINESS_FUNCTION, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_APPROVAL_MATRIX_HEADER\" where (\"BRANCH_CODE\" < :BRANCH_CODE) or (\"BRANCH_CODE\" = :BRANCH_CODE and \"LOB_CODE\" < :LOB_CODE) or (\"BRANCH_CODE\" = :BRANCH_CODE and \"LOB_CODE\" = :LOB_CODE and \"PRODUCT_CODE\" < :PRODUCT_CODE) or (\"BRANCH_CODE\" = :BRANCH_CODE and \"LOB_CODE\" = :LOB_CODE and \"PRODUCT_CODE\" = :PRODUCT_CODE and \"BUSINESS_FUNCTION\" < :BUSINESS_FUNCTION) order by \"BRANCH_CODE\" desc, \"LOB_CODE\" desc, \"PRODUCT_CODE\" desc, \"BUSINESS_FUNCTION\" desc";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("BRANCH_CODE", "MSIG_APPROVAL_MATRIX_HEADER.BRANCH_CODE", QueryObject.PARAM_STRING, BRANCH_CODE);//NOPMD
        query.addParameter("LOB_CODE", "MSIG_APPROVAL_MATRIX_HEADER.LOB_CODE", QueryObject.PARAM_STRING, LOB_CODE);//NOPMD
        query.addParameter("PRODUCT_CODE", "MSIG_APPROVAL_MATRIX_HEADER.PRODUCT_CODE", QueryObject.PARAM_STRING, PRODUCT_CODE);//NOPMD
        query.addParameter("BUSINESS_FUNCTION", "MSIG_APPROVAL_MATRIX_HEADER.BUSINESS_FUNCTION", QueryObject.PARAM_STRING, BUSINESS_FUNCTION);//NOPMD
        query.setResultClass(MSIG_APPROVAL_MATRIX_HEADER.class);
        query.setCursor(cursor);
        return query.getObjects();
    }


}
