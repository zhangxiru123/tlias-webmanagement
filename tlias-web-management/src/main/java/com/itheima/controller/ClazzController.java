package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result list(ClazzQueryParam clazzQueryParam){
      log.info("分页查询:{}",clazzQueryParam);
        PageResult<Clazz> pageResult=clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    @Log
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("根据id删除班级{}",id);
        clazzService.deleteById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result save(@RequestBody ClazzDTO clazzDTO){
        log.info("新增班级:{}",clazzDTO);
        clazzService.save(clazzDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询班级:{}",id);
        Clazz clazz=clazzService.getById(id);
        return Result.success(clazz);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody ClazzDTO clazzDTO){
        log.info("修改班级信息:{}",clazzDTO);
        clazzService.update(clazzDTO);
        return Result.success();
    }

    @GetMapping("/list")
    public Result getAll(){
        log.info("查询所有班级信息");
        List<Clazz> clazzList=clazzService.getAll();
        return Result.success(clazzList);
    }
}
