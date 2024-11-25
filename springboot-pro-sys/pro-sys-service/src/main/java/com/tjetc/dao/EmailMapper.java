package com.tjetc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjetc.entity.Email;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmailMapper extends BaseMapper<Email> {
List<Email> getAllEmail(@Param("cuid")Integer cuid);

   Integer getNum(@Param("cuid")Integer cuid);
}
