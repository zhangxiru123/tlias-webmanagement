package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询:{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工:{}", emp);
        empService.save(emp);
        return Result.success();
    }

    @Log
    @DeleteMapping
    public Result deleteById(Integer[] ids) {
        log.info("根据Id删除员工:{}", Arrays.toString(ids));
        empService.deleteById(ids);
        return Result.success();
    }

    @GetMapping ("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询员工信息:{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工:{}",emp);
        empService.update(emp);
        return Result.success();
    }

}















