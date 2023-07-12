package com.denis_adidas.calendar;

import com.denis_adidas.calendar.view.Day;
import com.denis_adidas.calendar.view.PanelSlide;
import com.denis_adidas.calendar.view.Week;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CalendarCustomDay extends javax.swing.JLayeredPane {

    private int month;
    private int year;
    private int currentWeek;
    private int day;
    
    public CalendarCustomDay() {
        initComponents();
        thisWeekMonth();
        slide.show(new Day(day), PanelSlide.AnimateType.TO_RIGHT);
        showMonthYear();
    }
    private void thisWeekMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        day = calendar.get(Calendar.DATE);
        currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);
    }
    private void showMonthYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DATE, 1);
        SimpleDateFormat df = new SimpleDateFormat("MMMM-yyyy");
        lbMonthYear.setText(df.format(calendar.getTime()));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdPrev = new javax.swing.JButton();
        cmdNext = new javax.swing.JButton();
        lbMonthYear = new javax.swing.JLabel();
        slide = new com.denis_adidas.calendar.view.PanelSlide();

        cmdPrev.setText("back");
        cmdPrev.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmdPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPrevActionPerformed(evt);
            }
        });

        cmdNext.setText("next");
        cmdNext.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmdNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNextActionPerformed(evt);
            }
        });

        lbMonthYear.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        lbMonthYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMonthYear.setText("Month - Year");

        setLayer(cmdPrev, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(cmdNext, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(lbMonthYear, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(slide, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(lbMonthYear)
                        .addGap(18, 18, 18)
                        .addComponent(cmdNext, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdNext, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMonthYear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmdPrev, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrevActionPerformed
        day--;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, day);
        month = calendar.get(Calendar.MONTH) + 1;
        currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        year = calendar.get(Calendar.YEAR);
        slide.show(new Day(day), PanelSlide.AnimateType.TO_RIGHT);
        showMonthYear();
    }//GEN-LAST:event_cmdPrevActionPerformed

    private void cmdNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNextActionPerformed
        day++;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, day);
        month = calendar.get(Calendar.MONTH) + 1;
        currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        year = calendar.get(Calendar.YEAR);
        slide.show(new Day(day), PanelSlide.AnimateType.TO_LEFT);
        showMonthYear();
    }//GEN-LAST:event_cmdNextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdNext;
    private javax.swing.JButton cmdPrev;
    private javax.swing.JLabel lbMonthYear;
    private com.denis_adidas.calendar.view.PanelSlide slide;
    // End of variables declaration//GEN-END:variables
}
