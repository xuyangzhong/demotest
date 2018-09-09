package com.zxy.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.sql.Date;

/**
 * @Description:
 * @Author: Yeliang
 * @Date: Create in 15:52 2018/9/7
 */
@MapperScan
public interface MessageDao {
    //    int saveOrUpdateUserData(Date time, String username, String name, String size, int num, double weight, double priceWithTax, double price, String salesman, String invoice);
    int saveOrUpdateUserData(@Param("time") Date time, @Param("username") String username, @Param("name") String name, @Param("size") String size, @Param("num") int num, @Param("weight") double weight, @Param("priceWithTax") double priceWithTax, @Param("price") double price, @Param("salesman") String salesman, @Param("invoice") String invoice);

}

