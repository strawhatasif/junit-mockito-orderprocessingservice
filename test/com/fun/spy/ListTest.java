package com.fun.spy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by rab4154 on 5/1/2017.
 */
public class ListTest {

    //Spy calls methods on the real class
    //to achieve partial mocking - use Mockito.doReturn.when
    //can't call Mockito.when directly on a class under spy because it will call
    //the actual class and not a mocked class
    @Spy
    List<String> myList = new ArrayList<>();

    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test()
    {
        myList.add("Katherine");
        myList.add("Samantha");

        Mockito.doReturn(3).when(myList).size();
        assertSame(3, myList.size());
    }
}