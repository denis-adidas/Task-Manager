package com.denis_adidas.calendar;

import com.denis_adidas.calendar.view.PanelSlide;
import com.denis_adidas.calendar.view.Week;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CalendarCustomWeek extends javax.swing.JLayeredPane {

    private int month;
    private int year;
    private int currentWeek;
    private Week weekCalendar;
    
    public CalendarCustomWeek() {
        initComponents();
        thisWeekMonth();
        weekCalendar = new Week(currentWeek, month);
        slide.show(weekCalendar, PanelSlide.AnimateType.TO_RIGHT);
        showMonthYear();
    }
    private void thisWeekMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
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
    public Week getWeek() {
        return weekCalendar;
    }
    private void setToDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdPrev = new javax.swing.JButton();
        cmdNext = new javax.swing.JButton();
        lbMonthYear = new javax.swing.JLabel();
        slide = new com.denis_adidas.calendar.view.PanelSlide();
        todayButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(715, 534));

        cmdPrev.setBackground(new java.awt.Color(37, 49, 50));
        cmdPrev.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        cmdPrev.setForeground(new java.awt.Color(224, 223, 218));
        cmdPrev.setText("back");
        cmdPrev.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmdPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPrevActionPerformed(evt);
            }
        });

        cmdNext.setBackground(new java.awt.Color(37, 49, 50));
        cmdNext.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        cmdNext.setForeground(new java.awt.Color(224, 223, 218));
        cmdNext.setText("next");
        cmdNext.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmdNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNextActionPerformed(evt);
            }
        });

        lbMonthYear.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        lbMonthYear.setForeground(new java.awt.Color(224, 223, 218));
        lbMonthYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMonthYear.setText("Month - Year");

        todayButton.setBackground(new java.awt.Color(37, 49, 50));
        todayButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        todayButton.setForeground(new java.awt.Color(224, 223, 218));
        todayButton.setText("return today");
        todayButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        todayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todayButtonActionPerformed(evt);
            }
        });

        setLayer(cmdPrev, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(cmdNext, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(lbMonthYear, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(slide, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(todayButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbMonthYear)
                .addGap(18, 18, 18)
                .addComponent(cmdNext, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                .addComponent(todayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addComponent(slide, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMonthYear, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdNext, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(todayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(slide, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrevActionPerformed
        if(currentWeek == 1) {
            currentWeek=52;
            year--;
            month=12;
        }
        else {
            currentWeek--;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.WEEK_OF_YEAR, currentWeek);
            month = calendar.get(Calendar.MONTH) + 1;
        }
        slide.show(new Week(currentWeek, month), PanelSlide.AnimateType.TO_RIGHT);
        showMonthYear();
    }//GEN-LAST:event_cmdPrevActionPerformed

    private void cmdNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNextActionPerformed
        if(currentWeek == 52) {
            currentWeek=1;
            year++;
            month=1;
        }
        else {
            currentWeek++;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.WEEK_OF_YEAR, currentWeek);
            month = calendar.get(Calendar.MONTH) + 1;
        }
        slide.show(new Week(currentWeek, month), PanelSlide.AnimateType.TO_LEFT);
        showMonthYear();
    }//GEN-LAST:event_cmdNextActionPerformed

    private void todayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todayButtonActionPerformed
        setToDay();
        slide.show(new Week(currentWeek, month), PanelSlide.AnimateType.TO_LEFT);
        showMonthYear();
    }//GEN-LAST:event_todayButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdNext;
    private javax.swing.JButton cmdPrev;
    private javax.swing.JLabel lbMonthYear;
    private com.denis_adidas.calendar.view.PanelSlide slide;
    private javax.swing.JButton todayButton;
    // End of variables declaration//GEN-END:variables
}
