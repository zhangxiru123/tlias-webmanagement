package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzDTO {
    private Integer id;
    private String name;
    private Integer room;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Integer masterId;
    private Integer subject;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}