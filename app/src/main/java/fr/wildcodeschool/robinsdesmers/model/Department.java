package fr.wildcodeschool.robinsdesmers.model;

public class Department {

    private String name;
    private int number;
    private boolean selected;

    public Department() {

    }

    public Department(String nameDepartment, int number) {
        this.name = nameDepartment;
        this.number = number;
        this.selected = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
