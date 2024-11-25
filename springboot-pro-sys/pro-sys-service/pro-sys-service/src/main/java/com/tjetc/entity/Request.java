package com.tjetc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("advertise_request")
public class Request {
    //@TableId 对表主键字段与属性映射，value是表示字段名称，type是主键类型
    @TableId(value = "request_id",type = IdType.AUTO)
    private Long id;
    //typeHandler
    @TableField(value = "customer_id")
    private Long customerId;
    //@TableField 配置表字段和实体类映射关系，配置字段名称
    @TableField(value = "title")
    private String title;
    private String description;
    private Long budget;
    //java实体类驼峰命名与数据库字段，字段下划线 自动映射（得益于配置 map-underscore-to-camel-case: true）
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
