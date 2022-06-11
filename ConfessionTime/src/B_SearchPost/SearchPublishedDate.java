
package B_SearchPost;

import B_ViewPost.MainPost;
import Utilities.ConnectDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SearchPublishedDate extends javax.swing.JFrame {

    private int userId;
    private int clickedPostId;
    private Statement stmt;
    private ResultSet rs;
    private DefaultTableModel model;
    
    public SearchPublishedDate() {
        initComponents();
        setLocationRelativeTo(null);
    }

    public SearchPublishedDate(int userId) {
        initComponents();
        setLocationRelativeTo(null);
        this.userId = userId;
        
        //model for Search Published Date Table
        model = new DefaultTableModel(){
            public Class<?> getColumnClass(int column) {
                switch(column){
                    case 0:
                     return String.class;
                    case 1:
                     return String.class;
                    case 2:
                     return String.class;
                    default:
                     return String.class;
                }
            }
            
            @Override
            public boolean isCellEditable(int row, int column) {
                switch (column){
                    case 0:
                     return false;
                    case 1:
                     return false;
                    case 2:
                     return false;
                    default:
                     return false;
                }
            }
        };
        
        //Assign the model to table
        jTableContent.setModel(model);
        
        model.addColumn("Posts Id");
        model.addColumn("Date Published");
        model.addColumn("Content");
        
        //Get the row from database
        try{
            GetContent (model);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void filterDate(){
        TableRowSorter <DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) jTableContent.getModel());
        RowFilter rowFilter = RowFilter.regexFilter(searchDateTxt.getText(), 1);
        sorter.setRowFilter(rowFilter); 
        jTableContent.setRowSorter(sorter);
    }
    
    private void GetContent(DefaultTableModel model) throws SQLException{
        try{
            //connect to the database
            //execute some sql and load the records in resultset
            stmt = ConnectDB.conn.createStatement(
                                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                                     ResultSet.CONCUR_UPDATABLE);
            
            String SQL = "SELECT ID, DATEPOSTED, CONTENT FROM POSTS";
            rs = stmt.executeQuery(SQL);
            int i = 0;
            while(rs.next()){              
                String postId = rs.getString("ID");
                String datePosted = rs.getString("DATEPOSTED");
                String content = rs.getString("CONTENT");
                model.addRow(new Object[0]);
                model.setValueAt(postId, i, 0); // post Id
                model.setValueAt(datePosted, i, 1);   // date posted
                model.setValueAt(content, i, 2);    // content
                i++;
            }   
             
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack1 = new javax.swing.JButton();
        lblDate = new javax.swing.JLabel();
        searchDateTxt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableContent = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();

        btnBack1.setText("Back");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblDate.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblDate.setText("Search By Published Date:");

        searchDateTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchDateTxtKeyReleased(evt);
            }
        });

        jTableContent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableContent.setColumnSelectionAllowed(true);
        jTableContent.getTableHeader().setReorderingAllowed(false);
        jTableContent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableContentMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableContent);

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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(searchDateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(searchDateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableContentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableContentMouseClicked
        if (evt.getClickCount()==2) {
            int rowIndex = jTableContent.convertRowIndexToModel(jTableContent.getSelectedRow());
            this.clickedPostId = Integer.parseInt( jTableContent.getModel().getValueAt(rowIndex, 0).toString());

            dispose();
            //Opens Posts Page
            MainPost mainPost = new MainPost(userId, clickedPostId);
            mainPost.setVisible(true);
        }
    }//GEN-LAST:event_jTableContentMouseClicked

    private void searchDateTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchDateTxtKeyReleased
        filterDate();
    }//GEN-LAST:event_searchDateTxtKeyReleased

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        dispose();
        SearchMenu searchMenu = new SearchMenu(userId);
        searchMenu.setVisible(true);
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
            java.util.logging.Logger.getLogger(SearchPublishedDate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchPublishedDate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchPublishedDate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchPublishedDate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchPublishedDate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBack1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableContent;
    private javax.swing.JLabel lblDate;
    private javax.swing.JTextField searchDateTxt;
    // End of variables declaration//GEN-END:variables
}
