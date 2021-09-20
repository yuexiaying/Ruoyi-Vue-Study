package com.study.system.mapper;

import com.study.system.domain.SysConfig;

import java.util.List;

/**
 * 参数配置 数据层
 *
 * @author fjding
 * @date 2021/9/20
 */
public interface SysConfigMapper {

    /**
     * 查询参数配置信息
     *
     * @param config 参数配置信息
     * @return 参数配置信息
     */
     SysConfig selectConfig(SysConfig config);

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
     List<SysConfig> selectConfigList(SysConfig config);

}
