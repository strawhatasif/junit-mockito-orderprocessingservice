package com.fun.scrapbook;

/**
 * Created by rab4154 on 6/5/2017.
 */
public class A {

    private B b;

    public A(B b)
    {
        this.b = b;
    }

    public int usesVoidMethod() throws Exception
    {

        b.voidMethod();

        return 1;
    }
}
