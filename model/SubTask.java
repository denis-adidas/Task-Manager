package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubTask extends TaskItem {
    @SerializedName("order")
    @Expose
    private int order;
    public SubTask() {

    }
    public SubTask(int id, String name, int order) {
        super(id, name);
        this.setOrder(order);
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return this.getName();
    }
    public String toString(boolean includeOrder){
        return includeOrder ? this.getOrder() + ". " + this.getName() : this.getName();
    }
}
