
package B_SearchPost;

import B_ViewPost.MainPost;
import Utilities.ConnectDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SearchPublishedDate_Time extends javax.swing.JFrame {

    private int userId;
    private int clickedPostId;
    private Statement stmt;
    private ResultSet rs;
    private DefaultTableModel model;
    
    public SearchPublishedDate_Time() {
        initComponents();
    }

    public SearchPublishedDate_Time(int userId) {
        initComponents();
        setLocationRelativeTo(null);
        this.userId = userId;
        
        //model for Search Published Date & Time Table
        model = new DefaultTableModel(){
            public Class<?> getColumnClass(int column) {
                switch(column){
                    case 0:
                     return String.class;   // post Id
                    case 1:
                     return String.class;   // Date
                    case 2:
                     return String.class;   // Time
                    case 3:
                     return String.class; // Content
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
                    case 3:
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
        model.addColumn("Time Posted");
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
    
    private void filterTime(){
        TableRowSorter <DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) jTableContent.getModel());
        RowFilter rowFilter = RowFilter.regexFilter(searchTimeTxt.getText(), 2);
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
            
            String SQL = "SELECT ID, DATEPOSTED, TIMEPOSTED, CONTENT FROM POSTS";
            rs = stmt.executeQuery(SQL);
            int i = 0;
            while(rs.next()){              
                String postId = rs.getString("ID");
                String datePosted = rs.getString("DATEPOSTED");
                String timePosted = rs.getString("TIMEPOSTED");
                String content = rs.getString("CONTENT");
                model.addRow(new Object[0]);
                model.setValueAt(postId, i, 0); // post Id
                model.setValueAt(datePosted, i, 1);   // date posted
                model.setValueAt(timePosted, i, 2);  // time posted
                model.setValueAt(content, i, 3);    // content
                i++;
            }   
             
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDate = new javax.swing.JLabel();
        searchDateTxt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableContent = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        searchTimeTxt = new javax.swing.JTextField();
        btnBack = new javax.swing.JButton();

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

        jLabel1.setText("Search By Published Time:");

        searchTimeTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTimeTxtKeyReleased(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(searchDateTxt)
                                    .addComponent(searchTimeTxt)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(searchDateTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchTimeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchDateTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchDateTxtKeyReleased
        filterDate();
    }//GEN-LAST:event_searchDateTxtKeyReleased

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

    private void searchTimeTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTimeTxtKeyReleased
        filterTime();
    }//GEN-LAST:event_searchTimeTxtKeyReleased

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
            java.util.logging.Logger.getLogger(SearchPublishedDate_Time.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchPublishedDate_Time.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchPublishedDate_Time.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchPublishedDate_Time.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchPublishedDate_Time().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableContent;
    private javax.swing.JLabel lblDate;
    private javax.swing.JTextField searchDateTxt;
    private javax.swing.JTextField searchTimeTxt;
    // End of variables declaration//GEN-END:variables
}
