package com.zxy.model;

import lombok.Data;

import java.sql.Date;

/**
 * @Description:
 * @Author: Yeliang
 * @Date: Create in 15:36 2018/9/7
 */
@Data
public class UserData {
    private int id;
    private Date time;
    private String username;
    private String name;
    private String size;
    private int num;
    private double weight;
    private double priceWithTax;
    private double price;
    private String salesman;
    private String invoice;

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", time=" + time +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", num=" + num +
                ", weight=" + weight +
                ", priceWithTax=" + priceWithTax +
                ", price=" + price +
                ", salesman='" + salesman + '\'' +
                ", invoice='" + invoice + '\'' +
                '}';
    }
}
