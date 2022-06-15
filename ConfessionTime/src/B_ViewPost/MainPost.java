package B_ViewPost;

import A_StartApp.MainPage;
import Utilities.ConnectDB;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MainPost extends javax.swing.JFrame {

    private int userId;
    private String postContent;
    private String datePosted;
    private String timePosted;
    private ImageIcon format = null;
    private int postId;
    private int replyId;
    private int specificPostId = -1;

    private Statement stmt;
    private ResultSet rs;
    
    public MainPost() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public MainPost(int userId){
        initComponents();
        setLocationRelativeTo(null); 
        PostID.setEditable(false);
        Date.setEditable(false);
        Time.setEditable(false);
        ReplyTo.setEditable(false);
        Post.setEditable(false);
        this.userId = userId;
    }
    
    public MainPost(int userId, int specificPostId){     // to open certain post page
        initComponents();
        setLocationRelativeTo(null); 
        this.userId = userId;
        this.specificPostId = specificPostId;
    }
    
    public boolean getAllPosts(){
        
        //retrieve data from the whole POSTS DATABASE
        String SQL = "SELECT * FROM POSTS ORDER BY DATEPOSTED DESC";
        
        try{
            //Execute some sql and load the records in resultset
            
            stmt = ConnectDB.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(SQL);
            
            if(specificPostId != -1) {
                while(rs.next()){
                    if(rs.getInt("ID") == specificPostId){
                        postId = specificPostId;
                        postContent = rs.getString("CONTENT");
                        replyId = rs.getInt("REPLYPOSTID");
                        datePosted = rs.getString("DATEPOSTED");
                        timePosted = rs.getString("TIMEPOSTED");
                        byte[] imageData = rs.getBytes("IMAGE");
                        ImageIcon image = loadImage(imageData);

                        PostID.setText(Integer.toString(postId));
                        Post.setText(postContent);
                        ReplyTo.setText(Integer.toString(replyId));
                        Date.setText(datePosted);
                        Time.setText(timePosted);
                        txtImage.setIcon(image);
                        
                        break;
                    }
                }
                return true;
            }else if(rs.next()){
                
               postId = rs.getInt("ID");
               postContent = rs.getString("CONTENT");
               replyId = rs.getInt("REPLYPOSTID");
               datePosted = rs.getString("DATEPOSTED");
               timePosted = rs.getString("TIMEPOSTED");
               byte[] imageData = rs.getBytes("IMAGE");
               ImageIcon image = loadImage(imageData);
               
               PostID.setText(Integer.toString(postId));
               Post.setText(postContent);
               ReplyTo.setText(Integer.toString(replyId));
               Date.setText(datePosted);
               Time.setText(timePosted);
               txtImage.setIcon(image);
               return true;
            }
            else{
                JOptionPane.showMessageDialog(this, "No posts existed!");
            }
        }catch(SQLException err){
            JOptionPane.showMessageDialog(this, err.getMessage());
        }
        MainPage mainPage = new MainPage(userId); // Display main page if main post do not have any posts
        mainPage.setVisible(true);
        return false;
    }
    
    // Load image in database
    public ImageIcon loadImage(byte[] imageData){
        ImageIcon img = null;
        
        if (imageData != null){
            format  = new ImageIcon(imageData) ;
            Image temp = format.getImage().getScaledInstance(240, 200, Image.SCALE_SMOOTH);
            img =new ImageIcon(temp);
        }
        return img;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NextPage = new javax.swing.JButton();
        Previous = new javax.swing.JButton();
        RepliesPost = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        ViewOriginalPost = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        PostID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Date = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Time = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Post = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        ReplyTo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtImage = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        NextPage.setText("Next Post");
        NextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextPageActionPerformed(evt);
            }
        });

        Previous.setText("Prev Post");
        Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousActionPerformed(evt);
            }
        });

        RepliesPost.setText("View Replies' Posts");
        RepliesPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RepliesPostActionPerformed(evt);
            }
        });

        Back.setText("Back to Main Page");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        ViewOriginalPost.setText("Original Post");
        ViewOriginalPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewOriginalPostActionPerformed(evt);
            }
        });

        jLabel1.setText("Confession Post ID ");

        jLabel2.setText("Date");

        jLabel3.setText("Time");

        jLabel4.setText(" Confession Post");

        Post.setColumns(20);
        Post.setRows(5);
        jScrollPane1.setViewportView(Post);

        jLabel5.setText("Replied To");

        jLabel6.setText("Image");

        txtImage.setBackground(new java.awt.Color(0, 102, 153));
        txtImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("UM OFFICIAL CONFESSION WEBSITE");

        jLabel8.setText("MAIN POST");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(249, 249, 249))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(321, 321, 321))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(PostID)
                            .addComponent(Date)
                            .addComponent(Time)
                            .addComponent(jLabel5)
                            .addComponent(ReplyTo))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(321, 321, 321)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtImage, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(RepliesPost)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Previous)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NextPage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ViewOriginalPost, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel6))
                            .addComponent(txtImage, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RepliesPost)
                            .addComponent(Previous)
                            .addComponent(NextPage)
                            .addComponent(ViewOriginalPost)
                            .addComponent(Back)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PostID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(7, 7, 7)
                        .addComponent(Time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ReplyTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextPageActionPerformed
        
       try{

            if(rs.next()){
                
               postId = rs.getInt("ID");
               postContent = rs.getString("CONTENT");
               replyId = rs.getInt("REPLYPOSTID");
               datePosted = rs.getString("DATEPOSTED");
               timePosted = rs.getString("TIMEPOSTED");
               byte[] imageData = rs.getBytes("IMAGE");
               ImageIcon image = loadImage(imageData);
               
               PostID.setText(Integer.toString(postId));
               Post.setText(postContent);
               ReplyTo.setText(Integer.toString(replyId));
               Date.setText(datePosted);
               Time.setText(timePosted);
               txtImage.setIcon(image);
            }
            else{
                rs.previous();
                JOptionPane.showMessageDialog(null, "End of Posts");
            }
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }//GEN-LAST:event_NextPageActionPerformed

    private void PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousActionPerformed
        try{

            if(rs.previous()){
                
               postId = rs.getInt("ID");
               postContent = rs.getString("CONTENT");
               replyId = rs.getInt("REPLYPOSTID");
               datePosted = rs.getString("DATEPOSTED");
               timePosted = rs.getString("TIMEPOSTED");
               byte[] imageData = rs.getBytes("IMAGE");
               ImageIcon image = loadImage(imageData);
               
               PostID.setText(Integer.toString(postId));
               Post.setText(postContent);
               ReplyTo.setText(Integer.toString(replyId));
               Date.setText(datePosted);
               Time.setText(timePosted);
               txtImage.setIcon(image);
            }
            else{
                rs.next();
                JOptionPane.showMessageDialog(null, "This is the latest post!");
            }
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }                 
    }//GEN-LAST:event_PreviousActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        dispose();
        MainPage mainpg = new MainPage(userId);
        mainpg.setVisible(true);                 
    }//GEN-LAST:event_BackActionPerformed

    private void RepliesPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RepliesPostActionPerformed
        ReplyPost replyPost = new ReplyPost(postId); // Do not need to pass userId
        replyPost.setVisible(replyPost.getAllPosts()); // setVisible(true) if there is reply post
    }//GEN-LAST:event_RepliesPostActionPerformed

    private void ViewOriginalPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewOriginalPostActionPerformed
        OriginalPost originalPost = new OriginalPost(replyId); // Do not need to pass userId
        originalPost.setVisible(originalPost.getAllPosts()); // setVisible(true) if there is original post
    }//GEN-LAST:event_ViewOriginalPostActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPost().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JTextField Date;
    private javax.swing.JButton NextPage;
    private javax.swing.JTextArea Post;
    private javax.swing.JTextField PostID;
    private javax.swing.JButton Previous;
    private javax.swing.JButton RepliesPost;
    private javax.swing.JTextField ReplyTo;
    private javax.swing.JTextField Time;
    private javax.swing.JButton ViewOriginalPost;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtImage;
    // End of variables declaration//GEN-END:variables
}
