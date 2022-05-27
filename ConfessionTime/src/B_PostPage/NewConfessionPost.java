
package B_PostPage;

import A_StartApp.MainPage;
import Utilitities.ConnectDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;

public class NewConfessionPost extends javax.swing.JFrame {
    
    ConnectDB connDB;
    Statement stmt;
    ResultSet rs;
    private int userId;
    private int newPostId;
    
    public NewConfessionPost() {
        initComponents();
    }
    
    public NewConfessionPost(int userId) {
        initComponents();
        setLocationRelativeTo(null);
        this.userId = userId;
        
        //Connecting to database
        connDB = new ConnectDB();
        connDB.DoConnect();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblInstruction1 = new javax.swing.JLabel();
        lblInstruction2 = new javax.swing.JLabel();
        txtPostId = new javax.swing.JTextField();
        lblInstruction3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtContent = new javax.swing.JTextArea();
        btnImage = new javax.swing.JToggleButton();
        btnSubmit = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblInstruction1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInstruction1.setText("Please enter the confession post ID you want to reply. ");

        lblInstruction2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInstruction2.setText("Leave it blank if you don’t want to reply to a confession post. ");

        txtPostId.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtPostId.setMinimumSize(new java.awt.Dimension(5, 18));
        txtPostId.setPreferredSize(new java.awt.Dimension(4, 18));

        lblInstruction3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInstruction3.setText("Please enter your confession content. ");

        txtContent.setColumns(20);
        txtContent.setRows(5);
        jScrollPane1.setViewportView(txtContent);

        btnImage.setText("Choose Image");
        btnImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImageActionPerformed(evt);
            }
        });

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInstruction1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblInstruction2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblInstruction3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 196, Short.MAX_VALUE)
                .addComponent(txtPostId, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(btnImage))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBack)
                                .addGap(284, 284, 284)
                                .addComponent(btnSubmit))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInstruction1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblInstruction2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPostId, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(lblInstruction3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnBack))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImageActionPerformed
        ImagePage imagePage = new ImagePage(userId);
        imagePage.setVisible(true);
    }//GEN-LAST:event_btnImageActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        String uPostId = txtPostId.getText();
        int postId = -1;
        if(!uPostId.equalsIgnoreCase(""))
            postId = Integer.parseInt(uPostId);
        String content = txtContent.getText();
        
        
        // Get date and time post was created
        Date formattedDate = new java.sql.Date(System.currentTimeMillis());
        String date = formattedDate.toString();
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = localTime.format(formatter);
        
        String SQL = "SELECT Id FROM POSTS WHERE ID = "+postId+"";

        try {
            
            // Execute some sql and load the records in resultset
            stmt = ConnectDB.conn.createStatement(
                                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);

            rs = stmt.executeQuery(SQL);
            
            if( rs.next() || (rs.next() == false && uPostId.equalsIgnoreCase("")) ){
                // Content is mandatory to be filled 
                if(!content.equalsIgnoreCase("")){
                   if(content.endsWith("-1")){
                    content = content.substring(0, content.length()-2);
                    String SQL_INSERT = "INSERT INTO POSTS (PostedById, ReplyPostId, DatePosted, TimePosted, Content) VALUES ("+userId+","+postId+", '"+date+"', '"+time+"', '"+content+"')";
                    int rows = stmt.executeUpdate(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                    if(rows>0){
                        System.out.println("New post has been added!");
                    } 
                    rs = stmt.getGeneratedKeys();
                    if(rs.next()){
                        newPostId = rs.getInt(1);
                        updateImageBlob(newPostId);
                        dispose();

                        // Confession post ID and the submission date and time
                        JOptionPane.showMessageDialog(null, "Submitted at " + date + " " + time
                                                                 + ".\nConfession post ID: " + newPostId
                                                                 + ".\nYour confession will be publilshed soon.");                
                    }
                    stmt.close();    
                    MainPage mainPage = new MainPage(userId);
                    mainPage.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "End your post with -1 to submit your confession.");                
                    } 
                } else {
                    JOptionPane.showMessageDialog(null, "Content field cannot be left blank.");                
                }
                
               
            } else{
                JOptionPane.showMessageDialog(null, "Reply confession post ID does not exists.");                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        dispose();
        MainPage mainPage = new MainPage(userId);
        mainPage.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    // #############################  Image function #############################
    
    // Prepare chosen image for storing
    private InputStream processImage(){
        InputStream input =null;
        // Only prepare image for storing if there's changes
        if(ImagePage.path !=null){
            File imgFile =new File(ImagePage.path);

            try {
                 input = new FileInputStream(imgFile);
            } catch (FileNotFoundException ex) {
                 ex.printStackTrace();
            }
        }
        return input;
    }
    
    // Save the image separately
    private void updateImageBlob(int newPostId){
        InputStream imageStream = processImage();
        
        if(imageStream != null)
        {
            try{
                String SQL_IMAGE =  "SELECT * FROM POSTS WHERE Id = "+newPostId+"";
                ResultSet imageRS = stmt.executeQuery(SQL_IMAGE);

                //update image blob
                if (imageRS.next()){
                    imageRS.updateBlob("IMAGE", imageStream);
                }
                imageRS.updateRow();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    
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
            java.util.logging.Logger.getLogger(NewConfessionPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewConfessionPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewConfessionPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewConfessionPost.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewConfessionPost().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JToggleButton btnImage;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblInstruction1;
    private javax.swing.JLabel lblInstruction2;
    private javax.swing.JLabel lblInstruction3;
    private javax.swing.JTextArea txtContent;
    private javax.swing.JTextField txtPostId;
    // End of variables declaration//GEN-END:variables
}
