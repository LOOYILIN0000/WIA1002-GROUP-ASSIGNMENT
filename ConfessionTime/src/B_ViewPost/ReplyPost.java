package B_ViewPost;

import Utilities.ConnectDB;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ReplyPost extends javax.swing.JFrame {

    private int postId;
    private int replyId;        // must! replyId == replyPostId 
    private String postContent;
    private int replypostId;    // receive from constructor
    private String datePosted;
    private String timePosted;
    private ImageIcon format = null;
   
    private Statement stmt;
    private ResultSet rs;
    
    public ReplyPost() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public ReplyPost(int replypostId){
        initComponents();
        setLocationRelativeTo(null);
        this.replypostId = replypostId;
    }
    
    protected boolean getAllPosts(){
        
        //retrieve data from the whole POSTS DATABASE
        String SQL = "SELECT * FROM POSTS WHERE REPLYPOSTID = "+replypostId+"";
        
        try{
            //Execute some sql and load the records in resultset
            stmt = ConnectDB.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(SQL);
            
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
               
            } else {
                JOptionPane.showMessageDialog(this, "This post has no reply post!");
                return false;
            }
        }catch(SQLException err){
            JOptionPane.showMessageDialog(this, err.getMessage());
        }
        
        // return true if there is reply posts
        return true;
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

        jFrame1 = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        Back = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        PostID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Date = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Time = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Post = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtImage = new javax.swing.JLabel();
        NextReply = new javax.swing.JButton();
        PrevReply = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        ReplyTo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        jLabel5.setText("REPLY POST");

        jLabel3.setText("Confession Post ID ");

        jLabel2.setText("Date");

        jLabel7.setText("Time");

        Post.setColumns(20);
        Post.setRows(5);
        jScrollPane1.setViewportView(Post);

        jLabel4.setText(" Confession Post");

        jLabel6.setText("Image");

        txtImage.setBackground(new java.awt.Color(0, 102, 153));
        txtImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        NextReply.setText("Next reply");
        NextReply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextReplyActionPerformed(evt);
            }
        });

        PrevReply.setText("Prev Reply");
        PrevReply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrevReplyActionPerformed(evt);
            }
        });

        jLabel8.setText("Replied To");

        jLabel9.setText("UM OFFICIAL CONFESSION WEBSITE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(PostID)
                    .addComponent(Date)
                    .addComponent(Time, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addComponent(ReplyTo))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(PrevReply)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(NextReply)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGap(159, 159, 159)))
                    .addComponent(txtImage, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(307, 307, 307))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(248, 248, 248))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(2, 2, 2)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtImage, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PrevReply)
                            .addComponent(NextReply)
                            .addComponent(Back)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PostID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ReplyTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        dispose();
    }//GEN-LAST:event_BackActionPerformed

    private void PrevReplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrevReplyActionPerformed
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
                JOptionPane.showMessageDialog(null, "Latest Reply!");
            }
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }                 
    }//GEN-LAST:event_PrevReplyActionPerformed

    private void NextReplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextReplyActionPerformed
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
                JOptionPane.showMessageDialog(null, "End of Replies!");
            }
        }
        catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }//GEN-LAST:event_NextReplyActionPerformed

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
            java.util.logging.Logger.getLogger(ReplyPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReplyPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReplyPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReplyPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReplyPost().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JTextField Date;
    private javax.swing.JButton NextReply;
    private javax.swing.JTextArea Post;
    private javax.swing.JTextField PostID;
    private javax.swing.JButton PrevReply;
    private javax.swing.JTextField ReplyTo;
    private javax.swing.JTextField Time;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txtImage;
    // End of variables declaration//GEN-END:variables
}
