import java.util.*;

public class HTMLManager {

   // Queue to store HTML tags in order
   private Queue<HTMLTag> tags;

   // Constructor: copies the input queue into a a local queue
   public HTMLManager(Queue<HTMLTag> html) {
     tags = new LinkedList<>();
     
     if (html == null) {
       // Throw exception if null is passed
       throw new IllegalArgumentException();
     }

     // Copy each tag into the internal queue
     for (HTMLTag tag : html) {
       tags.add(tag);
     }
   }

   // Getter method for tags queue
   public Queue<HTMLTag> getTags() {
     return tags;
   }

   // Returns a String of all tags in order
   public String toString() {
     String str = "";
     int size = tags.size();

     for (int i = 0; i < size; i++) {
       HTMLTag tag = tags.remove();           // Remove from front
       str += tag.toString().trim();          // Append tag
       tags.add(tag);                         // Add back to the end
     }

     return str;
   }

   // Fixes mismatched or missing HTML tags in the queue
   public void fixHTML() {
     Stack<HTMLTag> ta = new Stack<>();         // Stack to manage open tags
     Queue<HTMLTag> qu = new LinkedList<>();    // New queue for corrected output

     // Process each tag from the original queue
     while (!tags.isEmpty()) {
       HTMLTag cur = tags.remove();

       if (cur.isSelfClosing()) {
         // Self-closing tags are added directly
         qu.add(cur);
       }
       else if (cur.isOpening()) {
         // Opening tag - push onto stack and add to output queue
         qu.add(cur);
         ta.push(cur);
       }
       else if (cur.isClosing()) {
         if (ta.isEmpty()) {
           // No opening tags left to match with — skip stray closing tag
         } else {
           HTMLTag top = ta.peek();
           if (top.matches(cur)) {
             //Tag matches the top of the stack — good match
             qu.add(cur);
             ta.pop();
           } else {
             //Mismatched tag — close the last unclosed tag properly
             HTMLTag fix = ta.pop().getMatching(); // Get correct closing tag
             qu.add(fix); // Add the correct closing tag
           }
         }
       }
     }

     // Add missing closing tags for any remaining open tags in stack
     while (!ta.isEmpty()) {
       HTMLTag remainOpe = ta.pop();
       HTMLTag remainCLO = remainOpe.getMatching();
       qu.add(remainCLO);
     }

     // Replace old tags with the corrected version
     this.tags = qu;
   }
}
