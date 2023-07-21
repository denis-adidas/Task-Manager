package component.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class Menu extends javax.swing.JPanel {


    public Menu() {
        initComponents();
        setOpaque(false);
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(45, 61, 62));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D)grphcs;
        g2.setColor(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRect(getWidth() - 25, 0, getWidth(), getHeight());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
