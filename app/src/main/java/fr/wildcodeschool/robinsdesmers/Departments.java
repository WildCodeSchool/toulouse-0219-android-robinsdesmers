package fr.wildcodeschool.robinsdesmers;

public class Departments {

    private String nameDepartment;
    private int number;
    private boolean selected;

    public Departments() {

    }

    public Departments(String nameDepartment, int number) {
        this.nameDepartment = nameDepartment;
        this.number = number;
        this.selected = false;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
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
