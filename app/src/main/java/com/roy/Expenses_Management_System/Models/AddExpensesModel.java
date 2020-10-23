package com.roy.Expenses_Management_System.Models;

public class AddExpensesModel {
    private String expenses_ID;
    private String group_ID;
    private String user_ID;
    private String user_Name;
    private String expense_details;
    private String expense_price;
    private String added_date;

    AddExpensesModel()
    {

    }



    public AddExpensesModel(String group_ID, String user_Name, String user_ID, String expense_details, String expense_price, String added_date) {
        this.group_ID = group_ID;
        this.user_ID = user_ID;
        this.user_Name = user_Name;
        this.expense_details = expense_details;
        this.expense_price = expense_price;
        this.added_date = added_date;
    }
    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }
    public String getExpenses_ID() {
        return expenses_ID;
    }

    public void setExpenses_ID(String expenses_ID) {
        this.expenses_ID = expenses_ID;
    }

    public String getGroup_ID() {
        return group_ID;
    }

    public void setGroup_ID(String group_ID) {
        this.group_ID = group_ID;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getExpense_details() {
        return expense_details;
    }

    public void setExpense_details(String expense_details) {
        this.expense_details = expense_details;
    }

    public String getExpense_price() {
        return expense_price;
    }

    public void setExpense_price(String expense_price) {
        this.expense_price = expense_price;
    }

    public String getAdded_date() {
        return added_date;
    }

    public void setAdded_date(String added_date) {
        this.added_date = added_date;
    }
}
