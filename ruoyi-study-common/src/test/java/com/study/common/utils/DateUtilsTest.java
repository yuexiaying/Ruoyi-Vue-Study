package com.study.common.utils;

import org.junit.Test;

import java.util.Date;

public class DateUtilsTest {

    @Test
    public void getServerStartDateTest(){
        Date serverStartDate = DateUtils.getServerStartDate();
        String s = DateUtils.dateToStr(serverStartDate, DateUtils.YYYY_MM_DD_HH_MM_SS);
        System.out.println(s);
    }

    @Test
    public void serverTest() throws InterruptedException {
        Thread.sleep(1000*5);
        long time = DateUtils.getServerStartDate().getTime();
        long serverRunTime = DateUtils.getServerRunTime();
        String datePoor = DateUtils.getDatePoor(DateUtils.getServerStartDate(), new Date(time + serverRunTime));
        System.out.println("服务器运行了："+datePoor);
    }
}
