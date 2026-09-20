package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzDTO;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        java.util.List<Clazz> clazzList =clazzMapper.list(clazzQueryParam);
        Page<Clazz> p=(Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.delete(id);
    }

    @Override
    public void save(ClazzDTO clazzDTO) {
        clazzDTO.setUpdateTime(LocalDateTime.now());
        clazzDTO.setCreateTime(LocalDateTime.now());
        clazzMapper.insert(clazzDTO);
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(ClazzDTO clazzDTO) {
        clazzDTO.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazzDTO);
    }

    @Override
    public List<Clazz> getAll() {
        return clazzMapper.getAll();
    }
}
