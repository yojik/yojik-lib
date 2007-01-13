package sample.repoimpl;
import sample.model.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import javax.naming.InitialContext;
public class RepoImpl implements Repository { 
  static final SessionFactory sessionFactory;

  boolean isRollbackOnly = false;
  
  static {
    try {
      sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    } catch (Throwable ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }
  public void begin() {
    try {
        sessionFactory.getCurrentSession().getTransaction().begin();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  public  Session getSession() {
    try {
      return sessionFactory.getCurrentSession();
      
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  public void close() {
    getSession().close();
  }
  public void setRollbackOnly(boolean r) {
    this.isRollbackOnly = r;
  }
  public void commitOrRollback() {
    if(isRollbackOnly) {
      rollback();
    } else {
      commit();
    }
  }
  public void commit()  throws RuntimeException {
    if(isRollbackOnly) return;
    try {
      Transaction tx = getSession().getTransaction();
      if(!tx.isActive()) return;
      tx.commit();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  public void rollback() throws RuntimeException {
    try {
      Transaction tx = getSession().getTransaction();
      if(!tx.isActive()) return;
      tx.rollback();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }        
  }
  public <T> List<T> select(Class<T> clazz , String query) {
    return (List<T>) getSession().createQuery(query).list();
  }
  public <T> T get(Class<T> clazz , java.io.Serializable key) {
    return (T)getSession().get(clazz, key);
  }
  public <T> T merge(T entity) {
    return (T)getSession().merge(entity);
  }
  public void  save(Object entiry) {
    getSession().save(entiry);
  }
}