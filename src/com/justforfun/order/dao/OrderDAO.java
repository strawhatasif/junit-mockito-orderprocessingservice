package com.justforfun.order.dao;

import com.justforfun.order.dto.Order;

import java.sql.SQLException;

/**
 * Created by rab4154 on 4/25/2017.
 */
public interface OrderDAO {

    int create(Order order) throws SQLException;

    Order read(int id) throws SQLException;

    int update(Order order) throws SQLException;

    int delete(int id) throws SQLException;

}
