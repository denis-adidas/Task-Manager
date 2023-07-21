package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Task extends TaskItem{
    @SerializedName("targetTime")
    @Expose
    private LocalTime targetTime = LocalTime.now();
    @SerializedName("targetDate")
    @Expose
    private LocalDate targetDate = LocalDate.now();
    @SerializedName("targetDay")
    @Expose
    private DayOfWeek targetDay = targetDate.getDayOfWeek();
    @SerializedName("assignee")
    @Expose
    private String assignee;
    @SerializedName("subTasks")
    @Expose
    private final ArrayList<SubTask> subTaskList = new ArrayList<>();

    public Task() {
    }
    public Task(Task task) {
        super(task.getId(), task.getName());
        this.setTargetDate(task.getTargetDate());
        this.setTargetTime(task.getTargetTime());
        this.setAssignee(task.getAssignee());
        this.getSubTaskList().addAll(task.getSubTaskList());
    }

    public Task(int id, String name, LocalTime targetTime, LocalDate targetDate) {
        super(id, name);
        this.setTargetTime(targetTime);
        this.setTargetDate(targetDate);
        this.setTargetDay(targetDate.getDayOfWeek());
    }
    public Task(int id, String name, String assignee, LocalTime targetTime, LocalDate targetDate) {
        super(id, name);
        this.assignee = assignee;
        this.setTargetTime(targetTime);
        this.setTargetDate(targetDate);
        this.setTargetDay(targetDate.getDayOfWeek());
    }
    public Task(int id, String name, LocalTime targetTime, LocalDate targetDate, SubTask[] subTasks) {
        super(id, name);
        this.setTargetTime(targetTime);
        this.setTargetDate(targetDate);
        this.setTargetDay(targetDate.getDayOfWeek());
        this.getSubTaskList().addAll(Arrays.asList(subTasks));
    }
    public Task(Task task, LocalDate targetDate) {
        super(task.getId(), task.getName());
        this.setTargetTime(task.getTargetTime());
        this.setTargetDate(targetDate);
        this.getSubTaskList().addAll(task.getSubTaskList());
    }
    public LocalTime getTargetTime() {
        return targetTime;
    }

    public String getTargetTimeAsString() {
        return this.getTargetTime().format(DateTimeFormatter.ofPattern("HH"));
    }

    public void setTargetTime(LocalTime targetTime) {
        this.targetTime = targetTime;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }
    public String getTargetDateAsString() {
        return this.getTargetDate().format(DateTimeFormatter.ofPattern("dd/MM", Locale.ENGLISH));
    }


    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public DayOfWeek getTargetDay() {
        return targetDay;
    }
    public void setTargetDay(DayOfWeek targetDay) {
        this.targetDay = targetDay;
    }

    public String getTargetDayAsString() {
        return this.getTargetDay().toString();
    }

    public ArrayList<SubTask> getSubTaskList() {
        return subTaskList;
    }
    public boolean isDueThisWeek() {
        LocalDate today = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.ENGLISH);
        return targetDate.get(weekFields.weekOfWeekBasedYear()) == today.get(weekFields.weekOfWeekBasedYear());
    }
    public boolean isDueToday() {
        return targetDate.equals(LocalDate.now());
    }
    public boolean isDueThisMonth() {
        LocalDate today = LocalDate.now();
        return targetDate.getYear() == today.getYear() && targetDate.getMonth() == today.getMonth();
    }
    public boolean isDueNextMonth() {
        LocalDate today = LocalDate.now().plusMonths(1);
        return targetDate.getYear() == today.getYear() && targetDate.getMonth() == today.getMonth();
    }
    @Override
    public String toString() {
        return this.getName() + " Assignee: " +this.getAssignee() + " Target day: " + this.getTargetDayAsString() + " " +
                getTargetDateAsString() + " Target time: " + getTargetTimeAsString();
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    public void addSubTask(String task) {
        subTaskList.add(new SubTask(1, task)); 
    }
}