package sample.model;
import javax.persistence.*;
@Entity public class Comment {

  @Id @GeneratedValue Long id;
  
  String commentText;
  String email;    
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getCommentText() {
    return commentText;
  }
  public void setCommentText(String commentText) {
    this.commentText = commentText;
  }
  
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
}