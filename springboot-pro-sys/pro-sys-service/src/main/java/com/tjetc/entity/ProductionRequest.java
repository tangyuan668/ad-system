package com.tjetc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//配置实体列对应表名称
@TableName("production_request")
public class ProductionRequest {
    @TableId(value = "production_id",type = IdType.AUTO)
    private Long id;
    //typeHandler

    private Long proposalId;
    private Long adproducerId;
    //@TableField 配置表字段和实体类映射关系，配置字段名称
    private String name;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadTime;
    //java实体类驼峰命名与数据库字段，字段下划线 自动映射（得益于配置 map-underscore-to-camel-case: true）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;



}
