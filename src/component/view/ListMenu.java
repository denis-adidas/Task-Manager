package component.view;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.Model_Menu;

public class ListMenu<E extends Object> extends JList<E>{
    
    private DefaultListModel model;
    
    public ListMenu() {
        model = new DefaultListModel();
        setModel(model);
        setOpaque(false);
    }

    @Override
    public ListCellRenderer getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean selected, boolean focus) {
                Model_Menu data;
                if (o instanceof Model_Menu) {
                    data = (Model_Menu) o;
                } else {
                    data = new Model_Menu("No Data");
                }
                ItemMenu item = new ItemMenu(data);
                item.setSelected(selected);
                return item;
            }
        };
    }
    public Object getElementAt(int index) {
        return model.getElementAt(index);
    }
    public boolean contains(String text) {
        for (int i = 0; i < model.size(); i++){ 
            if (text.equals(model.getElementAt(i).toString()))
                return true;
        }
        return false;
    }

    public void deleteItem(int index) {
        model.remove(index);
    }
    public void deleteItem(String name) {
        model.removeElement(name);
    }
    public void addItem(Model_Menu data) {

        model.addElement(data);
    }
}