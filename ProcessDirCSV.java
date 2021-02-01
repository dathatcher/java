import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
public class ProcessDirCSV {    public static void main(String args[]) throws IOException {
      //Creating a File object for directory
      //File directoryPath = new File("/workspace/java/csv");
      File directoryPath = new File("./csv");
      //List of all files and directories
      String contents[] = directoryPath.list();
      //System.out.println("List =:" + contents.length);

      Vector system = new Vector();
      Vector category = new Vector();
      Vector score = new Vector();
      int sysCntr = 0;
      int catCntr = 0;
      int scoreCntr = 0;
      
      for(int i=0; i<contents.length; i++) {
        // System.out.println(contents[i]);
         String filename = contents[i];
         if (filename.endsWith(".csv")) {
            //System.out.println("FILE = "+ filename);
            Scanner sc = new Scanner(new File("/workspace/java/csv/" + filename));    
            sc.useDelimiter(",");   //sets the delimiter pattern 
            int firstOccurence=0;
            String scat=filename;
            
            system.add(sysCntr, filename);
            sysCntr++;

            while (sc.hasNext())  //returns a boolean value  
                    {  
                      String s = sc.next();
                      
                      if (s.startsWith("People -") || s.startsWith("Process -" ) || s.startsWith("Tech -" ) ){
                          firstOccurence++;
                         if (firstOccurence <= 21) { 
                             int idx=s.indexOf("Your");
                             if (idx>0) {
                                 s = s.substring(0, idx);
                             }
                             scat = scat +"," + s+"("+firstOccurence+") ";
                             category.add(catCntr, s); 
                             catCntr++;                                                        
                            // System.out.println("FILENAME: " + s);  //find and returns the next complete token from this scanner  
                          }
                       }
                       if (firstOccurence >= 21 && firstOccurence < 45){
                           firstOccurence++;
                           if (firstOccurence > 23 && firstOccurence< 45){
                               int idx = s.indexOf("Comparative");
                               if (idx>0) {
                                 s = s.substring(0, idx);
                               }
                               scat = scat +"," + s+"("+firstOccurence+") ";
                               score.add(scoreCntr, s);
                               scoreCntr++;                               
                           }
                       }
                        }
                    
                    Iterator isystem = system.iterator();  
                   // System.out.println("The iterator values are: "); 
                   String printSystem = "";
                    while (isystem.hasNext()) { 
                        printSystem=isystem.next().toString();
                        int idx = printSystem.indexOf(".csv");
                        printSystem = printSystem.substring(0, idx);
                       // System.out.print(printSystem);
                       // System.out.print(","); 
                    } 
                    Iterator icategory = category.iterator();
                    Iterator iscore = score.iterator();   
                   // System.out.println("The iterator values are: "); 
                    while (icategory.hasNext()) { 
                        System.out.print(printSystem + ",");
                        System.out.print(icategory.next());
                        if (iscore.hasNext()) { 
                            System.out.print(",");
                            System.out.println(iscore.next());
                        }
                        //System.out.print(","); 
                    } 
                   // System.out.println("");
                   // System.out.print(printSystem + ",");
                   /* Iterator iscore = score.iterator();  
                    //System.out.println("The iterator values are: "); 
                    while (iscore.hasNext()) { 
                        String s = iscore.next().toString();
                        if(s.startsWith("Your Data")){
                            System.out.print(",");
                        }else{
                            System.out.print(s);
                        }
                        System.out.print(",");                      
                    } */
                    //System.out.println("");
                    system.clear();
                    category.clear();
                    score.clear();
                    sysCntr = 0;
                    catCntr = 0;
                    scoreCntr = 0;
                    sc.close();  
         }
          
      }
   }
}