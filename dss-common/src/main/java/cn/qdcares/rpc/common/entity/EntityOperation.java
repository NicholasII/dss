package cn.qdcares.rpc.common.entity;

import org.hibernate.type.Type;

import java.io.Serializable;

/**
 * 　@Description: 实体操作
 * 　@author dongqun
 * 　@date 2018/5/30 16:21
 *
 */
public class EntityOperation implements Serializable {
    public static final String COLLECTIONCREATE = "COLLECTIONCREATE";
    public static final String COLLECTIONREMOVE = "COLLECTIONREMOVE";
    public static final String COLLECTIONUPDATE = "COLLECTIONUPDATE";
    public static final String DELETED = "DELETED";
    public static final String INSERT = "INSERT";
    private static final long serialVersionUID = 1L;
    public static final String UPDATE = "UPDATE";

    private String collectionName;
    private Object[] currentState;
    private Object entityValue;
    private String operationDescription;

    private String operationType;
    private Serializable pk;

    private Object[] previousState;

    private String[] propertyNames;

    private Object[] state;

    private Type[] types;

    public String getCollectionName() {
        return collectionName;
    }

    public Object[] getCurrentState() {
        return currentState;
    }

    public Object getEntityValue() {
        return entityValue;
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public String getOperationType() {
        return operationType;
    }

    public Serializable getPk() {
        return pk;
    }

    public Object[] getPreviousState() {
        return previousState;
    }

    public String[] getPropertyNames() {
        return propertyNames;
    }

    public Object[] getState() {
        return state;
    }

    /**
     * @return the types
     */
    public Type[] getTypes() {
        return types;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setCurrentState(Object[] currentState) {
        this.currentState = currentState;
    }

    public void setEntityValue(Object entityValue) {
        this.entityValue = entityValue;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setPk(Serializable pk) {
        this.pk = pk;
    }

    public void setPreviousState(Object[] previousState) {
        this.previousState = previousState;
    }

    public void setPropertyNames(String[] propertyNames) {
        this.propertyNames = propertyNames;
    }

    public void setState(Object[] state) {
        this.state = state;
    }

    /**
     * @param types the types to set
     */
    public void setTypes(Type[] types) {
        this.types = types;
    }

}
