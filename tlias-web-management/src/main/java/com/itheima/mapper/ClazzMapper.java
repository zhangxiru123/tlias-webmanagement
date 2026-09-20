package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzDTO;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    @Delete("delete from clazz where id=#{id}")
    void delete(Integer id);

    void insert(ClazzDTO clazzDTO);

    Clazz getById(Integer id);


    void updateById(ClazzDTO clazzDTO);

    @Select("select * from clazz")
    List<Clazz> getAll();
}
