package com.study.common.test.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 人实体
 * @author fjding
 * @date 2021/9/19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;

    private Integer age;

    private Double height;

}
