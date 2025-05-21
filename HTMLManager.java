import java.util.*;

public class HTMLManager {
  private Queue<HTMLTag> tags;
  public HTMLManager(Queue<HTMLTag> html){
     tags = new LinkedList<>();
     if(html == null){
       throw new IllegalArgumentException();
     } 
     for(HTMLTag tag : html) {
      tags.add(tag);
     }
  }
  public Queue<HTMLTag> getTags(){
    return tags;
  }
  public String toString(){
  String str = "";
   for( HTMLTag tag : tags){
      str += tag.toString().trim();
   }
   return str;
  }
  public void fixHTML(){
    Stack<HTMLTag> ta = new Stack<>();
    int quueSize = tags.size();
    for(int i = 0; i < quueSize; i++){
       HTMLTag tag = tags.remove();
       if(tag.isSelfClosing()){
         return true;
       }
    }
    
  
  }
  
}
