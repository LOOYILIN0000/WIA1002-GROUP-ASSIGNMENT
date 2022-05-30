package ViewConfession;

import Utilitities.ConnectDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChosenPost extends javax.swing.JFrame {

    private int userId;
    private int postedbyId;
    private String postContent;
    private int postId;
    private String datePosted;
    private String timePosted;
    
    ConnectDB connDB;
    Statement stmt;
    ResultSet rs;
    
    public ChosenPost() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public ChosenPost(int postId){
        initComponents();
        setLocationRelativeTo(null); 
        this.postId = postId;
    }
    
    private void SetIsUserPost(int postId){
        String SQL = "SELECT postedbyId FROM POSTS WHERE ID = "+postId+"";
        
        try{
            //Execute some sql and load the records in resultset
            
            stmt = ConnectDB.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(SQL);
            
            if(rs.next()){
            postedbyId = rs.getInt("PostedbyId");
            }
            stmt.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NextPage = new javax.swing.JButton();
        PreviousPage = new javax.swing.JButton();
        RepliesPost = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        NextPage.setText("Next Post");
        NextPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextPageActionPerformed(evt);
            }
        });

        PreviousPage.setText("Prev Post");
        PreviousPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousPageActionPerformed(evt);
            }
        });

        RepliesPost.setText("View Replies' Posts");
        RepliesPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RepliesPostActionPerformed(evt);
            }
        });

        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                        .addComponent(PreviousPage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NextPage)
                        .addGap(210, 210, 210))
                    .addComponent(jScrollPane2))
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RepliesPost)
                .addGap(286, 286, 286))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RepliesPost)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextPage)
                    .addComponent(PreviousPage)
                    .addComponent(Back))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NextPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextPageActionPerformed
        // TODO add your handling code here:
        dispose();
        NextPost nextpost = new NextPost();
        nextpost.setVisible(true);
    }//GEN-LAST:event_NextPageActionPerformed

    private void PreviousPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousPageActionPerformed
        // TODO add your handling code here:
        dispose();
        PrevPost prevpost = new PrevPost();
        prevpost.setVisible(true);
    }//GEN-LAST:event_PreviousPageActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        dispose();
        AllPost allpost = new AllPost();
        allpost.setVisible(true);
        
    }//GEN-LAST:event_BackActionPerformed

    private void RepliesPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RepliesPostActionPerformed
        // TODO add your handling code here:
        dispose();
        RepliesPost rplypost = new RepliesPost();
        rplypost.setVisible(true);     
    }//GEN-LAST:event_RepliesPostActionPerformed

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
            java.util.logging.Logger.getLogger(ChosenPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChosenPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChosenPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChosenPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChosenPost().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton NextPage;
    private javax.swing.JButton PreviousPage;
    private javax.swing.JButton RepliesPost;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
