package com.choimeetsworld.cursoradapter.Database;

/**
 * Created by hychoi on 9/6/2015.
 */
public class DataEntry {

    String customerName;
    String orderName;
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

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String toString() {
        return customerName + ", " + orderName + ", " + uniqueID;
    }
}
