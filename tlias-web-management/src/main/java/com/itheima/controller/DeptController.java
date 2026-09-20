package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import com.itheima.service.impl.DeptServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {


    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list(){
        log.info("查询全部的部门数据");
        List<Dept> deptList= deptService.findAll();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping
    public Result delete(Integer id){
        log.info("根据id删除部门:{}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门:{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id来查询部门:"+id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门:"+dept.getName());
        deptService.update(dept);
        return Result.success();
    }
}




















