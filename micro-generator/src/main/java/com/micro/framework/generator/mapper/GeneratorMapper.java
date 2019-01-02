package com.micro.framework.generator.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author tb
 * @date 2019/1/2 14:03
 */
public interface GeneratorMapper {

    List<Map<String, Object>> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);
}
