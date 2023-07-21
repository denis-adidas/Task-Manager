package model;

public class Model_Menu {
    private String menuName;
    
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public Model_Menu(String name) {
        this.menuName = name;
    }
    @Override
    public String toString(){ 
        return menuName;
    }
}
