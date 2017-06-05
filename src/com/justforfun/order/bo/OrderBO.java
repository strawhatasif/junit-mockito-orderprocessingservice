package com.justforfun.order.bo;

import com.justforfun.order.bo.exception.BusinessObjectException;
import com.justforfun.order.dto.Order;

/**
 * Created by rab4154 on 4/25/2017.
 */
public interface OrderBO {

    boolean placeOrder(Order order) throws BusinessObjectException;

    boolean cancelOrder(int id) throws BusinessObjectException;

    boolean deleteOrder (int id) throws BusinessObjectException;

}
