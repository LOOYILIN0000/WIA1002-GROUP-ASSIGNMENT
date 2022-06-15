
package B_SearchPost;

import A_StartApp.MainPage;


public class SearchMenu extends javax.swing.JFrame {

    private int userId;
    
    public SearchMenu(){
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public SearchMenu(int userId) {
        initComponents();
        setLocationRelativeTo(null); 
        this.userId = userId;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSearchKeywords = new javax.swing.JButton();
        btnSearchPostID = new javax.swing.JButton();
        btnPublishedDate = new javax.swing.JButton();
        btnPublishedDateTime = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSearchKeywords.setText("Search By Keywords");
        btnSearchKeywords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchKeywordsActionPerformed(evt);
            }
        });

        btnSearchPostID.setText("Search By Post ID");
        btnSearchPostID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchPostIDActionPerformed(evt);
            }
        });

        btnPublishedDate.setText("Search By Published Date");
        btnPublishedDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublishedDateActionPerformed(evt);
            }
        });

        btnPublishedDateTime.setText("Search By Published Date & Time");
        btnPublishedDateTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPublishedDateTimeActionPerformed(evt);
            }
        });

        jLabel1.setText("                    Search Menu");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBack)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnSearchKeywords, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSearchPostID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPublishedDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPublishedDateTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearchKeywords)
                .addGap(18, 18, 18)
                .addComponent(btnSearchPostID)
                .addGap(18, 18, 18)
                .addComponent(btnPublishedDate)
                .addGap(18, 18, 18)
                .addComponent(btnPublishedDateTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchKeywordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchKeywordsActionPerformed
        dispose();
        SearchKeyword searchKeyword = new SearchKeyword(userId);
        searchKeyword.setVisible(true);
    }//GEN-LAST:event_btnSearchKeywordsActionPerformed

    private void btnSearchPostIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchPostIDActionPerformed
        dispose();
        SearchPostId searchPostId = new SearchPostId(userId);
        searchPostId.setVisible(true);
    }//GEN-LAST:event_btnSearchPostIDActionPerformed

    private void btnPublishedDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublishedDateActionPerformed
        dispose();
        SearchPublishedDate searchPublishedDate = new SearchPublishedDate(userId);
        searchPublishedDate.setVisible(true);
    }//GEN-LAST:event_btnPublishedDateActionPerformed

    private void btnPublishedDateTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPublishedDateTimeActionPerformed
        dispose();
        SearchPublishedDate_Time searchPublishedDateTime = new SearchPublishedDate_Time(userId);
        searchPublishedDateTime.setVisible(true);
    }//GEN-LAST:event_btnPublishedDateTimeActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        dispose();
        MainPage mainPage = new MainPage(userId);
        mainPage.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    
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
            java.util.logging.Logger.getLogger(SearchMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchMenu().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnPublishedDate;
    private javax.swing.JButton btnPublishedDateTime;
    private javax.swing.JButton btnSearchKeywords;
    private javax.swing.JButton btnSearchPostID;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
