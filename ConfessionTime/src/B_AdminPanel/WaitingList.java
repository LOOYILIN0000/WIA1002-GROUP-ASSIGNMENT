package B_AdminPanel;

import Utilities.ConnectDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class WaitingList {
    
    private Queue<Integer> postId = new LinkedList<>();
    private Queue<Integer> postedById = new LinkedList<>();
    private Queue<Integer> replyPostId = new LinkedList<>();
    private Queue<String> datePosted = new LinkedList<>();
    private Queue<String> timePosted = new LinkedList<>();
    private Queue<String> content = new LinkedList<>();
    
    private Statement stmt;
    private ResultSet rs;
    
    
    public WaitingList() throws InterruptedException {
        getAllPosts();
        
        // Waiting time elapsed
        PostScheduler postScheduler 
                = new PostScheduler(postId, postedById, replyPostId, datePosted, timePosted, content);
        postScheduler.start();
        TimeUnit.SECONDS.sleep(0);
    }
    
    
    private void getAllPosts(){
        
        // Retrieve data from the whole PENDING_POSTS Table
        String SQL = "SELECT * FROM PENDING_POSTS";
        
        try{
            
            // Execute some sql and load the records in resultset
            stmt = ConnectDB.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(SQL);
            
            // Push posts into queue 
            while(rs.next()){
               postId.add(Integer.parseInt(rs.getString("ID")));
               postedById.add(Integer.parseInt(rs.getString("POSTEDBYID")));
               replyPostId.add(Integer.parseInt(rs.getString("REPLYPOSTID")));
               datePosted.add(rs.getString("DATEPOSTED"));               
               timePosted.add(rs.getString("TIMEPOSTED"));
               content.add(rs.getString("CONTENT"));
            }
            
        }catch(SQLException err){
            err.getStackTrace();
        }
    }
}
