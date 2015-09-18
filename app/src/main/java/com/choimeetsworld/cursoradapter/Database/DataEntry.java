package com.choimeetsworld.cursoradapter.Database;

/**
 * Created by hychoi on 9/6/2015.
 */
public class DataEntry {

    String customerName;
    String orderName;
    String address;
    String phoneNum;
    String price;
    String time;
    int uniqueID;

    public DataEntry() {}

    public DataEntry(String customerName, String orderName, int uniqueID) {

        this.customerName = customerName;
        this.orderName = orderName;
        this.uniqueID = uniqueID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String toString() {
        return customerName + ", " + orderName + ", " + uniqueID;
    }
}
