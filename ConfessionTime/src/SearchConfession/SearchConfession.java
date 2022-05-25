
package SearchConfession;

import Utilitities.ConnectDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

        
public class SearchConfession extends javax.swing.JFrame {
    
    private int userId;
    private String postContent;
    private int postId;
    private Date datePosted;
    private Time timePosted;
    
    ConnectDB connDB;
    Statement stmt;
    ResultSet rs;
    
    DefaultTableModel modelKeyword;
    DefaultTableModel modelPost_ID;
    DefaultTableModel modelDate;
    DefaultTableModel modelDate_Time;
            
    public SearchConfession() { 
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public SearchConfession(int userId) {
        initComponents();
        setLocationRelativeTo(null);
        this.userId = userId;
        
        //model for Keywords Table
        modelKeyword = new DefaultTableModel(){
            @Override
            public Class<?> getColumnClass(int column) {
                return switch (column) {
                    case 0 -> String.class;
                    case 1 -> String.class;
                    default -> String.class;
                };
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return switch (column) {
                    case 0 -> false;
                    case 1 -> false;
                    default -> false;
                };
            }    
        };

        //model for Post ID Table
        modelPost_ID = new DefaultTableModel(){
            @Override
            public Class<?> getColumnClass(int column) {
                return switch (column) {
                    case 0 -> String.class;
                    case 1 -> String.class;
                    default -> String.class;
                };
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return switch (column) {
                    case 0 -> false;
                    case 1 -> false;
                    default -> false;
                };
            }    
        };         

        //model for Date Table
        modelDate = new DefaultTableModel(){
            @Override
            public Class<?> getColumnClass(int column) {
                return switch (column) {
                    case 0 -> String.class;
                    case 1 -> String.class;
                    default -> String.class;
                };
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return switch (column) {
                    case 0 -> false;
                    case 1 -> false;
                    default -> false;
                };
            }    
        };         

        //model for Date_Time Table
        modelDate_Time = new DefaultTableModel(){
            @Override
            public Class<?> getColumnClass(int column) {
                return switch (column) {
                    case 0 -> String.class;
                    case 1 -> String.class;
                    default -> String.class;
                };
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return switch (column) {
                    case 0 -> false;
                    case 1 -> false;
                    default -> false;
                };
            }    
        };

        //Assign the model to table
        jTableKeywords.setModel(modelKeyword);
        jTableID.setModel(modelPost_ID);
        jTableDate.setModel(modelDate);
        jTableDate_Time.setModel(modelDate_Time);
        
        modelKeyword.addColumn("postContent");
        modelKeyword.addColumn("postID");
        
        modelPost_ID.addColumn("postID");
        //modelPost_ID.addColumn();
        
        modelDate.addColumn("Date");
        modelDate.addColumn("postID");
        
        modelDate_Time.addColumn("Date");
        modelDate_Time.addColumn("Time");
        modelDate_Time.addColumn("postID");
        
        //hide column Post Id (for keywords Search)
        jTableKeywords.getColumnModel().getColumn(1).setMinWidth(0);
        jTableKeywords.getColumnModel().getColumn(1).setMaxWidth(0);
        jTableKeywords.getColumnModel().getColumn(1).setWidth(0);
        jTableKeywords.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableKeywords.setCellSelectionEnabled(true); 
        
        //hide column Post Id (Date search)
        jTableDate.getColumnModel().getColumn(1).setMinWidth(0);
        jTableDate.getColumnModel().getColumn(1).setMaxWidth(0);
        jTableDate.getColumnModel().getColumn(1).setWidth(0);
        jTableDate.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableDate.setCellSelectionEnabled(true); 
        
        //hide column Post Id (Date_Time Search)
        jTableDate_Time.getColumnModel().getColumn(1).setMinWidth(0);
        jTableDate_Time.getColumnModel().getColumn(1).setMaxWidth(0);
        jTableDate_Time.getColumnModel().getColumn(1).setWidth(0);
        jTableDate_Time.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTableDate_Time.setCellSelectionEnabled(true); 
        
        
        //Get the row from database
        GetKeyword (modelKeyword);
        GetPost_ID(modelPost_ID);
        GetDate(modelDate);
        GetDate_Time(modelDate_Time);
       
    }   
    private void filterKeyword(String query){
        TableRowSorter <DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) jTableKeywords.getModel());
        sorter.setRowFilter(RowFilter.regexFilter("(?i).*\\Q" + query + "\\E.*"));
        jTableKeywords.setRowSorter(sorter);
    }
    
    private void filterPost_ID(String query){
        TableRowSorter <DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) jTableID.getModel());
        sorter.setRowFilter(RowFilter.regexFilter("(?i).*\\Q" + query + "\\E.*"));
        jTableID.setRowSorter(sorter);
    }
   
    private void filterDate(String query){
        TableRowSorter <DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) jTableDate.getModel());
        sorter.setRowFilter(RowFilter.regexFilter("(?i).*\\Q" + query + "\\E.*"));
        jTableDate.setRowSorter(sorter);
    }
    
    private void filterDate_Time(String query){
        TableRowSorter <DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) jTableDate_Time.getModel());
        sorter.setRowFilter(RowFilter.regexFilter("(?i).*\\Q" + query + "\\E.*"));
        jTableDate_Time.setRowSorter(sorter);
    }
     
    
    private void GetKeyword(DefaultTableModel modelKeyword) {
         try{
            //connect to the database
            //execute some sql and load the records in resultset
            stmt = ConnectDB.conn.createStatement(
                                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                                     ResultSet.CONCUR_UPDATABLE);
            
            String SQL = "SELECT ID, CONTENT FROM SWIFTIES.POSTS";
            rs = stmt.executeQuery( SQL );
            int i = 0;
            while(rs.next()){              
                String postID = rs.getString("ID");
                String content = rs.getString("CONTENT");
                modelKeyword.addRow(new Object[0]);
                modelKeyword.setValueAt(content, i, 0);
                modelKeyword.setValueAt(postID, i, 1);  
                i++;
            }   
             
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
    
    }

    private void GetPost_ID(DefaultTableModel modelPost_ID) {
        try{
            //connect to the database
            //execute some sql and load the records in resultset
            stmt = ConnectDB.conn.createStatement(
                                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                                     ResultSet.CONCUR_UPDATABLE);
            
            String SQL = "SELECT ID FROM SWIFTIES.POSTS";
            rs = stmt.executeQuery( SQL );
            int i = 0;
            while(rs.next()){              
                String postID = rs.getString("ID");
                modelPost_ID.addRow(new Object[0]);
                modelPost_ID.setValueAt(postID, i, 1);  
                i++;
            }   
             
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
    
    }

    private void GetDate(DefaultTableModel modelDate) {
        try{
            //connect to the database
            //execute some sql and load the records in resultset
            stmt = ConnectDB.conn.createStatement(
                                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                                     ResultSet.CONCUR_UPDATABLE);
            
            String SQL = "SELECT ID, DATEPOSTED FROM SWIFTIES.POSTS";
            rs = stmt.executeQuery( SQL );
            int i = 0;
            while(rs.next()){              
                String postID = rs.getString("ID");
                String date = rs.getString("DATEPOSTED");
                modelDate.addRow(new Object[0]);
                modelDate.setValueAt(date, i, 0);
                modelDate.setValueAt(postID, i, 1);  
                i++;
            }   
             
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
    
    }

    private void GetDate_Time(DefaultTableModel modelDate_Time) {
        try{
            //connect to the database
            //execute some sql and load the records in resultset
            stmt = ConnectDB.conn.createStatement(
                                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                                     ResultSet.CONCUR_UPDATABLE);
            
            String SQL = "SELECT ID, DATEPOSTED, TIMEPOSTED FROM SWIFTIES.POSTS";
            rs = stmt.executeQuery( SQL );
            int i = 0;
            while(rs.next()){              
                String postID = rs.getString("ID");
                String date = rs.getString("DATEPOSTED");
                String time = rs.getString("TIMEPOSTED");

                modelDate_Time.addRow(new Object[0]);
                modelDate_Time.setValueAt(date, i, 0);
                modelDate_Time.setValueAt(time, i, 0);
                modelDate_Time.setValueAt(postID, i, 1);  
                i++;
            }   
             
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
    
    }       



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SearchLabel = new javax.swing.JLabel();
        HomeButton = new javax.swing.JButton();
        SearchByKeywordsPanel = new javax.swing.JPanel();
        Keyword_Field = new javax.swing.JTextField();
        KeywordSearch = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableKeywords = new javax.swing.JTable();
        SearchByIDPanel = new javax.swing.JPanel();
        ID_Search = new javax.swing.JLabel();
        ID_Field = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableID = new javax.swing.JTable();
        SearchByDate_TimePanel = new javax.swing.JPanel();
        Date_Time_Field = new javax.swing.JTextField();
        Date_TimeSearch = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableDate_Time = new javax.swing.JTable();
        SearchByDatePanel = new javax.swing.JPanel();
        DateSearch = new javax.swing.JLabel();
        Date_Field = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableDate = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Search Confession");
        setPreferredSize(new java.awt.Dimension(1000, 850));

        SearchLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        SearchLabel.setText("SEARCH PUBLISHED CONFESSION POST");

        HomeButton.setText("Back To Home Page");
        HomeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeButtonActionPerformed(evt);
            }
        });

        SearchByKeywordsPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SearchByKeywordsPanel.setPreferredSize(new java.awt.Dimension(400, 253));

        Keyword_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Keyword_FieldActionPerformed(evt);
            }
        });

        KeywordSearch.setText("Search By Keywords :");

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jTableKeywords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableKeywords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableKeywordsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableKeywords);

        javax.swing.GroupLayout SearchByKeywordsPanelLayout = new javax.swing.GroupLayout(SearchByKeywordsPanel);
        SearchByKeywordsPanel.setLayout(SearchByKeywordsPanelLayout);
        SearchByKeywordsPanelLayout.setHorizontalGroup(
            SearchByKeywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchByKeywordsPanelLayout.createSequentialGroup()
                .addGroup(SearchByKeywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SearchByKeywordsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(KeywordSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Keyword_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SearchByKeywordsPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        SearchByKeywordsPanelLayout.setVerticalGroup(
            SearchByKeywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchByKeywordsPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(SearchByKeywordsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(KeywordSearch)
                    .addComponent(Keyword_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        SearchByIDPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SearchByIDPanel.setPreferredSize(new java.awt.Dimension(400, 253));

        ID_Search.setText("Search By Confession post ID :");

        ID_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID_FieldActionPerformed(evt);
            }
        });

        jTableID.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableIDMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableID);

        javax.swing.GroupLayout SearchByIDPanelLayout = new javax.swing.GroupLayout(SearchByIDPanel);
        SearchByIDPanel.setLayout(SearchByIDPanelLayout);
        SearchByIDPanelLayout.setHorizontalGroup(
            SearchByIDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchByIDPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(SearchByIDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(SearchByIDPanelLayout.createSequentialGroup()
                        .addComponent(ID_Search)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ID_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        SearchByIDPanelLayout.setVerticalGroup(
            SearchByIDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchByIDPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(SearchByIDPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ID_Search)
                    .addComponent(ID_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        SearchByDate_TimePanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SearchByDate_TimePanel.setPreferredSize(new java.awt.Dimension(400, 253));

        Date_Time_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Date_Time_FieldActionPerformed(evt);
            }
        });

        Date_TimeSearch.setText("Search By Date and Time :");

        jTableDate_Time.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableDate_Time.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDate_TimeMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTableDate_Time);

        javax.swing.GroupLayout SearchByDate_TimePanelLayout = new javax.swing.GroupLayout(SearchByDate_TimePanel);
        SearchByDate_TimePanel.setLayout(SearchByDate_TimePanelLayout);
        SearchByDate_TimePanelLayout.setHorizontalGroup(
            SearchByDate_TimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchByDate_TimePanelLayout.createSequentialGroup()
                .addGroup(SearchByDate_TimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SearchByDate_TimePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Date_TimeSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Date_Time_Field, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SearchByDate_TimePanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        SearchByDate_TimePanelLayout.setVerticalGroup(
            SearchByDate_TimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchByDate_TimePanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(SearchByDate_TimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Date_TimeSearch)
                    .addComponent(Date_Time_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        SearchByDatePanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SearchByDatePanel.setPreferredSize(new java.awt.Dimension(400, 253));

        DateSearch.setText("Search By Date :");

        Date_Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Date_FieldActionPerformed(evt);
            }
        });

        jTableDate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDateMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTableDate);

        javax.swing.GroupLayout SearchByDatePanelLayout = new javax.swing.GroupLayout(SearchByDatePanel);
        SearchByDatePanel.setLayout(SearchByDatePanelLayout);
        SearchByDatePanelLayout.setHorizontalGroup(
            SearchByDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchByDatePanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(SearchByDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SearchByDatePanelLayout.createSequentialGroup()
                        .addComponent(DateSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Date_Field)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        SearchByDatePanelLayout.setVerticalGroup(
            SearchByDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchByDatePanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(SearchByDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DateSearch)
                    .addComponent(Date_Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SearchByKeywordsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SearchByDatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SearchByIDPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SearchByDate_TimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addComponent(SearchLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(403, 403, 403)
                        .addComponent(HomeButton)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SearchLabel)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SearchByKeywordsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchByIDPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SearchByDatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchByDate_TimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                .addComponent(HomeButton)
                .addGap(30, 30, 30))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Keyword_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Keyword_FieldActionPerformed
        String query = KeywordSearch.getText().toLowerCase();
        filterKeyword(query);    }//GEN-LAST:event_Keyword_FieldActionPerformed

    private void Date_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Date_FieldActionPerformed
        String query = DateSearch.getText().toLowerCase();
        filterDate(query);    }//GEN-LAST:event_Date_FieldActionPerformed

    private void ID_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID_FieldActionPerformed
        String query = ID_Search.getText().toLowerCase();
        filterPost_ID(query);    }//GEN-LAST:event_ID_FieldActionPerformed

    private void Date_Time_FieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Date_Time_FieldActionPerformed
        String query = Date_TimeSearch.getText().toLowerCase();
        filterDate_Time(query);    }//GEN-LAST:event_Date_Time_FieldActionPerformed

    private void HomeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeButtonActionPerformed
        dispose();
    }//GEN-LAST:event_HomeButtonActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jTableDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDateMouseClicked
        if (evt.getClickCount()==2) {
            int rowIndex = jTableDate.convertRowIndexToModel(jTableDate.getSelectedRow());
            this.datePosted = Integer.parseInt( jTableDate.getModel().getValueAt(rowIndex, 1).toString());
            
            //Opens Confession page (View Confession)
            //ViewConfessionPage confessionPage = new ViewConfessionPage(ID, timePosted);
            //confessionPage.setVisible(true);  
        }
    }//GEN-LAST:event_jTableDateMouseClicked

    private void jTableDate_TimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDate_TimeMouseClicked
        if (evt.getClickCount()==2) {
            int rowIndex = jTableDate.convertRowIndexToModel(jTableDate.getSelectedRow());
            this.datePosted = Integer.parseInt( jTableDate.getModel().getValueAt(rowIndex, 1).toString());
            
            //Opens Confession page (View Confession)
            //ViewConfessionPage confessionPage = new ViewConfessionPage(ID, timePosted);
            //confessionPage.setVisible(true);
      }//GEN-LAST:event_jTableDate_TimeMouseClicked
    }
    
    private void jTableIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableIDMouseClicked
        if (evt.getClickCount()==2) {
            int rowIndex = jTableID.convertRowIndexToModel(jTableID.getSelectedRow());
            this.postId = Integer.parseInt( jTableID.getModel().getValueAt(rowIndex, 1).toString());
            
            //Opens Confession page (View Confession)
            //ViewConfessionPage confessionPage = new ViewConfessionPage(ID, timePosted);
            //confessionPage.setVisible(true); 
     }//GEN-LAST:event_jTableIDMouseClicked
    }
    
    private void jTableKeywordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKeywordsMouseClicked
        if (evt.getClickCount()==2) {
            int rowIndex = jTableKeywords.convertRowIndexToModel(jTableKeywords.getSelectedRow());
            this.postContent =(String) jTableKeywords.getModel().getValueAt(rowIndex, 1);
            
            //Opens Confession page (View Confession)
            //ViewConfessionPage confessionPage = new ViewConfessionPage(ID, timePosted);
            //confessionPage.setVisible(true);  
    }//GEN-LAST:event_jTableKeywordsMouseClicked
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
            java.util.logging.Logger.getLogger(SearchConfession.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchConfession.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchConfession.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchConfession.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchConfession().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DateSearch;
    private javax.swing.JTextField Date_Field;
    private javax.swing.JLabel Date_TimeSearch;
    private javax.swing.JTextField Date_Time_Field;
    private javax.swing.JButton HomeButton;
    private javax.swing.JTextField ID_Field;
    private javax.swing.JLabel ID_Search;
    private javax.swing.JLabel KeywordSearch;
    private javax.swing.JTextField Keyword_Field;
    private javax.swing.JPanel SearchByDatePanel;
    private javax.swing.JPanel SearchByDate_TimePanel;
    private javax.swing.JPanel SearchByIDPanel;
    private javax.swing.JPanel SearchByKeywordsPanel;
    private javax.swing.JLabel SearchLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTableDate;
    private javax.swing.JTable jTableDate_Time;
    private javax.swing.JTable jTableID;
    private javax.swing.JTable jTableKeywords;
    // End of variables declaration//GEN-END:variables


 }
