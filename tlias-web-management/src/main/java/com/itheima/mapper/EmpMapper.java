package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.JobOption;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface EmpMapper {


    public List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username,  name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "            values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime});")
    void insert(Emp emp);

    @Delete("delete from emp where id=#{id}")
    void deleteById(Integer id);


    Emp getById(Integer id);

    void updateById(Emp emp);

    List<Map<String, Object>> countEmpJobData();

    List<Map<String, Object>> countEmpGenderData();

    @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
