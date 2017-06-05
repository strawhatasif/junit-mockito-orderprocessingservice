package com.justforfun.order.bo;

import com.justforfun.order.bo.exception.BusinessObjectException;
import com.justforfun.order.dao.OrderDAO;
import com.justforfun.order.dto.Order;

import java.sql.SQLException;

/**
 * Created by rab4154 on 4/25/2017.
 */
public class OrderBOImpl implements OrderBO {

    OrderDAO orderDAO;

    @Override
    public boolean placeOrder(Order order) throws BusinessObjectException
    {
        try
        {
            int result = orderDAO.create(order);

            if (returnFalseIfIntIsZero(result)) return false;
        }
        catch (SQLException e)
        {
           throw new BusinessObjectException(e);
        }

        return true;
    }

    @Override
    public boolean cancelOrder(int id) throws BusinessObjectException
    {
        try
        {
            Order order = orderDAO.read(id);
            order.setStatus("cancelled");
            int result = orderDAO.update(order);

            if (returnFalseIfIntIsZero(result)) return false;
        }
        catch (SQLException e)
        {
            throw new BusinessObjectException(e);
        }

        return true;
    }

    @Override
    public boolean deleteOrder(int id) throws BusinessObjectException
    {
        try
        {
           int result = orderDAO.delete(id);

            if (returnFalseIfIntIsZero(result)) return false;
        }
        catch (SQLException e)
        {
            throw new BusinessObjectException(e);
        }
        return true;
    }

    /**
     * Return false if the result is 0
     *
     * @param result
     * @return
     */
    private boolean returnFalseIfIntIsZero(int result)
    {
        if (result == 0)
        {
            return true;
        }
        return false;
    }

    public OrderDAO getOrderDAO() 
    {
        return orderDAO;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
}
