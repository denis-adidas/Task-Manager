package view.component.CalendarForm;

import view.component.calendar.view.Month;
import view.component.calendar.view.PanelSlide;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CalendarCustomMonth extends javax.swing.JLayeredPane {

    private int month;
    private int year;
    private int currentWeek;
    public CalendarCustomMonth() {
        initComponents();
        thisMonth();
        slide.show(new Month(month, year), PanelSlide.AnimateType.TO_RIGHT);
        showMonthYear();
    }
    private void thisMonth() {
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
        todayButton = new javax.swing.JButton();
        lbMonthYear = new javax.swing.JLabel();
        slide = new view.component.calendar.view.PanelSlide();
        cmdNext1 = new javax.swing.JButton();

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

        lbMonthYear.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        lbMonthYear.setForeground(new java.awt.Color(224, 223, 218));
        lbMonthYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMonthYear.setText("Month - Year");

        cmdNext1.setBackground(new java.awt.Color(37, 49, 50));
        cmdNext1.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        cmdNext1.setForeground(new java.awt.Color(224, 223, 218));
        cmdNext1.setText("next");
        cmdNext1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cmdNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNext1ActionPerformed(evt);
            }
        });

        setLayer(cmdPrev, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(todayButton, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(lbMonthYear, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(slide, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(cmdNext1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cmdPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbMonthYear)
                        .addGap(18, 18, 18)
                        .addComponent(cmdNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(240, 240, 240)
                        .addComponent(todayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMonthYear, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdNext1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(todayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(slide, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrevActionPerformed
        if (month == 1) {
            month = 12;
            year--;
        } else {
            month--;
        }
        slide.show(new Month(month, year), PanelSlide.AnimateType.TO_RIGHT);
        showMonthYear();
    }//GEN-LAST:event_cmdPrevActionPerformed

    private void todayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todayButtonActionPerformed
        setToDay();
        slide.show(new Month(month, year), PanelSlide.AnimateType.TO_LEFT);
        showMonthYear();        
    }//GEN-LAST:event_todayButtonActionPerformed

    private void cmdNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNext1ActionPerformed
        if (month == 12) {
            month = 1;
            year++;
        } else {
            month++;
        }
        slide.show(new Month(month, year), PanelSlide.AnimateType.TO_LEFT);
        showMonthYear();
    }//GEN-LAST:event_cmdNext1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdNext1;
    private javax.swing.JButton cmdPrev;
    private javax.swing.JLabel lbMonthYear;
    private view.component.calendar.view.PanelSlide slide;
    private javax.swing.JButton todayButton;
    // End of variables declaration//GEN-END:variables
}
