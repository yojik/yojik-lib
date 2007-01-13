package sample.model;
import java.util.List;
public interface Repository {
  public void begin();
  public void setRollbackOnly(boolean isRollback);
  public void commitOrRollback();
  public void commit()  throws RuntimeException;  
  public void rollback() throws RuntimeException;
  public void close();
  
  public <T> T get(Class<T> clazz , java.io.Serializable key) ;
  public <T> List<T> select(Class<T> clazz , String query);  
  public <T> T merge(T entity);
  public void  save(Object entiry);



 
}