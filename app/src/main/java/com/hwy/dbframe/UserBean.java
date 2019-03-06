package com.hwy.dbframe;

import com.hwy.db.annotation.SqlField;
import com.hwy.db.annotation.SqlTable;

/**
 * 作者: hewenyu
 * 日期: 2019/3/6 14:51
 * 说明:
 */
@SqlTable
public class UserBean {

    @SqlField
    private String userName;

    @SqlField
    private int age;

    @SqlField
    private double salary;

    public UserBean() {
    }

    public UserBean(String userName, int age, double salary) {
        this.userName = userName;
        this.age = age;
        this.salary = salary;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
