/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CoffeeStore;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ntphu
 */
public class Order extends HashMap<String, Menu>implements Serializable {

    public String orderCode, orderName;
    public HashMap dsDrink;

    public Order() {
    }

    public Order(String orderCode, String orderName, HashMap<String, Menu> dsDrink) {
        this.orderCode = orderCode;
        this.orderName = orderName;
        this.dsDrink = dsDrink;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public HashMap<String, Menu> getDsDrink() {
        return dsDrink;
    }

    public void setDsDrink(HashMap<String, Menu> dsDrink) {
        this.dsDrink = dsDrink;
    }

    @Override
    public String toString() {
        return "orderCode=" + orderCode + ", orderName=" + orderName;
    }

}
