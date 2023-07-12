package com.denis_adidas.calendar.view;

import com.denis_adidas.calendar.ToDay;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.table.TableColumn;


public class Week extends javax.swing.JPanel {

    private int month;
    private int week;
    
    public Week(int week, int month) {
        initComponents();
        this.month = month;
        this.week = week;
        setDate();
    }
    public Week() {
        initComponents();
    }
    
    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        SimpleDateFormat sdf = new SimpleDateFormat("dd, EE");
        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;  
        calendar.add(Calendar.DATE, -startDay);
        TableColumn tempColumn = table.getColumnModel().getColumn(0);
        tempColumn.setHeaderValue("Time");
        for (int i = 1; i < table.getColumnCount(); i++) { 
            tempColumn = table.getColumnModel().getColumn(i);
            Date date = calendar.getTime();
            String formattedDate = sdf.format(date);
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            tempColumn.setHeaderValue(formattedDate);
        }
    }
    public void cmdPrev() {
        
    }
    
    private ToDay getToDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        jScrollPane1.setHorizontalScrollBar(null);

        table.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"0", null, null, null, null, null, null, null},
                {"1", null, null, null, null, null, null, null},
                {"2", null, null, null, null, null, null, null},
                {"3", null, null, null, null, null, null, null},
                {"4", null, null, null, null, null, null, null},
                {"5", null, null, null, null, null, null, null},
                {"6", null, null, null, null, null, null, null},
                {"7", null, null, null, null, null, null, null},
                {"8", null, null, null, null, null, null, null},
                {"9", null, null, null, null, null, null, null},
                {"10", null, null, null, null, null, null, null},
                {"11", null, null, null, null, null, null, null},
                {"12", null, null, null, null, null, null, null},
                {"13", null, null, null, null, null, null, null},
                {"14", null, null, null, null, null, null, null},
                {"15", null, null, null, null, null, null, null},
                {"16", null, null, null, null, null, null, null},
                {"17", null, null, null, null, null, null, null},
                {"18", null, null, null, null, null, null, null},
                {"19", null, null, null, null, null, null, null},
                {"20", null, null, null, null, null, null, null},
                {"21", null, null, null, null, null, null, null},
                {"22", null, null, null, null, null, null, null},
                {"23", null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
            }
        ));
        table.setAutoscrolls(false);
        table.setFillsViewportHeight(true);
        table.setGridColor(new java.awt.Color(224, 223, 218));
        table.setRowHeight(30);
        table.setRowSelectionAllowed(false);
        table.setSelectionBackground(new java.awt.Color(255, 255, 255));
        table.setShowGrid(true);
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
