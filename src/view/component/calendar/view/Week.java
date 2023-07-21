package view.component.calendar.view;

import view.component.CalendarForm.ToDay;
import view.component.menu.ScrollBarCustom;
import data.TaskListManager;
import java.awt.Color;
import java.awt.Component;
import java.io.InvalidObjectException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import model.TaskList;


public class Week extends javax.swing.JPanel {

    private int month;
    private int week;
    LocalDate date;
    public Week(int week, int month) {
        initComponents();
        this.month = month;
        this.week = week;
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        setDate();
        loadTask();
        
        ColoredRowRenderer rowRenderer = new ColoredRowRenderer();
        table.setDefaultRenderer(Object.class, rowRenderer);

        LocalTime currentTime = LocalTime.now();
        int rowIndexForCurrentTime = 0; // Индекс строки для текущего времени (например, первая строка)
        rowRenderer.setRowToColor(currentTime.getHour());

    }
    public Week() {
        initComponents();
    }
    
    private void setDate() {
    Date today = Calendar.getInstance().getTime();
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.WEEK_OF_YEAR, week);
    SimpleDateFormat sdf = new SimpleDateFormat("dd, EE");
    int startDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
    calendar.add(Calendar.DATE, -startDay);
    
    TableColumn tempColumn = table.getColumnModel().getColumn(0);
    tempColumn.setHeaderValue("Time");
    
    month = calendar.get(Calendar.MONTH) + 1;
    
    for (int i = 1; i < table.getColumnCount(); i++) {
        tempColumn = table.getColumnModel().getColumn(i);
        Date date = calendar.getTime();
        String formattedDate = sdf.format(date);
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        tempColumn.setHeaderValue(formattedDate);
    }
}

    public void updateWeek(int week, int month) {
        this.week = week;
        this.month = month;
        setDate();
    }
    
    public void loadTask() {
        TaskList temp = new TaskList();
        TableColumnModel columnModel = table.getColumnModel();
        int columnCount = columnModel.getColumnCount();   
        String[] day;
        for (int columnIndex = 1; columnIndex < columnCount; columnIndex++) {
            TableColumn column = columnModel.getColumn(columnIndex);
            String headerValue = column.getHeaderValue().toString();
            day = headerValue.split(", ");
            date = LocalDate.of(LocalDate.now().getYear(), month, Integer.parseInt(day[0]));
            loadDayTask(date, columnIndex);
        }
        table.revalidate();
        table.repaint();
    }
   public void loadDayTask(LocalDate date, int column) {
       removeData(column);
        TaskList temp = new TaskList();
        for (model.Task x : TaskListManager.getInstance().getTaskList().getTasks()) {
            
            if (x.getTargetDate().equals(date)) {
                temp.addTask(x);
            }
        }
        for (model.Task x : temp.getTasks()) {
            if (!x.isCompleted()) {
            if (table.getValueAt(Integer.parseInt(x.getTargetTimeAsString()), column) == null) 
                table.setValueAt(x.getName(), Integer.parseInt(x.getTargetTimeAsString()), column);
            else {
                StringBuilder str = new StringBuilder();
                str.append(table.getValueAt(Integer.parseInt(x.getTargetTimeAsString()), column)).append(", ").append(x.getName());
                table.setValueAt(str, Integer.parseInt(x.getTargetTimeAsString()), column);
            }
        }
    }
   }
   private void removeData(int column) {
        for (int i = 0; i < table.getRowCount(); i++) {
            table.setValueAt(null, i,column );
        }
    }
      
    
    private ToDay getToDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return new ToDay(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
    }

    static class ColoredRowRenderer extends DefaultTableCellRenderer {
        private final Color rowColor = new Color(230, 181, 195);
        private int rowToColor = -1;
        private int columnToColor = -1;
        private Date dateToColor;
        private int columnIndexForDate;
        private boolean colorByDate = false;
        private boolean colorByTime = false;

        public void setRowToColor(int rowToColor) {
            this.rowToColor = rowToColor;
        }

        public void setColumnToColor(int columnToColor) {
            this.columnToColor = columnToColor;
        }

        public void setColumnToColorByDate(Date date, int columnIndexForDate) {
            this.dateToColor = date;
            this.columnIndexForDate = columnIndexForDate;
            colorByDate = true;
        }

        public void setRowToColorByTime(LocalTime time, int rowIndexForTime) {
            int currentHour = time.getHour();
            if (currentHour == rowIndexForTime) {
                rowToColor = rowIndexForTime;
                colorByTime = true;
            }
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            // Получаем компонент для текущей ячейки
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Окрашиваем определенную строку
            if (row == rowToColor && !isSelected) {
                cellComponent.setBackground(rowColor);
            } else if (colorByDate && column == columnIndexForDate) {
                // Окрашиваем колонку с заданной датой
                Object columnValue = table.getValueAt(row, column);
                if (columnValue instanceof LocalDate && columnValue.equals(dateToColor)) {
                    cellComponent.setBackground(rowColor);
                } else {
                    cellComponent.setBackground(table.getBackground());
                }
            } else if (colorByTime && row == rowToColor) {
                // Окрашиваем строку с текущим временем
                cellComponent.setBackground(rowColor);
            } else {
                // Если условие не выполнено, устанавливаем фон по умолчанию
                cellComponent.setBackground(table.getBackground());
            }

            return cellComponent;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        jScrollPane1.setHorizontalScrollBar(null);

        table.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"0", null, null, null, null, null, null, null},
                {"1", null, null, null, null, null, null, null},
                {"2", null, null, null, null, null, null, null},
                {"3", null, null, null, null, null, null, null},
                {"4", null, null, null, null, null, null, null},
                {"5", null, null, null, null, null, null, null},
                {"6", null, null, null, null, null, null, null},
                {"7", null, null, null, null, null, null, null},
                {"8", null, null, null, null, null, null, null},
                {"9", null, null, null, null, null, null, null},
                {"10", null, null, null, null, null, null, null},
                {"11", null, null, null, null, null, null, null},
                {"12", null, null, null, null, null, null, null},
                {"13", null, null, null, null, null, null, null},
                {"14", null, null, null, null, null, null, null},
                {"15", null, null, null, null, null, null, null},
                {"16", null, null, null, null, null, null, null},
                {"17", null, null, null, null, null, null, null},
                {"18", null, null, null, null, null, null, null},
                {"19", null, null, null, null, null, null, null},
                {"20", null, null, null, null, null, null, null},
                {"21", null, null, null, null, null, null, null},
                {"22", null, null, null, null, null, null, null},
                {"23", null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
            }
        ));
        table.setAutoscrolls(false);
        table.setFillsViewportHeight(true);
        table.setGridColor(new java.awt.Color(224, 223, 218));
        table.setRowHeight(30);
        table.setRowSelectionAllowed(false);
        table.setSelectionBackground(new java.awt.Color(255, 255, 255));
        table.setShowGrid(true);
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
