package com.denis_adidas.calendar.view;

import com.denis_adidas.calendar.ToDay;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;


public class Month extends javax.swing.JPanel {

    private int month;
    private int year;
    public Month(int month, int year) {
        initComponents();
        this.month = month;
        this.year = year;
        init();
    }
    
    private void init(){ 
        Sun.asTitle();
        Tue.asTitle();
        Wed.asTitle();
        Thu.asTitle();
        Fri.asTitle();
        Sat.asTitle();
        Mon.asTitle();
        setDate();
    }
    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);  //  month jan as 0 so start from 0
        calendar.set(Calendar.DATE, 1);
        int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;  //  get day of week -1 to index
        calendar.add(Calendar.DATE, -startDay);
        ToDay toDay = getToDay();
        for (Component com : getComponents()) {
            Cell cell = (Cell) com;
            if (!cell.isTitle()) {
                cell.setText(calendar.get(Calendar.DATE) + "");
                cell.setDate(calendar.getTime());
                cell.currentMonth(calendar.get(Calendar.MONTH) == month - 1);
                if (toDay.isToDay(new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR))))
                    cell.setAsToDay();
                calendar.add(Calendar.DATE, 1); //  up 1 day
            }
        }
    }
    
    private ToDay getToDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Sun = new com.denis_adidas.calendar.view.Cell();
        Tue = new com.denis_adidas.calendar.view.Cell();
        Wed = new com.denis_adidas.calendar.view.Cell();
        Thu = new com.denis_adidas.calendar.view.Cell();
        Fri = new com.denis_adidas.calendar.view.Cell();
        Sat = new com.denis_adidas.calendar.view.Cell();
        Mon = new com.denis_adidas.calendar.view.Cell();
        cell8 = new com.denis_adidas.calendar.view.Cell();
        cell9 = new com.denis_adidas.calendar.view.Cell();
        cell10 = new com.denis_adidas.calendar.view.Cell();
        cell11 = new com.denis_adidas.calendar.view.Cell();
        cell12 = new com.denis_adidas.calendar.view.Cell();
        cell13 = new com.denis_adidas.calendar.view.Cell();
        cell14 = new com.denis_adidas.calendar.view.Cell();
        cell15 = new com.denis_adidas.calendar.view.Cell();
        cell16 = new com.denis_adidas.calendar.view.Cell();
        cell17 = new com.denis_adidas.calendar.view.Cell();
        cell18 = new com.denis_adidas.calendar.view.Cell();
        cell19 = new com.denis_adidas.calendar.view.Cell();
        cell20 = new com.denis_adidas.calendar.view.Cell();
        cell21 = new com.denis_adidas.calendar.view.Cell();
        cell22 = new com.denis_adidas.calendar.view.Cell();
        cell23 = new com.denis_adidas.calendar.view.Cell();
        cell24 = new com.denis_adidas.calendar.view.Cell();
        cell25 = new com.denis_adidas.calendar.view.Cell();
        cell26 = new com.denis_adidas.calendar.view.Cell();
        cell27 = new com.denis_adidas.calendar.view.Cell();
        cell28 = new com.denis_adidas.calendar.view.Cell();
        cell29 = new com.denis_adidas.calendar.view.Cell();
        cell30 = new com.denis_adidas.calendar.view.Cell();
        cell31 = new com.denis_adidas.calendar.view.Cell();
        cell32 = new com.denis_adidas.calendar.view.Cell();
        cell33 = new com.denis_adidas.calendar.view.Cell();
        cell34 = new com.denis_adidas.calendar.view.Cell();
        cell35 = new com.denis_adidas.calendar.view.Cell();
        cell36 = new com.denis_adidas.calendar.view.Cell();
        cell37 = new com.denis_adidas.calendar.view.Cell();
        cell38 = new com.denis_adidas.calendar.view.Cell();
        cell39 = new com.denis_adidas.calendar.view.Cell();
        cell40 = new com.denis_adidas.calendar.view.Cell();
        cell41 = new com.denis_adidas.calendar.view.Cell();
        cell42 = new com.denis_adidas.calendar.view.Cell();
        cell43 = new com.denis_adidas.calendar.view.Cell();
        cell44 = new com.denis_adidas.calendar.view.Cell();
        cell45 = new com.denis_adidas.calendar.view.Cell();
        cell46 = new com.denis_adidas.calendar.view.Cell();
        cell47 = new com.denis_adidas.calendar.view.Cell();
        cell48 = new com.denis_adidas.calendar.view.Cell();
        cell49 = new com.denis_adidas.calendar.view.Cell();

        setLayout(new java.awt.GridLayout(7, 7));

        Sun.setText("Sun");
        Sun.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        Sun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SunActionPerformed(evt);
            }
        });
        add(Sun);

        Tue.setText("Tue");
        Tue.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(Tue);

        Wed.setText("Wed");
        Wed.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(Wed);

        Thu.setText("Thu");
        Thu.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(Thu);

        Fri.setText("Fri");
        Fri.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(Fri);

        Sat.setText("Sat");
        Sat.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(Sat);

        Mon.setText("Mon");
        Mon.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        Mon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MonActionPerformed(evt);
            }
        });
        add(Mon);

        cell8.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell8);

        cell9.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell9);

        cell10.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell10);

        cell11.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell11);

        cell12.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell12);

        cell13.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell13);

        cell14.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell14);

        cell15.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell15);

        cell16.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell16);

        cell17.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell17);

        cell18.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell18);

        cell19.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell19);

        cell20.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell20);

        cell21.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell21);

        cell22.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell22);

        cell23.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell23);

        cell24.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell24);

        cell25.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell25);

        cell26.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell26);

        cell27.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell27);

        cell28.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell28);

        cell29.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell29);

        cell30.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell30);

        cell31.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell31);

        cell32.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell32);

        cell33.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell33);

        cell34.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell34);

        cell35.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell35);

        cell36.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell36);

        cell37.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell37);

        cell38.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell38);

        cell39.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell39);

        cell40.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell40);

        cell41.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell41);

        cell42.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell42);

        cell43.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell43);

        cell44.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell44);

        cell45.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell45);

        cell46.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell46);

        cell47.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell47);

        cell48.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        add(cell48);

        cell49.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        cell49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cell49ActionPerformed(evt);
            }
        });
        add(cell49);
    }// </editor-fold>//GEN-END:initComponents

    private void SunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SunActionPerformed

    private void MonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MonActionPerformed

    private void cell49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cell49ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cell49ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.denis_adidas.calendar.view.Cell Fri;
    private com.denis_adidas.calendar.view.Cell Mon;
    private com.denis_adidas.calendar.view.Cell Sat;
    private com.denis_adidas.calendar.view.Cell Sun;
    private com.denis_adidas.calendar.view.Cell Thu;
    private com.denis_adidas.calendar.view.Cell Tue;
    private com.denis_adidas.calendar.view.Cell Wed;
    private com.denis_adidas.calendar.view.Cell cell10;
    private com.denis_adidas.calendar.view.Cell cell11;
    private com.denis_adidas.calendar.view.Cell cell12;
    private com.denis_adidas.calendar.view.Cell cell13;
    private com.denis_adidas.calendar.view.Cell cell14;
    private com.denis_adidas.calendar.view.Cell cell15;
    private com.denis_adidas.calendar.view.Cell cell16;
    private com.denis_adidas.calendar.view.Cell cell17;
    private com.denis_adidas.calendar.view.Cell cell18;
    private com.denis_adidas.calendar.view.Cell cell19;
    private com.denis_adidas.calendar.view.Cell cell20;
    private com.denis_adidas.calendar.view.Cell cell21;
    private com.denis_adidas.calendar.view.Cell cell22;
    private com.denis_adidas.calendar.view.Cell cell23;
    private com.denis_adidas.calendar.view.Cell cell24;
    private com.denis_adidas.calendar.view.Cell cell25;
    private com.denis_adidas.calendar.view.Cell cell26;
    private com.denis_adidas.calendar.view.Cell cell27;
    private com.denis_adidas.calendar.view.Cell cell28;
    private com.denis_adidas.calendar.view.Cell cell29;
    private com.denis_adidas.calendar.view.Cell cell30;
    private com.denis_adidas.calendar.view.Cell cell31;
    private com.denis_adidas.calendar.view.Cell cell32;
    private com.denis_adidas.calendar.view.Cell cell33;
    private com.denis_adidas.calendar.view.Cell cell34;
    private com.denis_adidas.calendar.view.Cell cell35;
    private com.denis_adidas.calendar.view.Cell cell36;
    private com.denis_adidas.calendar.view.Cell cell37;
    private com.denis_adidas.calendar.view.Cell cell38;
    private com.denis_adidas.calendar.view.Cell cell39;
    private com.denis_adidas.calendar.view.Cell cell40;
    private com.denis_adidas.calendar.view.Cell cell41;
    private com.denis_adidas.calendar.view.Cell cell42;
    private com.denis_adidas.calendar.view.Cell cell43;
    private com.denis_adidas.calendar.view.Cell cell44;
    private com.denis_adidas.calendar.view.Cell cell45;
    private com.denis_adidas.calendar.view.Cell cell46;
    private com.denis_adidas.calendar.view.Cell cell47;
    private com.denis_adidas.calendar.view.Cell cell48;
    private com.denis_adidas.calendar.view.Cell cell49;
    private com.denis_adidas.calendar.view.Cell cell8;
    private com.denis_adidas.calendar.view.Cell cell9;
    // End of variables declaration//GEN-END:variables
}
