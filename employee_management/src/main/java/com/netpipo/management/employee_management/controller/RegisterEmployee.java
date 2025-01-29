package com.netpipo.management.employee_management.controller;

public class RegisterEmployee {

    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String salary;

    public RegisterEmployee() {
    }

    public RegisterEmployee(String firstName, String lastName, String email, String salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
    }

    public RegisterEmployee(String firstName, String lastName, String email, String department, String salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
