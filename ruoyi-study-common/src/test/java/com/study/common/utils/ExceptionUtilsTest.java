package com.study.common.utils;

import org.junit.Test;

public class ExceptionUtilsTest {

    @Test
    public void getExceptionMsgTest(){
        Exception e = new RuntimeException();
        String exceptionMsg = ExceptionUtils.getExceptionMsg(e);
        System.out.println(exceptionMsg);
    }

    @Test
    public void getRootErrorMessageTest(){
        RuntimeException runtimeException = new RuntimeException("run" , new IllegalArgumentException("ill"));
        String rootErrorMessage = ExceptionUtils.getRootErrorMessage(runtimeException);
        System.out.println(rootErrorMessage);
        System.out.println(ExceptionUtils.getExceptionMsg(runtimeException));
    }
}
