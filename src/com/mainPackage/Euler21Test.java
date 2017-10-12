package com.mainPackage;

import org.junit.Assert;

public class Euler21Test {
    @org.junit.Test
    public void d() throws Exception {
        int num = Euler21.d(220);
        Assert.assertEquals(284, num);
    }
}