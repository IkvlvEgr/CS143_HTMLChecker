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
     int size = tags.size();
     for( int i = 0; i < size; i++){
      HTMLTag tag = tags.remove();
      str += tag.toString().trim();
      tags.add(tag);
     }
     return str;
  }
  public void fixHTML(){
    Stack<HTMLTag> ta = new Stack<>();
     Queue<HTMLTag> qu = new LinkedList<>();
     while(!tags.isEmpty()){
       HTMLTag cur = tags.remove();
        if(cur.isSelfClosing()){
           qu.add(cur);
        }
        else if(cur.isOpening()){
           qu.add(cur);
           ta.push(cur);
       }
       else if(cur.isClosing()){
         if(ta.isEmpty()){
         }
         else{
            HTMLTag top = ta.peek();
            if(top.matches(cur)){
               qu.add(cur);
               ta.pop();
            }
            else{
             HTMLTag find = ta.pop().getMatching();
               qu.add(find);
            }
        
         }
      }
    }
        while(!ta.isEmpty()){
       HTMLTag remainOpe = ta.pop();
       HTMLTag remainCLO = remainOpe.getMatching();
       qu.add(remainCLO);
       
       }
       this.tags = qu;

  
  }
     
  
  
}
