package sample.view;
import net.sf.click.*;
import net.sf.click.control.*;
import net.sf.click.extras.control.*;
import java.util.*;
import sample.model.*;
import sample.repoimpl.*;

public class SampleCommentPage extends Page {
  /** ƒŠƒ|ƒWƒgƒŠ */
  Repository repo = new RepoImpl();
  public Table commentTable = new Table("commentTable");
  public Form commentBox = new Form("commentBox");
  protected Field  emf = new EmailField("email");
  protected Field  cmf = new TextField("commentText");
  protected Submit submit = new Submit("submit", this, "onComment");
  
  public SampleCommentPage() {
    commentTable.addColumn(new Column("id", "ID"));
    commentTable.addColumn(new Column("email", "E-MAIL"));
    commentTable.addColumn(new Column("commentText", "comment"));
    commentBox.add(emf);
    commentBox.add(cmf);
    commentBox.add(submit);
  }
  @Override
  public void onInit() {
    repo.begin();
  }
  @Override
  public void onRender() {
    List<Comment> commentList =repo.select(Comment.class , "from Comment c order by c.id");
    commentTable.setRowList(commentList);
  }

  public boolean onComment() throws Exception {
    if(emf.getError()!=null) return true; 
    try {
      Comment c = new Comment();
      commentBox.copyTo(c);
      repo.save(c);   
      setRedirect(this.getClass());
      return false;
    } catch (Exception e) {
      e.printStackTrace();
      repo.setRollbackOnly(true);
      throw e;
    }
  }
  
  @Override
  public void onDestroy() {
    repo.commitOrRollback();
    repo.close();
  }
}