package fr.wildcodeschool.robinsdesmers.model;

public class Department {

    private String name;
    private String number;
    private boolean selected;

    public Department(Department department) {
    }

    public Department(String nameDepartment, String number) {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
