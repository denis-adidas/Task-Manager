package view.component.ToDoForm;

import view.component.menu.ScrollBarCustom;
import data.TaskListManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY;
import model.Task;
import model.TaskList;
import model.TaskQuery;



public class ToDo extends javax.swing.JPanel {
    
   private TaskForm selectedTaskForm;
   public ToDo() {
        initComponents();
        init();
    }
   void init() {
    TaskListPanel.setLayout(new BoxLayout(TaskListPanel, BoxLayout.Y_AXIS));
    addButton.addActionListener(new ActionListener() {
            @Override
           public void actionPerformed(ActionEvent e) {
                addTaskFromTextArea();
            }
        });
    AddTaskForm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddTaskForm.setText(null); 
            }
        });

        AddTaskForm.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (AddTaskForm.getText().isEmpty()) {
                    AddTaskForm.setText("Try typing \"Homework for tomorrow's lesson\""); 
                }
            }
        });
        searchBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchBar.setText(null); 
            }
        });

        searchBar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (searchBar.getText().isEmpty()) {
                    searchBar.setText("smth"); 
                }
            }
        });
        subTaskAddForm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                subTaskAddForm.setText(null); 
            }
        });

        subTaskAddForm.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (subTaskAddForm.getText().isEmpty()) {
                    subTaskAddForm.setText("Sub task"); 
                }
            }
        });
    TaskListScroll.setVerticalScrollBar(new ScrollBarCustom());
    TaskListPanel.setTransferHandler(transferHandler);
    updateTasksPanel();
    
   }
   public void updateTasksPanel() {
        TaskListPanel.removeAll();
        TaskListPanel.setTransferHandler(transferHandler);
        for (Task x : TaskListManager.getInstance().getTaskList().getTasks()) {
            if (!x.isCompleted()) {
                TaskForm task = new TaskForm(x);
                task.addMouseListener(taskMouseListener);
                task.setTransferHandler(transferHandler);
                TaskListPanel.add(task);
            }
        }
        for (Task x : TaskListManager.getInstance().getTaskList().getTasks()) {
            if (x.isCompleted()) {
                TaskForm task = new TaskForm(x);
                task.addMouseListener(taskMouseListener);
                task.setTransferHandler(transferHandler);
                TaskListPanel.add(task);
            }
        }
        
        revalidate();
        repaint();
    }
   public void updateTasksPanel(TaskList tasks) {
        TaskListPanel.removeAll();
        TaskListPanel.setTransferHandler(transferHandler);
        for (Task x : tasks.getTasks()) {
            TaskForm task = new TaskForm(x);
            task.addMouseListener(taskMouseListener);
            task.setTransferHandler(transferHandler);
            TaskListPanel.add(task);
        }
        
        revalidate();
        repaint();
    }
   
    private void addTaskFromTextArea() {
        String taskText = AddTaskForm.getText();
        if (!taskText.isEmpty() && !taskText.equals("Try typing \"Homework for tomorrow's lesson\"")) {
            model.Task task = new model.Task(1, taskText, LocalTime.now(), LocalDate.now());
            TaskListManager.getInstance().addTask(task);
            AddTaskForm.setText("");
            
            String choose = (String)sortBy.getSelectedItem();
            TaskQuery query = new TaskQuery("", choose, (String)sortAsDes.getSelectedItem(), TaskQuery.FILTER_BY_ALL, "");
            TaskListManager.getInstance().queryTasks(query, true);
            
            choose = (String)sortAsDes.getSelectedItem();
            query = new TaskQuery("", (String)sortBy.getSelectedItem(), choose, TaskQuery.FILTER_BY_ALL, "");
            TaskListManager.getInstance().queryTasks(query, true);
            
            updateTasksPanel();
        }
        
        
    } 
   
   
   private MouseAdapter taskMouseListener = new MouseAdapter() {  
    @Override
    public void mousePressed(MouseEvent e) {
        JComponent comp = (JComponent) e.getSource();
        TaskForm taskForm = (TaskForm) e.getSource();
        
        TransferHandler handler = comp.getTransferHandler();
        handler.exportAsDrag(comp, e, TransferHandler.COPY);
        
        if (selectedTaskForm != null) {
            selectedTaskForm.getLabel().setFont(new java.awt.Font("Inter", 0, 20));
            selectedTaskForm.repaint();
        }

        taskForm.getLabel().setFont(new java.awt.Font("Inter", 1, 20));
        taskForm.repaint();

        selectedTaskForm = taskForm; 

        model.Task task = selectedTaskForm.getTask();
        updateEditor(selectedTaskForm);
    }
};
   TransferHandler transferHandler = new TransferHandler("background") {
            private TaskForm sourcePanel;

            
            public int getSourceActions(JComponent comp) {
                sourcePanel = (TaskForm) comp;
                return COPY;
            }

            
            public boolean canImport(TransferHandler.TransferSupport support) {
                return support.getComponent() instanceof TaskForm;
            }

            
            public boolean importData(TransferHandler.TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }

                try {
                    TaskForm targetPanel = (TaskForm) support.getComponent();

                    
                    Task source = sourcePanel.getTask();
                    Task target = targetPanel.getTask();

                    sourcePanel.setTask(target);
                    targetPanel.setTask(source);

                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        };
   
   private void updateEditor(TaskForm taskForm) {
    if (taskForm != null) {
        subTaskArea.setText(null);
        model.Task task = taskForm.getTask();
        timeForm.setText(selectedTaskForm.getTask().getTargetTimeAsString());
        EditTaskForm.setText(task.getName());
        listForm.setText(selectedTaskForm.getTask().getAssignee());
        Date date = Date.from(selectedTaskForm.getTask().getTargetDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        TaskListPanel.setTransferHandler(transferHandler);
        dateChooser1.setSelectedDate(date);
        if (!selectedTaskForm.getTask().getSubTaskList().isEmpty()) {
            StringBuilder str = new StringBuilder();
            for (model.SubTask x : task.getSubTaskList())
                str.append(x.getName()).append("\n");
            subTaskArea.setText(str.toString());
        }   
        EditTaskForm.repaint();
    }
} 
   
   private void editSubTaskList(Task task) {
       String str = subTaskArea.getText();
       String[] temp = str.split("\n");
       task.getSubTaskList().clear();
       for (int i = 0; i < temp.length; i++) {
           task.addSubTask(temp[i]);
       }
   }
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new view.component.datechooser.DateChooser();
        sortBy = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        sortAsDes = new javax.swing.JComboBox<>();
        TaskListScroll = new javax.swing.JScrollPane();
        TaskListPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        repeatComboBox = new javax.swing.JComboBox<>();
        listForm = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        subTaskArea = new javax.swing.JTextArea();
        EditTaskForm = new javax.swing.JTextField();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        dateField = new javax.swing.JTextField();
        timeForm = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        addButton1 = new javax.swing.JButton();
        subTaskAddForm = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        AddTaskForm = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        searchBar = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        completeButton = new javax.swing.JButton();

        dateChooser1.setTextRefernce(dateField);

        setBackground(new java.awt.Color(23, 24, 23));

        sortBy.setBackground(new java.awt.Color(37, 49, 50));
        sortBy.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        sortBy.setForeground(new java.awt.Color(224, 223, 218));
        sortBy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Due name", "Due time", "Due date" }));
        sortBy.setBorder(null);
        sortBy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortByActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(224, 223, 218));
        jLabel1.setText("Sort");

        sortAsDes.setBackground(new java.awt.Color(37, 49, 50));
        sortAsDes.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        sortAsDes.setForeground(new java.awt.Color(224, 223, 218));
        sortAsDes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        sortAsDes.setBorder(null);
        sortAsDes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortAsDesActionPerformed(evt);
            }
        });

        TaskListScroll.setBorder(null);
        TaskListScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        TaskListScroll.setViewportBorder(null);
        TaskListScroll.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        TaskListScroll.setOpaque(false);
        TaskListScroll.setViewport(null);

        TaskListPanel.setBackground(new java.awt.Color(23, 24, 23));
        TaskListPanel.setLayout(new javax.swing.BoxLayout(TaskListPanel, javax.swing.BoxLayout.LINE_AXIS));
        TaskListScroll.setViewportView(TaskListPanel);

        jPanel1.setBackground(new java.awt.Color(23, 24, 23));

        jLabel2.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(224, 223, 218));
        jLabel2.setText("List");

        repeatComboBox.setBackground(new java.awt.Color(37, 49, 50));
        repeatComboBox.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        repeatComboBox.setForeground(new java.awt.Color(224, 223, 218));
        repeatComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No repeat", "Every 1 day", "Every 3 days", "Every 7 days" }));
        repeatComboBox.setBorder(null);
        repeatComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repeatComboBoxActionPerformed(evt);
            }
        });

        listForm.setBackground(new java.awt.Color(30, 35, 35));
        listForm.setBorder(null);
        listForm.setForeground(new java.awt.Color(224, 223, 218));
        listForm.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        listForm.setText("New list");
        listForm.setCaretColor(new java.awt.Color(224, 223, 218));
        listForm.setDisabledTextColor(new java.awt.Color(224, 223, 218));
        listForm.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        listForm.setOpaque(true);
        listForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listFormActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(224, 223, 218));
        jLabel3.setText("Repeat");

        jLabel4.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(224, 223, 218));
        jLabel4.setText("Due time");

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        subTaskArea.setBackground(new java.awt.Color(37, 49, 50));
        subTaskArea.setColumns(20);
        subTaskArea.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        subTaskArea.setForeground(new java.awt.Color(224, 223, 218));
        subTaskArea.setRows(5);
        subTaskArea.setBorder(null);
        jScrollPane2.setViewportView(subTaskArea);

        EditTaskForm.setBackground(new java.awt.Color(30, 35, 35));
        EditTaskForm.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        EditTaskForm.setForeground(new java.awt.Color(224, 223, 218));
        EditTaskForm.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        EditTaskForm.setText("Task1");
        EditTaskForm.setBorder(null);
        EditTaskForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditTaskFormActionPerformed(evt);
            }
        });

        editButton.setBackground(new java.awt.Color(37, 49, 50));
        editButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        editButton.setForeground(new java.awt.Color(224, 223, 218));
        editButton.setText("Edit task");
        editButton.setBorder(null);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(37, 49, 50));
        deleteButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(224, 223, 218));
        deleteButton.setText("Delete task");
        deleteButton.setBorder(null);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        dateField.setBackground(new java.awt.Color(30, 35, 35));
        dateField.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        dateField.setForeground(new java.awt.Color(224, 223, 218));
        dateField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        dateField.setBorder(null);
        dateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateFieldActionPerformed(evt);
            }
        });

        timeForm.setBackground(new java.awt.Color(30, 35, 35));
        timeForm.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        timeForm.setForeground(new java.awt.Color(224, 223, 218));
        timeForm.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        timeForm.setBorder(null);
        timeForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeFormActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(224, 223, 218));
        jLabel6.setText("Due date");

        addButton1.setBackground(new java.awt.Color(37, 49, 50));
        addButton1.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        addButton1.setForeground(new java.awt.Color(224, 223, 218));
        addButton1.setText("Add task");
        addButton1.setBorder(null);
        addButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButton1ActionPerformed(evt);
            }
        });

        subTaskAddForm.setBackground(new java.awt.Color(30, 35, 35));
        subTaskAddForm.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        subTaskAddForm.setForeground(new java.awt.Color(224, 223, 218));
        subTaskAddForm.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        subTaskAddForm.setText("Sub task");
        subTaskAddForm.setBorder(null);
        subTaskAddForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subTaskAddFormActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(listForm, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(repeatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(timeForm, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditTaskForm, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subTaskAddForm, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(258, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EditTaskForm, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(repeatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listForm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeForm, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(subTaskAddForm, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        addButton.setBackground(new java.awt.Color(37, 49, 50));
        addButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        addButton.setForeground(new java.awt.Color(224, 223, 218));
        addButton.setText("Add task");
        addButton.setBorder(null);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        AddTaskForm.setBackground(new java.awt.Color(30, 35, 35));
        AddTaskForm.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        AddTaskForm.setForeground(new java.awt.Color(224, 223, 218));
        AddTaskForm.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        AddTaskForm.setText("Try typing \"Homework for tomorrow's lesson\"");
        AddTaskForm.setBorder(null);

        jLabel5.setFont(new java.awt.Font("Inter", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(224, 223, 218));
        jLabel5.setText("Search");

        searchBar.setBackground(new java.awt.Color(30, 35, 35));
        searchBar.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        searchBar.setForeground(new java.awt.Color(224, 223, 218));
        searchBar.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        searchBar.setText(" smth");
        searchBar.setBorder(null);
        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarActionPerformed(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(37, 49, 50));
        searchButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        searchButton.setForeground(new java.awt.Color(224, 223, 218));
        searchButton.setText("letsss");
        searchButton.setBorder(null);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        completeButton.setBackground(new java.awt.Color(37, 49, 50));
        completeButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        completeButton.setForeground(new java.awt.Color(224, 223, 218));
        completeButton.setText("   Complete!");
        completeButton.setBorder(null);
        completeButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        completeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TaskListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(461, 461, 461))
                        .addComponent(AddTaskForm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sortAsDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(completeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(sortBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addComponent(sortAsDes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TaskListScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddTaskForm, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(completeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sortByActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortByActionPerformed
        String choose = (String)sortBy.getSelectedItem();
        TaskQuery query = new TaskQuery("", choose, (String)sortAsDes.getSelectedItem(), TaskQuery.FILTER_BY_ALL, "");
        TaskListManager.getInstance().queryTasks(query, true);
        updateTasksPanel();
    }//GEN-LAST:event_sortByActionPerformed

    private void sortAsDesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortAsDesActionPerformed
        String choose = (String)sortAsDes.getSelectedItem();
        TaskQuery query = new TaskQuery("", (String)sortBy.getSelectedItem(), choose, TaskQuery.FILTER_BY_ALL, "");
        TaskListManager.getInstance().queryTasks(query, true);
        updateTasksPanel();
    }//GEN-LAST:event_sortAsDesActionPerformed

    private void listFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listFormActionPerformed

    private void repeatComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repeatComboBoxActionPerformed
        int choose = repeatComboBox.getSelectedIndex();
        if (choose == 1)
            TaskListManager.getInstance().repeatTask(selectedTaskForm.getTask(), 1);
        if (choose == 2)
            TaskListManager.getInstance().repeatTask(selectedTaskForm.getTask(), 3);
        if (choose == 3)
            TaskListManager.getInstance().repeatTask(selectedTaskForm.getTask(), 7);
        updateTasksPanel();
    }//GEN-LAST:event_repeatComboBoxActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addButtonActionPerformed

    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarActionPerformed
        
    }//GEN-LAST:event_searchBarActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if (selectedTaskForm != null) {
        TaskListManager.getInstance().deleteTask(selectedTaskForm.getTask());
        updateTasksPanel();
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        updateTasksPanel(TaskListManager.getInstance().searchText(searchBar.getText()));
    }//GEN-LAST:event_searchButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if (selectedTaskForm != null) {
            selectedTaskForm.getTask().setName(EditTaskForm.getText());
            selectedTaskForm.getTask().setAssignee(listForm.getText());
            String dateString = dateField.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(dateString, formatter);
            selectedTaskForm.getTask().setTargetDate(date);
            formatter = DateTimeFormatter.ofPattern("HH");
            editSubTaskList(selectedTaskForm.getTask());
            if (Integer.parseInt(timeForm.getText()) > 0 && Integer.parseInt(timeForm.getText()) < 24){
                LocalTime localTime = LocalTime.parse(timeForm.getText(), formatter);
                selectedTaskForm.getTask().setTargetTime(localTime);
            } 
            updateTasksPanel();
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void completeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_completeButtonActionPerformed
        if (selectedTaskForm != null) {
            String originalText = selectedTaskForm.getText();
            String strikethroughText = "<html><s>" + originalText + "</s></html>";
            selectedTaskForm.getTask().setName(strikethroughText);
            selectedTaskForm.getTask().setCompleted(true);        
            updateTasksPanel();
        }
    }//GEN-LAST:event_completeButtonActionPerformed

    private void dateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateFieldActionPerformed

    private void timeFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeFormActionPerformed

    private void EditTaskFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditTaskFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditTaskFormActionPerformed

    private void addButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButton1ActionPerformed
        if (selectedTaskForm != null) {
            selectedTaskForm.getTask().addSubTask(subTaskAddForm.getText());
            updateEditor(selectedTaskForm);
            updateTasksPanel();
        }
    }//GEN-LAST:event_addButton1ActionPerformed

    private void subTaskAddFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subTaskAddFormActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subTaskAddFormActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AddTaskForm;
    private javax.swing.JTextField EditTaskForm;
    private javax.swing.JPanel TaskListPanel;
    private javax.swing.JScrollPane TaskListScroll;
    private javax.swing.JButton addButton;
    private javax.swing.JButton addButton1;
    private javax.swing.JButton completeButton;
    private view.component.datechooser.DateChooser dateChooser1;
    private javax.swing.JTextField dateField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JFormattedTextField listForm;
    private javax.swing.JComboBox<String> repeatComboBox;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox<String> sortAsDes;
    private javax.swing.JComboBox<String> sortBy;
    private javax.swing.JTextField subTaskAddForm;
    private javax.swing.JTextArea subTaskArea;
    private javax.swing.JTextField timeForm;
    // End of variables declaration//GEN-END:variables
}
