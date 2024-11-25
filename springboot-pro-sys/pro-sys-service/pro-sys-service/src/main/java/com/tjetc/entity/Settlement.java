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
@TableName("advertise_settlement")
public class Settlement {
    @TableId(value = "settlement_id",type = IdType.AUTO)
    private Long id;
    private Long signId;
    private String name;
    private String content;
    private Long amout;
    private String status;
    private   String advertiserSignature;
    @JsonFormat(pattern = "YY-MM-DD HH:mm:ss")
    private LocalDateTime paymentDate;
    @JsonFormat(pattern = "YY-MM-DD HH:mm:ss")
    private LocalDateTime updateTime;
    @JsonFormat(pattern = "YY-MM-DD HH:mm:ss")
    private LocalDateTime createTime;








}
