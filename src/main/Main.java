package main;

import com.denis_adidas.calendar.Calendar;
import com.denis_adidas.component.ToDoForm.ToDo;
import component.view.ScrollBarCustom;
import data.JsonFileManager;
import data.TaskListManager;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

import model.Model_Menu;


public class Main extends javax.swing.JFrame {

    CardLayout cl ;
    public Main() {
        initComponents();
        init();
        scrollMenu.setVerticalScrollBar(new ScrollBarCustom());
        WindowAdapter windowAdapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                TaskListManager.getInstance().saveTasks();
                super.windowClosing(e);
            }
        };

        addWindowListener(windowAdapter);

        String iconPath = "icon.png";
        ImageIcon icon = new ImageIcon(iconPath);
        setIconImage(icon.getImage());
        setTitle("Today I'm going to write rap😎😎");
    }
    
     private void init() {
        JsonFileManager.getInstance().importTasks("autosave.json");
        updateMenu();
        scrollMenu.getViewport().setOpaque(false);
        setMainTime();
    }

    public void updateMenu() {
        cl = (CardLayout) MainPanel.getLayout();

        menuList.addItem(new Model_Menu("Calendar"));
        menuList.addItem(new Model_Menu("Todo"));
        menuList.addItem(new Model_Menu("Completed"));


        updateAssigneeList();

        MainPanel.add(new Calendar(), "Calendar");
        MainPanel.add(new ToDo(), "Todo");

        menuList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Object selectedValue = menuList.getSelectedValue();
                if (selectedValue instanceof Model_Menu) {
                    Model_Menu menu = (Model_Menu) selectedValue;
                    String panelName = menu.getMenuName();
                    if ("Todo".equals(panelName)) {
                        ToDo todo = (ToDo)MainPanel.getComponent(1);
                        todo.updateTasksPanel();
                        cl.show(MainPanel, "Todo");
                        checkList();
                        updateAssigneeList();
                    }
                    if ("Calendar".equals(panelName)) {
                        Calendar calendar = (Calendar) MainPanel.getComponent(0);
                        calendar.updateTasks();
                        calendar.repaint();
                        cl.show(MainPanel, "Calendar");
                        checkList();
                        updateAssigneeList();
                    }
                    if ("Completed".equals(panelName)) {
                        ToDo todo = (ToDo)MainPanel.getComponent(1);
                        todo.updateTasksPanel(TaskListManager.getInstance().getCompletedTask());
                        cl.show(MainPanel, "Todo");
                        checkList();
                        updateAssigneeList();
                    }
                    if (menuList.contains(panelName) && !"Calendar".equals(panelName) && !"Todo".equals(panelName) && !"Completed".equals(panelName)) {
                        ToDo todo = (ToDo)MainPanel.getComponent(1);
                        todo.updateTasksPanel(TaskListManager.getInstance().getAssigneeList(panelName));
                        cl.show(MainPanel, "Todo");
                        checkList();
                        updateAssigneeList();
                    }
                }


            }
        });
    }
    private void updateAssigneeList() {
        for (String x : TaskListManager.getInstance().getAssigneeList(true)) {
            if (x != null && !x.equals("") && !menuList.contains(x))
                menuList.addItem(new Model_Menu(x));
        }
    }
    private void checkList() {
        for (int i = 3; i < menuList.getModel().getSize(); i++) {
            if (!TaskListManager.getInstance().getAssigneeList(true).contains(menuList.getElementAt(i).toString()) ){
                menuList.deleteItem(i);
            }
        }
    }
     private void setMainTime() {
         new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        System.err.println(e);
                    }
                    Date date = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("hh:mm aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, MMMM dd");
                    String time = tf.format(date);
                    lbTime.setText(time.split(" ")[0]);
                    lbType.setText(time.split(" ")[1]);
                    lbDate.setText(df.format(date));
                }
            }
        }).start();
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new model.Panel();
        menu1 = new component.view.Menu();
        scrollMenu = new javax.swing.JScrollPane();
        menuList = new component.view.ListMenu<>();
        MainPanel = new javax.swing.JPanel();
        lbTime = new javax.swing.JLabel();
        lbDate = new javax.swing.JLabel();
        lbType = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setBackground(new java.awt.Color(23, 24, 23));

        scrollMenu.setBorder(null);
        scrollMenu.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollMenu.setViewportBorder(null);
        scrollMenu.setViewportView(menuList);

        scrollMenu.setViewportView(menuList);

        javax.swing.GroupLayout menu1Layout = new javax.swing.GroupLayout(menu1);
        menu1.setLayout(menu1Layout);
        menu1Layout.setHorizontalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
        );
        menu1Layout.setVerticalGroup(
            menu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(scrollMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );

        MainPanel.setBackground(new java.awt.Color(23, 24, 23));
        MainPanel.setLayout(new java.awt.CardLayout());

        lbTime.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        lbTime.setForeground(new java.awt.Color(224, 223, 218));
        lbTime.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTime.setText("11:33");

        lbDate.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        lbDate.setForeground(new java.awt.Color(224, 223, 218));
        lbDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbDate.setText("Monday, July 10");

        lbType.setFont(new java.awt.Font("Inter", 0, 24)); // NOI18N
        lbType.setForeground(new java.awt.Color(224, 223, 218));
        lbType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbType.setText("PM");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(lbTime, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbType, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbDate, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                        .addGap(640, 640, 640))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbType, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTime)
                    .addComponent(lbDate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(692, 692, 692))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbTime;
    private javax.swing.JLabel lbType;
    private component.view.Menu menu1;
    private component.view.ListMenu<String> menuList;
    private model.Panel panel1;
    private javax.swing.JScrollPane scrollMenu;
    // End of variables declaration//GEN-END:variables
}
