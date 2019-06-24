package fr.wildcodeschool.robinsdesmers;

import fr.wildcodeschool.robinsdesmers.model.Department;

public class DepartmentSingleton {
    private static DepartmentSingleton ourInstance = new DepartmentSingleton();
    private Department userDepartment;
    public static DepartmentSingleton getInstance() {
        return ourInstance;
    }

    private DepartmentSingleton() {
    }

    public static DepartmentSingleton getDepartmentInstance(){
        if(ourInstance == null){
            ourInstance = new DepartmentSingleton();
        }
        return ourInstance;
    }
    public void setDepartmentInstance(Department department){
        userDepartment = new Department(department);
    }
    public Department getUserDepartment(){
        return userDepartment;
    }
    public void setUserDepartment(Department department){
         this.userDepartment = department;
    }
}
