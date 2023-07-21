package view.component.CalendarForm;

import view.component.calendar.view.Day;
import view.component.calendar.view.PanelSlide;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CalendarCustomDay extends javax.swing.JLayeredPane {

    private int month;
    private int year;
    private int currentWeek;
    private int day;
    private Day dayCalendar;
    
    public CalendarCustomDay() {
        initComponents();
        thisWeekMonth();
        dayCalendar = new Day(day);
        slide.show(dayCalendar, PanelSlide.AnimateType.TO_RIGHT);
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
    public Day getDay() {
        return dayCalendar;
    }
    
    private void setToDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        day = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdPrev = new javax.swing.JButton();
        cmdNext = new javax.swing.JButton();
        lbMonthYear = new javax.swing.JLabel();
        slide = new view.component.calendar.view.PanelSlide();
        TodayButton = new javax.swing.JButton();

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

        TodayButton.setBackground(new java.awt.Color(37, 49, 50));
        TodayButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        TodayButton.setForeground(new java.awt.Color(224, 223, 218));
        TodayButton.setText("return today");
        TodayButton.setBorder(null);
        TodayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TodayButtonActionPerformed(evt);
            }
        });

        setLayer(cmdPrev, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(cmdNext, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(lbMonthYear, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(slide, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(TodayButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbMonthYear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdNext, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 328, Short.MAX_VALUE)
                .addComponent(TodayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addComponent(slide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmdPrev, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(lbMonthYear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdNext, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TodayButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void TodayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TodayButtonActionPerformed
        setToDay();
        slide.show(new Day(day), PanelSlide.AnimateType.TO_LEFT);
        showMonthYear();
    }//GEN-LAST:event_TodayButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton TodayButton;
    private javax.swing.JButton cmdNext;
    private javax.swing.JButton cmdPrev;
    private javax.swing.JLabel lbMonthYear;
    private view.component.calendar.view.PanelSlide slide;
    // End of variables declaration//GEN-END:variables
}
