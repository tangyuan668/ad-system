package com.tjetc.common.adproducer.vo.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 返回前端页面的数据对象
 */
@Data
public class AdProducerView {
    private Long id;
    private String username;
    private String imagePath;
    //json日期格式化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
