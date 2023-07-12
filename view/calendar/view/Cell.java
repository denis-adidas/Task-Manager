package com.denis_adidas.calendar.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;


public class Cell extends JButton{
    
    private Date data;
    private boolean title;
    private boolean isToDay;
    public Cell() {
        setContentAreaFilled(false);
        setBorder(null);
        setHorizontalAlignment(JLabel.CENTER);
    }
    public void setDate(Date data) {
        this.data = data;
    }
    public void asTitle(){ 
        title = true;
    }
    public boolean isTitle(){ 
        return title;
    }
    public void currentMonth(boolean act) {
        if (act) {
            setForeground(new Color(168, 52, 235));
        }
        else {
            setForeground(Color.red);
        }
    }
    public void setAsToDay() {
        isToDay = true;
        setForeground(Color.white);
    }
    @Override 
    protected void paintComponent(Graphics grphcs) {
        if (title){
            grphcs.setColor(Color.BLACK);
            grphcs.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
        }
        if (isToDay) {
            Graphics2D g2 = (Graphics2D)grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(97, 49, 237));
            int x = getWidth() / 2 - 15;
            int y = getHeight()/2 - 15;
            g2.fillRoundRect(x, y, 30, 30, 100, 100);
        }
        super.paintComponent(grphcs);
    }
}
