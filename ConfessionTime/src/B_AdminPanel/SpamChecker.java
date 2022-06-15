
package B_AdminPanel;

import Utilities.ConnectDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SpamChecker {
    
    private Statement stmt;
    private ResultSet rs;
    private static ArrayList<String> allPosts = new ArrayList<>();
    private static double stringSimilarity;
    public static int pass;

    public SpamChecker(String content) {
        getAllPosts();
        
    }
    
    public boolean checkContent(String content) {
        
        //Sensitive word to filtered out
        String[] offensiveWords = {"promotion", "I want to promote","@", "$", "%", "#", "fucking", "sexy", "rape" };

        //Filter start
        int id = 0;
        for (int i = 0; i<offensiveWords.length; i++) {
            if (content.toLowerCase().contains(offensiveWords[i].toLowerCase())) {
                id = 0;
                break;
            } else
                id = 1;
        } //filter end
        
        if (id == 1){
            //SPAM CHECKER CODE HERE
            // create for loop to loop the content in database
            // fetch post from database
            // compare each post in data base to the content post that user just input
             for (int i = 0; i < allPosts.size(); i++) {
                 if (content.equals(allPosts.get(i)))
                    // If the Strings are equal
                     return false;

                // Length of two Strings
                int len1 = content.length(),
                        len2 = allPosts.get(i).length();

                // Maximum distance upto which matching
                // is allowed
                int max_dist = (int) (Math.floor(Math.max(len1, len2) / 2) - 1);

                // Count of matches
                int match = 0;

                // Hash for matches
                int hash_content[] = new int[content.length()];
                int hash_allPosts[] = new int[allPosts.get(i).length()];

                // Traverse through the first String
                for (int x = 0; x < len1; x++){
                    // Check if there is any matches
                    for (int j = Math.max(0, x - max_dist);
                         j < Math.min(len2, x + max_dist + 1); j++){

                        // If there is a match
                        
                        if (content.charAt(x) == allPosts.get(i).charAt(j) && hash_allPosts[j] == 0){
                            hash_content[x] = 1;
                            hash_allPosts[j] = 1;
                            match++;
                            break;
                        }
                    }    
                }

                // If there is no match
                if (match == 0)
                    return true;

                // Number of transpositions
                double t = 0;

                int point = 0;

                // Count number of occurrences
                // where two characters match but
                // there is a third matched character
                // in between the indices
                for (int x = 0; x < len1; x++)
                    if (hash_content[x] == 1){
                        // Find the next matched character
                        // in second String
                        while (hash_allPosts[point] == 0)
                            point++;
                        

                        if (content.charAt(i) != allPosts.get(i).charAt(point++) )
                            t++;
                    }

                t /= 2;

                stringSimilarity = (((double)match) / ((double)len1) + ((double)match) / ((double)len2) + ((double)match - t) / ((double)match)) / 3.0;

                // Return the Jaro Similarity
                //return stringSimilarity;

                pass = 0;
                if (stringSimilarity>0.93) {
                 //is rejected and NOT pushed into queue
                 pass = 0;
                 break;
                } else
                    pass = 1;
            }
            if(allPosts.isEmpty())
                return true;
            if (pass == 1) {
                return true;
                //is accepted and pushed into queue
            }
        }
        return false;
    } 
    
    
    private void getAllPosts(){
        
        //retrieve data from the whole POSTS DATABASE
        String SQL = "SELECT CONTENT FROM POSTS";
        
        try{
            //Execute some sql and load the records in resultset
            
            stmt = ConnectDB.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(SQL);
            
            while(rs.next()){
               allPosts.add(rs.getString("CONTENT"));
            }
            
        }catch(SQLException err){
            err.getStackTrace();
        }
    }
    
    
    
}
