package com.fun.scrapbook;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by rab4154 on 6/5/2017.
 */
public class ATest {

    @Mock
    B b;

    private A a;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        a = new A(b);
    }

    @Test
    public void testUsesVoidMethodShouldCallVoidMethod() throws Exception
    {
        doNothing().when(b).voidMethod();
        assertEquals(1,a.usesVoidMethod());
        verify(b).voidMethod();
    }

    @Test(expected = Exception.class)
    public void testUsesVoidMethodShouldThrowRuntimeException() throws Exception
    {
        doThrow(Exception.class).when(b).voidMethod();
        a.usesVoidMethod();
    }

    @Test(expected = Exception.class)
    public void testConsecutiveCalls() throws Exception
    {
        doNothing().doThrow(Exception.class).when(b).voidMethod();
        a.usesVoidMethod();
        verify(b).voidMethod();
        a.usesVoidMethod();
    }
}