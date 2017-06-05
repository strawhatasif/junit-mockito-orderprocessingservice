package com.justforfun.order.bo;

import com.justforfun.order.bo.exception.BusinessObjectException;
import com.justforfun.order.dao.OrderDAO;
import com.justforfun.order.dto.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


/**
 * Created by rab4154 on 5/1/2017.
 */
public class OrderBOImplTest {

    @Mock
    OrderDAO orderDAO;

    private OrderBOImpl orderBO;
    private int ORDER_ID = 123;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        orderBO = new OrderBOImpl();
        orderBO.setOrderDAO(orderDAO);
    }

    @Test
    public void placeOrder_Should_Create_An_Order() throws SQLException, BusinessObjectException
    {
        Order order = new Order();
        //when the create method is called on the mocked DAO
        //then return a value of 1
        when(orderDAO.create(any(Order.class))).thenReturn(new Integer(1));

        boolean result = orderBO.placeOrder(order);

        assertTrue(result);
        //verifying that the mocked method is being called

        //NOTE: if you want to verify that a method is being called
        //you can pass in built in methods like times (number of times to call) OR
        //atLeast - where you state that the mocked method should be called at least x times

        //syntax verify(class,times(1)).method
        verify(orderDAO,times(1)).create(order);
    }


    @Test(expected=BusinessObjectException.class)
    public void placeOrder_Should__Throw_BusinessObjectException() throws SQLException, BusinessObjectException
    {
        Order order = new Order();
        //when the create method is called on the mocked DAO
        //then return a value of 0
        when(orderDAO.create(order)).thenThrow(SQLException.class);

        orderBO.placeOrder(order);

    }

    @Test
    public void cancelOrder_Should_Cancel_The_Order() throws SQLException, BusinessObjectException {
        Order order = new Order();
        when(orderDAO.read(ORDER_ID)).thenReturn(order);
        when(orderDAO.update(order)).thenReturn(1);

        boolean result = orderBO.cancelOrder(ORDER_ID);

        assertTrue(result);
        verify(orderDAO).read(ORDER_ID);
        verify(orderDAO).update(order);
    }

    @Test
    public void cancelOrder_Should_Not_Cancel_The_Order() throws SQLException, BusinessObjectException {
        Order order = new Order();
        when(orderDAO.read(ORDER_ID)).thenReturn(order);
        when(orderDAO.update(order)).thenReturn(0);

        boolean result = orderBO.cancelOrder(ORDER_ID);

        assertFalse(result);
        verify(orderDAO).read(123);
        verify(orderDAO).update(order);
    }

    @Test(expected=BusinessObjectException.class)
    public void cancelOrder_Should__Throw_BusinessObjectExceptionOnRead() throws SQLException, BusinessObjectException
    {
        when(orderDAO.read(anyInt())).thenThrow(SQLException.class);

        orderBO.cancelOrder(anyInt());
    }

    @Test(expected=BusinessObjectException.class)
    public void cancelOrder_Should__Throw_BusinessObjectExceptionOnUpdate() throws SQLException, BusinessObjectException
    {
        Order order = new Order();
        when(orderDAO.read(ORDER_ID)).thenReturn(order);
        when(orderDAO.update(order)).thenThrow(SQLException.class);

        orderBO.cancelOrder(ORDER_ID);
    }
}