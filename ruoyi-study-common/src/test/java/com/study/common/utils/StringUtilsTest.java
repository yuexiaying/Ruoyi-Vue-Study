package com.study.common.utils;

import org.junit.Test;

import java.util.Set;

public class StringUtilsTest {

    @Test
    public void strToSetTest(){
        String str = "a , d ,a ,ef , , ";
        Set<String> strings = StringUtils.strToSet(str, ",");
        System.out.println(strings);
    }

    @Test
    public void toUnderScoreCaseTest(){
        // abc_ddef
        String str = "aDeFABCdEe";
        System.out.println(StringUtils.toUnderScoreCase(str));
    }

    @Test
    public void test(){
      String s1 = new String("abc");
      s1.intern();
      String s2 = "abc";
        System.out.println(s1 == s2);
      String s3 = new String(new char[]{'a','b','c'});
    }

    @Test
    public void test2(){
        String s3 = new String(new char[]{'a','b','c'});
        s3.intern();
        String s2 = "abc";
        System.out.println(s2 == s3);
    }
}
