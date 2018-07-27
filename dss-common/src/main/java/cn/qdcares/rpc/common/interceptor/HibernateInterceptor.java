package cn.qdcares.rpc.common.interceptor;

import cn.qdcares.rpc.common.entity.EntityOperation;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.Transaction;
import org.hibernate.collection.internal.AbstractPersistentCollection;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Iterator;

/**
 *  @Description:
 *  @author dongqun
 *  @date 2018/5/30 15:53
 */
public class HibernateInterceptor extends EmptyInterceptor {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state,
                         String[] propertyNames, Type[] types) {

        EntityOperation entityOperation = new EntityOperation();
        entityOperation.setEntityValue(entity);
        entityOperation.setPk(id);
        entityOperation.setState(state);
        entityOperation.setPropertyNames(propertyNames);
        entityOperation.setOperationType(EntityOperation.DELETED);
        entityOperation.setTypes(types);
        super.onDelete(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id,
                                Object[] currentState, Object[] previousState,
                                String[] propertyNames, Type[] types) {
        EntityOperation entityOperation = new EntityOperation();
        entityOperation.setEntityValue(entity);
        entityOperation.setPk(id);
        entityOperation.setCurrentState(currentState);
        entityOperation.setPreviousState(previousState);
        entityOperation.setPropertyNames(propertyNames);
        entityOperation.setOperationType(EntityOperation.UPDATE);
        entityOperation.setTypes(types);
        return super.onFlushDirty(entity, id, currentState, previousState,
                propertyNames, types);

    }

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state,
                          String[] propertyNames, Type[] types) {
        return super.onLoad(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state,
                          String[] propertyNames, Type[] types) {

        EntityOperation entityOperation = new EntityOperation();
        entityOperation.setEntityValue(entity);
        entityOperation.setPk(id);
        entityOperation.setPropertyNames(propertyNames);
        entityOperation.setState(state);
        entityOperation.setOperationType(EntityOperation.INSERT);
        entityOperation.setTypes(types);
        return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public void postFlush(Iterator entities) {
        super.postFlush(entities);
    }

    @Override
    public void preFlush(Iterator entities) {
        super.preFlush(entities);
    }

    @Override
    public Boolean isTransient(Object entity) {
        return super.isTransient(entity);
    }

    @Override
    public Object instantiate(String entityName, EntityMode entityMode,
                              Serializable id) {

        return super.instantiate(entityName, entityMode, id);
    }

    @Override
    public int[] findDirty(Object entity, Serializable id,
                           Object[] currentState, Object[] previousState,
                           String[] propertyNames, Type[] types) {
        return super.findDirty(entity, id, currentState, previousState,
                propertyNames, types);
    }

    @Override
    public String getEntityName(Object object) {
        return super.getEntityName(object);
    }

    @Override
    public Object getEntity(String entityName, Serializable id) {
        return super.getEntity(entityName, id);
    }

    @Override
    public void afterTransactionBegin(Transaction tx) {
        super.afterTransactionBegin(tx);
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        super.afterTransactionCompletion(tx);
    }

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        super.beforeTransactionCompletion(tx);
    }

    @Override
    public String onPrepareStatement(String sql) {
        return super.onPrepareStatement(sql);
    }

    @Override
    public void onCollectionRemove(Object collection, Serializable key)
            throws CallbackException {

        AbstractPersistentCollection abstractPersistentCollection = (AbstractPersistentCollection) collection;
        EntityOperation entityOperation = new EntityOperation();
        entityOperation.setEntityValue(abstractPersistentCollection.getOwner());
        entityOperation.setCollectionName(abstractPersistentCollection
                .getRole());
        entityOperation.setOperationType(EntityOperation.COLLECTIONREMOVE);
        super.onCollectionRemove(collection, key);
    }

    @Override
    public void onCollectionRecreate(Object collection, Serializable key)
            throws CallbackException {
        // PersistentBag bag = (PersistentBag) collection;
        // EntityOperation entityOperation = new EntityOperation();
        // entityOperation.setEntityValue(bag.getOwner());
        // entityOperation.setCollectionName(bag.getRole());
        // entityOperation.setOperationType(EntityOperation.COLLECTIONCREATE);
        // FimsSystemContext.getEntityOperationsThreadLocal().get()
        // .add(entityOperation);
        super.onCollectionRecreate(collection, key);
    }

    @Override
    public void onCollectionUpdate(Object collection, Serializable key)
            throws CallbackException {
        AbstractPersistentCollection abstractPersistentCollection = (AbstractPersistentCollection) collection;
        EntityOperation entityOperation = new EntityOperation();
        entityOperation.setEntityValue(abstractPersistentCollection.getOwner());
        entityOperation.setCollectionName(abstractPersistentCollection
                .getRole());
        entityOperation.setOperationType(EntityOperation.COLLECTIONUPDATE);
        super.onCollectionUpdate(collection, key);
    }

}
