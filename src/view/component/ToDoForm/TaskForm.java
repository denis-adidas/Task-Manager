package view.component.ToDoForm;

import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;


public class TaskForm extends JPanel  {

  private boolean selected;
    private int index;
    private model.Task task;
    

    public TaskForm() {
        initComponents();

    }

    public TaskForm(String text) {
        initComponents();
        setText(text);

    }

    public TaskForm(model.Task task) {
        initComponents();
        this.task = task;
        lbText.setText(task.getName());

    }



    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            lbText.setFont(new java.awt.Font("Inter", 1, 20));
            lbText.setForeground(new Color(224, 223, 218));
        } else {
            lbText.setFont(new java.awt.Font("Inter", 0, 20));
            lbText.setForeground(new Color(224, 223, 218));
        }
    }

    public void setText(String text) {
        lbText.setText(text);
    }
     public void setTask(model.Task task) {
         this.task = task;
         lbText.setText(task.getName());
    }

    public String getText() {
        return lbText.getText();
    }

    public void setLabel(JLabel text) {
        lbText = text;
    }

    public JLabel getLabel() {
        return lbText;
    }

    model.Task getTask() {
        return task;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbText = new javax.swing.JLabel();

        setBackground(new java.awt.Color(30, 35, 35));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 10, 1));

        lbText.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        lbText.setForeground(new java.awt.Color(224, 223, 218));
        lbText.setText("Task1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbText, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbText)
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbText;
    // End of variables declaration//GEN-END:variables
}
