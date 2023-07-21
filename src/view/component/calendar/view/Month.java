package view.component.calendar.view;

import view.component.CalendarForm.ToDay;
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

        Sun = new view.component.calendar.view.Cell();
        Tue = new view.component.calendar.view.Cell();
        Wed = new view.component.calendar.view.Cell();
        Thu = new view.component.calendar.view.Cell();
        Fri = new view.component.calendar.view.Cell();
        Sat = new view.component.calendar.view.Cell();
        Mon = new view.component.calendar.view.Cell();
        cell8 = new view.component.calendar.view.Cell();
        cell9 = new view.component.calendar.view.Cell();
        cell10 = new view.component.calendar.view.Cell();
        cell11 = new view.component.calendar.view.Cell();
        cell12 = new view.component.calendar.view.Cell();
        cell13 = new view.component.calendar.view.Cell();
        cell14 = new view.component.calendar.view.Cell();
        cell15 = new view.component.calendar.view.Cell();
        cell16 = new view.component.calendar.view.Cell();
        cell17 = new view.component.calendar.view.Cell();
        cell18 = new view.component.calendar.view.Cell();
        cell19 = new view.component.calendar.view.Cell();
        cell20 = new view.component.calendar.view.Cell();
        cell21 = new view.component.calendar.view.Cell();
        cell22 = new view.component.calendar.view.Cell();
        cell23 = new view.component.calendar.view.Cell();
        cell24 = new view.component.calendar.view.Cell();
        cell25 = new view.component.calendar.view.Cell();
        cell26 = new view.component.calendar.view.Cell();
        cell27 = new view.component.calendar.view.Cell();
        cell28 = new view.component.calendar.view.Cell();
        cell29 = new view.component.calendar.view.Cell();
        cell30 = new view.component.calendar.view.Cell();
        cell31 = new view.component.calendar.view.Cell();
        cell32 = new view.component.calendar.view.Cell();
        cell33 = new view.component.calendar.view.Cell();
        cell34 = new view.component.calendar.view.Cell();
        cell35 = new view.component.calendar.view.Cell();
        cell36 = new view.component.calendar.view.Cell();
        cell37 = new view.component.calendar.view.Cell();
        cell38 = new view.component.calendar.view.Cell();
        cell39 = new view.component.calendar.view.Cell();
        cell40 = new view.component.calendar.view.Cell();
        cell41 = new view.component.calendar.view.Cell();
        cell42 = new view.component.calendar.view.Cell();
        cell43 = new view.component.calendar.view.Cell();
        cell44 = new view.component.calendar.view.Cell();
        cell45 = new view.component.calendar.view.Cell();
        cell46 = new view.component.calendar.view.Cell();
        cell47 = new view.component.calendar.view.Cell();
        cell48 = new view.component.calendar.view.Cell();
        cell49 = new view.component.calendar.view.Cell();

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
    private view.component.calendar.view.Cell Fri;
    private view.component.calendar.view.Cell Mon;
    private view.component.calendar.view.Cell Sat;
    private view.component.calendar.view.Cell Sun;
    private view.component.calendar.view.Cell Thu;
    private view.component.calendar.view.Cell Tue;
    private view.component.calendar.view.Cell Wed;
    private view.component.calendar.view.Cell cell10;
    private view.component.calendar.view.Cell cell11;
    private view.component.calendar.view.Cell cell12;
    private view.component.calendar.view.Cell cell13;
    private view.component.calendar.view.Cell cell14;
    private view.component.calendar.view.Cell cell15;
    private view.component.calendar.view.Cell cell16;
    private view.component.calendar.view.Cell cell17;
    private view.component.calendar.view.Cell cell18;
    private view.component.calendar.view.Cell cell19;
    private view.component.calendar.view.Cell cell20;
    private view.component.calendar.view.Cell cell21;
    private view.component.calendar.view.Cell cell22;
    private view.component.calendar.view.Cell cell23;
    private view.component.calendar.view.Cell cell24;
    private view.component.calendar.view.Cell cell25;
    private view.component.calendar.view.Cell cell26;
    private view.component.calendar.view.Cell cell27;
    private view.component.calendar.view.Cell cell28;
    private view.component.calendar.view.Cell cell29;
    private view.component.calendar.view.Cell cell30;
    private view.component.calendar.view.Cell cell31;
    private view.component.calendar.view.Cell cell32;
    private view.component.calendar.view.Cell cell33;
    private view.component.calendar.view.Cell cell34;
    private view.component.calendar.view.Cell cell35;
    private view.component.calendar.view.Cell cell36;
    private view.component.calendar.view.Cell cell37;
    private view.component.calendar.view.Cell cell38;
    private view.component.calendar.view.Cell cell39;
    private view.component.calendar.view.Cell cell40;
    private view.component.calendar.view.Cell cell41;
    private view.component.calendar.view.Cell cell42;
    private view.component.calendar.view.Cell cell43;
    private view.component.calendar.view.Cell cell44;
    private view.component.calendar.view.Cell cell45;
    private view.component.calendar.view.Cell cell46;
    private view.component.calendar.view.Cell cell47;
    private view.component.calendar.view.Cell cell48;
    private view.component.calendar.view.Cell cell49;
    private view.component.calendar.view.Cell cell8;
    private view.component.calendar.view.Cell cell9;
    // End of variables declaration//GEN-END:variables
}
