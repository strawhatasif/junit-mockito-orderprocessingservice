package com.justforfun.order.bo.exception;

import java.sql.SQLException;

/**
 * Created by rab4154 on 4/25/2017.
 */
public class BusinessObjectException extends Exception {

    private static final long serialVersionUID = 1L;

    public BusinessObjectException(SQLException e)
    {
        super(e);
    }
}
