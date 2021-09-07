package com.study.common.utils.bean;

import com.study.common.domain.City;
import com.study.common.domain.School;
import com.study.common.domain.Student;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;

public class BeanUtilsTest {
    @Test
    public void copyBeanPropTest(){
        School s1 = new School();
        s1.setName("beiyi");
        s1.setId("123");
        Student student = new Student();
        student.setName("make");
        student.setAge(12);
        s1.setStudent(student);
        School s2 = new School();
        BeanUtils.copyBeanProp(s2,s1);
        System.out.println(s2);
        City city = new City();
        BeanUtils.copyBeanProp(city,s1);
        System.out.println(city);
    }

    @Test
    public void getGetterMethodsTest(){
        City city = new City();
        List<Method> getterMethods = BeanUtils.getGetterMethods(city);
        getterMethods.forEach(method -> System.out.println(method.getName()));
    }

    @Test
    public void getSetterMethodsTest(){
        School school = new School();
        List<Method> setterMethods = BeanUtils.getSetterMethods(school);
        setterMethods.forEach(method -> System.out.println(method.getName()));
    }

    @Test
    public void isMethodPropEqualsTest(){
        System.out.println(BeanUtils.isMethodPropEquals("getAge()" , "setAge()"));
        System.out.println(BeanUtils.isMethodPropEquals("getName()" , "getAge()"));
    }

}
