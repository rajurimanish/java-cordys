/*
  This class has been generated by the Code Generator
*/

package com.msig.masterdata;

import com.cordys.cpc.bsf.busobject.BusObjectConfig;
import com.cordys.cpc.bsf.busobject.BusObjectIterator;
import com.cordys.cpc.bsf.busobject.QueryObject;
import com.cordys.cpc.bsf.classinfo.AttributeInfo;
import com.cordys.cpc.bsf.classinfo.ClassInfo;
import com.cordys.cpc.bsf.listeners.constraint.StringValidator;


public abstract class MSIG_DOCUMENT_TYPESBase extends com.cordys.cpc.bsf.busobject.StateBusObject
{
    // tags used in the XML document
    public final static String ATTR_APPLICATION = "APPLICATION";
    public final static String ATTR_DOCUMENT_LOB_CODE = "DOCUMENT_LOB_CODE";
    public final static String ATTR_DOCUMENT_TYPE = "DOCUMENT_TYPE";
    public final static String ATTR_DOCUMENT_NAME = "DOCUMENT_NAME";
    public final static String ATTR_IS_AUTO_PRIVATE = "IS_AUTO_PRIVATE";
    private static ClassInfo s_classInfo = null;
    public static ClassInfo _getClassInfo()//NOPMD framework ensures this is thread safe
    {
        if ( s_classInfo == null )//NOPMD
        {
            s_classInfo = newClassInfo(MSIG_DOCUMENT_TYPES.class);
            s_classInfo.setTableName("MSIG_DOCUMENT_TYPES");
            s_classInfo.setUIDElements(new String[]{ATTR_APPLICATION, ATTR_DOCUMENT_LOB_CODE, ATTR_DOCUMENT_TYPE, ATTR_DOCUMENT_NAME});
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
                AttributeInfo ai = new AttributeInfo(ATTR_DOCUMENT_LOB_CODE);
                ai.setJavaName(ATTR_DOCUMENT_LOB_CODE);
                ai.setColumnName(ATTR_DOCUMENT_LOB_CODE);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_DOCUMENT_LOB_CODE);
                v.setMaxLength(100);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_DOCUMENT_TYPE);
                ai.setJavaName(ATTR_DOCUMENT_TYPE);
                ai.setColumnName(ATTR_DOCUMENT_TYPE);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_DOCUMENT_TYPE);
                v.setMaxLength(20);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_DOCUMENT_NAME);
                ai.setJavaName(ATTR_DOCUMENT_NAME);
                ai.setColumnName(ATTR_DOCUMENT_NAME);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_DOCUMENT_NAME);
                v.setMaxLength(200);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
            {
                AttributeInfo ai = new AttributeInfo(ATTR_IS_AUTO_PRIVATE);
                ai.setJavaName(ATTR_IS_AUTO_PRIVATE);
                ai.setColumnName(ATTR_IS_AUTO_PRIVATE);
                ai.setAttributeClass(String.class);
                StringValidator v = new StringValidator(ATTR_IS_AUTO_PRIVATE);
                v.setMaxLength(1);
                ai.addConstraintHandler(v);
                s_classInfo.addAttributeInfo(ai);
            }
        }
        return s_classInfo;
    }

    public MSIG_DOCUMENT_TYPESBase(BusObjectConfig config)
    {
        super(config);
    }

    public String getAPPLICATION()
    {
        return getStringProperty(ATTR_APPLICATION);
    }

    public void setAPPLICATION(String value)
    {
        setProperty(ATTR_APPLICATION, value, 0);
    }

    public String getDOCUMENT_LOB_CODE()
    {
        return getStringProperty(ATTR_DOCUMENT_LOB_CODE);
    }

    public void setDOCUMENT_LOB_CODE(String value)
    {
        setProperty(ATTR_DOCUMENT_LOB_CODE, value, 0);
    }

    public String getDOCUMENT_TYPE()
    {
        return getStringProperty(ATTR_DOCUMENT_TYPE);
    }

    public void setDOCUMENT_TYPE(String value)
    {
        setProperty(ATTR_DOCUMENT_TYPE, value, 0);
    }

    public String getDOCUMENT_NAME()
    {
        return getStringProperty(ATTR_DOCUMENT_NAME);
    }

    public void setDOCUMENT_NAME(String value)
    {
        setProperty(ATTR_DOCUMENT_NAME, value, 0);
    }

    public String getIS_AUTO_PRIVATE()
    {
        return getStringProperty(ATTR_IS_AUTO_PRIVATE);
    }

    public void setIS_AUTO_PRIVATE(String value)
    {
        setProperty(ATTR_IS_AUTO_PRIVATE, value, 0);
    }



    public static com.msig.masterdata.MSIG_DOCUMENT_TYPES getMsigDocumentTypesObject(String APPLICATION, String DOCUMENT_LOB_CODE, String DOCUMENT_TYPE, String DOCUMENT_NAME)
    {
        String queryText = "select * from \"MSIG_DOCUMENT_TYPES\" where \"APPLICATION\" = :APPLICATION and \"DOCUMENT_LOB_CODE\" = :DOCUMENT_LOB_CODE and \"DOCUMENT_TYPE\" = :DOCUMENT_TYPE and \"DOCUMENT_NAME\" = :DOCUMENT_NAME";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APPLICATION", "MSIG_DOCUMENT_TYPES.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);//NOPMD
        query.addParameter("DOCUMENT_LOB_CODE", "MSIG_DOCUMENT_TYPES.DOCUMENT_LOB_CODE", QueryObject.PARAM_STRING, DOCUMENT_LOB_CODE);//NOPMD
        query.addParameter("DOCUMENT_TYPE", "MSIG_DOCUMENT_TYPES.DOCUMENT_TYPE", QueryObject.PARAM_STRING, DOCUMENT_TYPE);//NOPMD
        query.addParameter("DOCUMENT_NAME", "MSIG_DOCUMENT_TYPES.DOCUMENT_NAME", QueryObject.PARAM_STRING, DOCUMENT_NAME);//NOPMD
        query.setResultClass(MSIG_DOCUMENT_TYPES.class);
        return (MSIG_DOCUMENT_TYPES)query.getObject();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_DOCUMENT_TYPES> getMsigDocumentTypesObjects(String fromAPPLICATION, String toAPPLICATION, String fromDOCUMENT_LOB_CODE, String toDOCUMENT_LOB_CODE, String fromDOCUMENT_TYPE, String toDOCUMENT_TYPE, String fromDOCUMENT_NAME, String toDOCUMENT_NAME, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_DOCUMENT_TYPES\" where \"APPLICATION\" between :fromAPPLICATION and :toAPPLICATION and \"DOCUMENT_LOB_CODE\" between :fromDOCUMENT_LOB_CODE and :toDOCUMENT_LOB_CODE and \"DOCUMENT_TYPE\" between :fromDOCUMENT_TYPE and :toDOCUMENT_TYPE and \"DOCUMENT_NAME\" between :fromDOCUMENT_NAME and :toDOCUMENT_NAME";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("fromAPPLICATION", "MSIG_DOCUMENT_TYPES.APPLICATION", QueryObject.PARAM_STRING, fromAPPLICATION);
        query.addParameter("toAPPLICATION", "MSIG_DOCUMENT_TYPES.APPLICATION", QueryObject.PARAM_STRING, toAPPLICATION);
        query.addParameter("fromDOCUMENT_LOB_CODE", "MSIG_DOCUMENT_TYPES.DOCUMENT_LOB_CODE", QueryObject.PARAM_STRING, fromDOCUMENT_LOB_CODE);
        query.addParameter("toDOCUMENT_LOB_CODE", "MSIG_DOCUMENT_TYPES.DOCUMENT_LOB_CODE", QueryObject.PARAM_STRING, toDOCUMENT_LOB_CODE);
        query.addParameter("fromDOCUMENT_TYPE", "MSIG_DOCUMENT_TYPES.DOCUMENT_TYPE", QueryObject.PARAM_STRING, fromDOCUMENT_TYPE);
        query.addParameter("toDOCUMENT_TYPE", "MSIG_DOCUMENT_TYPES.DOCUMENT_TYPE", QueryObject.PARAM_STRING, toDOCUMENT_TYPE);
        query.addParameter("fromDOCUMENT_NAME", "MSIG_DOCUMENT_TYPES.DOCUMENT_NAME", QueryObject.PARAM_STRING, fromDOCUMENT_NAME);
        query.addParameter("toDOCUMENT_NAME", "MSIG_DOCUMENT_TYPES.DOCUMENT_NAME", QueryObject.PARAM_STRING, toDOCUMENT_NAME);
        query.setResultClass(MSIG_DOCUMENT_TYPES.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_DOCUMENT_TYPES> getMsigDocumentTypesObjects(String fromAPPLICATION, String toAPPLICATION, String fromDOCUMENT_LOB_CODE, String toDOCUMENT_LOB_CODE, String fromDOCUMENT_TYPE, String toDOCUMENT_TYPE, String fromDOCUMENT_NAME, String toDOCUMENT_NAME)
    {
        String queryText = "select * from \"MSIG_DOCUMENT_TYPES\" where \"APPLICATION\" between :fromAPPLICATION and :toAPPLICATION and \"DOCUMENT_LOB_CODE\" between :fromDOCUMENT_LOB_CODE and :toDOCUMENT_LOB_CODE and \"DOCUMENT_TYPE\" between :fromDOCUMENT_TYPE and :toDOCUMENT_TYPE and \"DOCUMENT_NAME\" between :fromDOCUMENT_NAME and :toDOCUMENT_NAME";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("fromAPPLICATION", "MSIG_DOCUMENT_TYPES.APPLICATION", QueryObject.PARAM_STRING, fromAPPLICATION);
        query.addParameter("toAPPLICATION", "MSIG_DOCUMENT_TYPES.APPLICATION", QueryObject.PARAM_STRING, toAPPLICATION);
        query.addParameter("fromDOCUMENT_LOB_CODE", "MSIG_DOCUMENT_TYPES.DOCUMENT_LOB_CODE", QueryObject.PARAM_STRING, fromDOCUMENT_LOB_CODE);
        query.addParameter("toDOCUMENT_LOB_CODE", "MSIG_DOCUMENT_TYPES.DOCUMENT_LOB_CODE", QueryObject.PARAM_STRING, toDOCUMENT_LOB_CODE);
        query.addParameter("fromDOCUMENT_TYPE", "MSIG_DOCUMENT_TYPES.DOCUMENT_TYPE", QueryObject.PARAM_STRING, fromDOCUMENT_TYPE);
        query.addParameter("toDOCUMENT_TYPE", "MSIG_DOCUMENT_TYPES.DOCUMENT_TYPE", QueryObject.PARAM_STRING, toDOCUMENT_TYPE);
        query.addParameter("fromDOCUMENT_NAME", "MSIG_DOCUMENT_TYPES.DOCUMENT_NAME", QueryObject.PARAM_STRING, fromDOCUMENT_NAME);
        query.addParameter("toDOCUMENT_NAME", "MSIG_DOCUMENT_TYPES.DOCUMENT_NAME", QueryObject.PARAM_STRING, toDOCUMENT_NAME);
        query.setResultClass(MSIG_DOCUMENT_TYPES.class);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_DOCUMENT_TYPES> getNextMsigDocumentTypesObjects(String APPLICATION, String DOCUMENT_LOB_CODE, String DOCUMENT_TYPE, String DOCUMENT_NAME, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_DOCUMENT_TYPES\" where (\"APPLICATION\" > :APPLICATION) or (\"APPLICATION\" = :APPLICATION and \"DOCUMENT_LOB_CODE\" > :DOCUMENT_LOB_CODE) or (\"APPLICATION\" = :APPLICATION and \"DOCUMENT_LOB_CODE\" = :DOCUMENT_LOB_CODE and \"DOCUMENT_TYPE\" > :DOCUMENT_TYPE) or (\"APPLICATION\" = :APPLICATION and \"DOCUMENT_LOB_CODE\" = :DOCUMENT_LOB_CODE and \"DOCUMENT_TYPE\" = :DOCUMENT_TYPE and \"DOCUMENT_NAME\" > :DOCUMENT_NAME) order by \"APPLICATION\" asc, \"DOCUMENT_LOB_CODE\" asc, \"DOCUMENT_TYPE\" asc, \"DOCUMENT_NAME\" asc";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APPLICATION", "MSIG_DOCUMENT_TYPES.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);//NOPMD
        query.addParameter("DOCUMENT_LOB_CODE", "MSIG_DOCUMENT_TYPES.DOCUMENT_LOB_CODE", QueryObject.PARAM_STRING, DOCUMENT_LOB_CODE);//NOPMD
        query.addParameter("DOCUMENT_TYPE", "MSIG_DOCUMENT_TYPES.DOCUMENT_TYPE", QueryObject.PARAM_STRING, DOCUMENT_TYPE);//NOPMD
        query.addParameter("DOCUMENT_NAME", "MSIG_DOCUMENT_TYPES.DOCUMENT_NAME", QueryObject.PARAM_STRING, DOCUMENT_NAME);//NOPMD
        query.setResultClass(MSIG_DOCUMENT_TYPES.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

    public static BusObjectIterator<com.msig.masterdata.MSIG_DOCUMENT_TYPES> getPreviousMsigDocumentTypesObjects(String APPLICATION, String DOCUMENT_LOB_CODE, String DOCUMENT_TYPE, String DOCUMENT_NAME, com.cordys.cpc.bsf.query.Cursor cursor)
    {
        String queryText = "select * from \"MSIG_DOCUMENT_TYPES\" where (\"APPLICATION\" < :APPLICATION) or (\"APPLICATION\" = :APPLICATION and \"DOCUMENT_LOB_CODE\" < :DOCUMENT_LOB_CODE) or (\"APPLICATION\" = :APPLICATION and \"DOCUMENT_LOB_CODE\" = :DOCUMENT_LOB_CODE and \"DOCUMENT_TYPE\" < :DOCUMENT_TYPE) or (\"APPLICATION\" = :APPLICATION and \"DOCUMENT_LOB_CODE\" = :DOCUMENT_LOB_CODE and \"DOCUMENT_TYPE\" = :DOCUMENT_TYPE and \"DOCUMENT_NAME\" < :DOCUMENT_NAME) order by \"APPLICATION\" desc, \"DOCUMENT_LOB_CODE\" desc, \"DOCUMENT_TYPE\" desc, \"DOCUMENT_NAME\" desc";
        QueryObject query = new QueryObject(queryText);
        query.addParameter("APPLICATION", "MSIG_DOCUMENT_TYPES.APPLICATION", QueryObject.PARAM_STRING, APPLICATION);//NOPMD
        query.addParameter("DOCUMENT_LOB_CODE", "MSIG_DOCUMENT_TYPES.DOCUMENT_LOB_CODE", QueryObject.PARAM_STRING, DOCUMENT_LOB_CODE);//NOPMD
        query.addParameter("DOCUMENT_TYPE", "MSIG_DOCUMENT_TYPES.DOCUMENT_TYPE", QueryObject.PARAM_STRING, DOCUMENT_TYPE);//NOPMD
        query.addParameter("DOCUMENT_NAME", "MSIG_DOCUMENT_TYPES.DOCUMENT_NAME", QueryObject.PARAM_STRING, DOCUMENT_NAME);//NOPMD
        query.setResultClass(MSIG_DOCUMENT_TYPES.class);
        query.setCursor(cursor);
        return query.getObjects();
    }

}
