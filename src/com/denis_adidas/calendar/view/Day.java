package com.denis_adidas.calendar.view;

import component.view.ScrollBarCustom;
import data.TaskListManager;
import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import model.TaskList;


public class Day extends javax.swing.JPanel {

    private int day;
    private int month;
    private LocalDate date;
    private LocalTime timeNow = LocalTime.now();
    
    public Day(int day) {
        initComponents();
        this.day = day;
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        TableColumn firstColumn = table.getColumnModel().getColumn(0);
        firstColumn.setMaxWidth(50);
        setDate();
        loadDayTask();
        
        ColoredRowRenderer rowRenderer = new ColoredRowRenderer();
        table.setDefaultRenderer(Object.class, rowRenderer);
        rowRenderer.setRowToColor(timeNow.getHour());
    }
    public Day() {
        initComponents();
    }
    
    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd, EE");
        
        calendar.set(Calendar.DAY_OF_MONTH, day);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        
        month = calendar.get(Calendar.MONTH)+1;
        date = LocalDate.of(LocalDate.now().getYear(), month, day);
        
        TableColumn firstColumn = table.getColumnModel().getColumn(1);
        Date date = calendar.getTime();
        String formattedDate = sdf.format(date);
        firstColumn.setHeaderValue(formattedDate);
    }
    
    public void loadDayTask() {
        removeData();
        TaskList temp = new TaskList(); 
        for (model.Task x : TaskListManager.getInstance().getTaskList().getTasks()) {
            if (x.getTargetDate().equals(date)) {
                temp.addTask(x);
            }
        }
        for (model.Task x : temp.getTasks()) {
            if (!x.isCompleted()) {
            if (table.getValueAt(Integer.parseInt(x.getTargetTimeAsString()), 1) == null) 
                table.setValueAt(x.getName(), Integer.parseInt(x.getTargetTimeAsString()), 1);
            else if (!table.getValueAt(Integer.parseInt(x.getTargetTimeAsString()), 1).equals(x.getName())){
                StringBuilder str = new StringBuilder();
                str.append(table.getValueAt(Integer.parseInt(x.getTargetTimeAsString()), 1)).append(", ").append(x.getName());
                table.setValueAt(str, Integer.parseInt(x.getTargetTimeAsString()), 1);
                
            }
        }
        table.revalidate();
        table.repaint();
    }
    }
    static class ColoredRowRenderer extends DefaultTableCellRenderer {
        private final Color rowColor = new Color(230, 181, 195);
        private int rowToColor = -1;

        public void setRowToColor(int rowToColor) {
            this.rowToColor = rowToColor;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (row == rowToColor && !isSelected) {
                cellComponent.setBackground(rowColor);
            } else {
                cellComponent.setBackground(table.getBackground());
            }

            return cellComponent;
        }
    }
    private void removeData() {
        for (int i = 0; i < table.getRowCount(); i++) {
            table.setValueAt(null, i, 1);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        table.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"0", null},
                {"1", null},
                {"2", null},
                {"3", null},
                {"4", null},
                {"5", null},
                {"6", null},
                {"7", null},
                {"8", null},
                {"9", null},
                {"10", null},
                {"11", null},
                {"12", null},
                {"13", null},
                {"14", null},
                {"15", null},
                {"16", null},
                {"17", null},
                {"18", null},
                {"19", null},
                {"20", null},
                {"21", null},
                {"22", null},
                {"23", null}
            },
            new String [] {
                "", "11, Tue"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setAutoscrolls(false);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setRowSelectionAllowed(false);
        table.setShowGrid(true);
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 763, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
