
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author CALE
 */
public class SortGUI extends javax.swing.JFrame {
    
    DefaultTableModel songModel = new DefaultTableModel();
    
    /**
     * Creates new form SortGUI
     */
    public SortGUI() {
        initComponents();
        
        songModel = new DefaultTableModel();
        songModel.addColumn("Artist Name");
        songModel.addColumn("Song Name");
        songModel.addColumn("Song Runtime (in seconds)");
        
        songTable.setModel(songModel);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fileLocationTxt = new javax.swing.JTextField();
        loadDataBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        bubleSortBtn = new javax.swing.JRadioButton();
        insertSortBtn = new javax.swing.JRadioButton();
        mergeSortBtn = new javax.swing.JRadioButton();
        runtimeSortBtn = new javax.swing.JButton();
        songSortBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        songTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        timeTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("File:");

        loadDataBtn.setText("Load Data");
        loadDataBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadDataBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Sort By:");

        buttonGroup1.add(bubleSortBtn);
        bubleSortBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bubleSortBtn.setText("Bubble Sort");
        bubleSortBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bubleSortBtnActionPerformed(evt);
            }
        });

        buttonGroup1.add(insertSortBtn);
        insertSortBtn.setText("Insertion Sort");

        buttonGroup1.add(mergeSortBtn);
        mergeSortBtn.setText("Merge Sort");

        runtimeSortBtn.setText("Sort By Runtime");
        runtimeSortBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runtimeSortBtnActionPerformed(evt);
            }
        });

        songSortBtn.setText("Sort By Song");
        songSortBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                songSortBtnActionPerformed(evt);
            }
        });

        songTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(songTable);

        jLabel3.setText("Total Sort Time (in milliseconds):");

        timeTxt.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(timeTxt))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(fileLocationTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(loadDataBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)
                        .addComponent(bubleSortBtn)
                        .addGap(35, 35, 35)
                        .addComponent(insertSortBtn)
                        .addGap(38, 38, 38)
                        .addComponent(mergeSortBtn)
                        .addGap(18, 18, 18)
                        .addComponent(songSortBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(runtimeSortBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fileLocationTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadDataBtn))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(bubleSortBtn)
                    .addComponent(insertSortBtn)
                    .addComponent(mergeSortBtn)
                    .addComponent(runtimeSortBtn)
                    .addComponent(songSortBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(timeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bubleSortBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubleSortBtnActionPerformed
   
    }//GEN-LAST:event_bubleSortBtnActionPerformed

    private void loadDataBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadDataBtnActionPerformed
        try {
            PS2Sort.loadArray(fileLocationTxt.getText());
            refreshTable();
        } catch (IOException ex) {
            Logger.getLogger(SortGUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "That file doesn't exist!");
            
        }
    }//GEN-LAST:event_loadDataBtnActionPerformed

    private void songSortBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_songSortBtnActionPerformed
        
        if(bubleSortBtn.isSelected()){
            timeTxt.setText(Double.toString(PS2Sort.bubbleSort(0)));
            refreshTable();
            
        }else if(insertSortBtn.isSelected()){
            timeTxt.setText(Double.toString(PS2Sort.insertionSort(0)));
            refreshTable();
        }else if(mergeSortBtn.isSelected()){
            timeTxt.setText(Double.toString(PS2Sort.mergeSort(0)));
            refreshTable();
        }
        
        
    }//GEN-LAST:event_songSortBtnActionPerformed

    private void runtimeSortBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runtimeSortBtnActionPerformed
        if(bubleSortBtn.isSelected()){
            timeTxt.setText(Double.toString(PS2Sort.bubbleSort(1)));
            refreshTable();
        }else if(insertSortBtn.isSelected()){
            timeTxt.setText(Double.toString(PS2Sort.insertionSort(1)));
            refreshTable();
        }else if(mergeSortBtn.isSelected()){
            timeTxt.setText(Double.toString(PS2Sort.mergeSort(1)));
            refreshTable();
        }
    }//GEN-LAST:event_runtimeSortBtnActionPerformed
    
    private void refreshTable(){
        songModel.setRowCount(0);
        
        if(PS2Sort.getSongs().length !=0){
                //System.out.println("inloop");
               for(int i = 0; i < PS2Sort.getSongs().length; i ++){
                   String[] row = new String[7];
                   
                   row[0] = PS2Sort.getSongs()[i].getaName();
                   row[1] = PS2Sort.getSongs()[i].getsName();
                   row[2] = Integer.toString(PS2Sort.getSongs()[i].getRuntime());
                   
                   songModel.addRow(row);
                   //System.out.println("added row");
               }
           }
    }
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(SortGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SortGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SortGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SortGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SortGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bubleSortBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField fileLocationTxt;
    private javax.swing.JRadioButton insertSortBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadDataBtn;
    private javax.swing.JRadioButton mergeSortBtn;
    private javax.swing.JButton runtimeSortBtn;
    private javax.swing.JButton songSortBtn;
    private javax.swing.JTable songTable;
    private javax.swing.JTextField timeTxt;
    // End of variables declaration//GEN-END:variables
}
