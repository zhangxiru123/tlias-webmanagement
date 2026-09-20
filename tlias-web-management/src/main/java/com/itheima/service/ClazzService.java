package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzDTO;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;


public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);

    void save(ClazzDTO clazz);

    Clazz getById(Integer id);

    void update(ClazzDTO clazzDTO);

    List<Clazz> getAll();
}
