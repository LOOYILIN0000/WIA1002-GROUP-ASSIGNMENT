package B_AdminPanel;

import Utilities.ConnectDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostScheduler extends Thread{
    
    private Queue<Integer> postId = new LinkedList<>();
    private Queue<Integer> postedById = new LinkedList<>();
    private Queue<Integer> replyPostId = new LinkedList<>();
    private Queue<String> datePosted = new LinkedList<>();
    private Queue<String> timePosted = new LinkedList<>();
    private Queue<String> content = new LinkedList<>();
    private Queue<java.sql.Blob> image = new LinkedList<>(); 
    
    private Statement stmt;
    private Integer currentId;
    
    public PostScheduler(Queue<Integer> postId, Queue<Integer> postedById,
            Queue<Integer> replyPostId, Queue<String> datePosted,
            Queue<String> timePosted, Queue<String> content, Queue<java.sql.Blob> image) {
        this.postId = postId;
        this.postedById = postedById;
        this.replyPostId = replyPostId;
        this.datePosted = datePosted;
        this.timePosted = timePosted;
        this.content = content;
        this.image = image;
    }

    @Override
    public void run() {
            try {
                if (postId.size() >= 1 && postId.size() <= 5) {
                    // 15 minutes = 900 000
                    // for testing purpose, assume 15 minutes == 1.5 sec == 1500 ms
                    while(!postId.isEmpty()){
                        Thread.sleep(1500);
                        currentId = postId.peek();
                        System.out.println("Popping post #" + currentId + " from queue"); 
                        // Content popped from queue, stored permanantly in database
                        insertIntoDatabase();
                        deleteRow();
                    }                    
                    
                } else if (postId.size() >= 6 && postId.size() <= 10) {
                    // 10 minutes = 600 000
                    // assume 10 minutes == 1 sec = 1000 ms
                    while(!postId.isEmpty()){
                        Thread.sleep(1000);
                        currentId = postId.peek();
                        System.out.println("Popping post #" + currentId + " from queue.");                        
                        insertIntoDatabase();
                        deleteRow();
                    }

                } else if (postId.size() >= 11) {
                    // 5 minutes = 300 000
                    // assume 5 minutes == 0.5 sec == 500 ms
                    while(!postId.isEmpty()){
                        Thread.sleep(500);
                        currentId = postId.peek();
                        System.out.println("Popping post #" + currentId + " from queue.");
                        insertIntoDatabase();
                        deleteRow();
                    }
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        
    }
    
    public void insertIntoDatabase() {
        
        String SQL_INSERT = "INSERT INTO POSTS (Id, PostedById, ReplyPostId, DatePosted, TimePosted, Content, Image) VALUES "
                + "("+postId.poll()+","+postedById.poll()+", "+replyPostId.poll()+", '"+datePosted.poll()+"', '"+timePosted.poll()+"', '"+content.poll()+"', "+image.poll()+")";

        try {
            stmt = ConnectDB.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            int rows = stmt.executeUpdate(SQL_INSERT);
                            if(rows>0){
                                System.out.println("New post has been added into post table");
                            } 
        } catch (SQLException ex) {
            Logger.getLogger(PostScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteRow(){
            String SQL_DELETE = "DELETE FROM PENDING_POSTS WHERE Id = "+currentId+"";
             try {
            stmt = ConnectDB.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            int rows = stmt.executeUpdate(SQL_DELETE);
                            if(rows>0){
                                System.out.println("Post has been deleted from pending_posts table");
                            } 
        } catch (SQLException ex) {
            Logger.getLogger(PostScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
