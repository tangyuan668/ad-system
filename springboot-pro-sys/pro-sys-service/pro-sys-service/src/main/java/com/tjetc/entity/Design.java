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
@TableName("advertise_design")
public class Design {
    @TableId(value = "design_id",type = IdType.AUTO)
    private Long id;
    private Long requestId;
    private String imagePath;
       private   String status;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @JsonFormat(pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
private String name;
private String advice;




}
