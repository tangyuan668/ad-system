package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.common.adproducer.vo.param.AdProducerParam;

public interface AdProducerService {
    /**
     * 根据id查询AdProducer信息
     * @param id
     * @return
     */
    JsonResult findById(Long id);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    JsonResult login(String username,String password);

    /**
     * 分页查询用户信息
     * @param username 用户名（模糊匹配）
     * @param pageNo 页码
     * @param pageSize 每页数量
     * @return
     */
    JsonResult finPage(String username,Integer pageNo,Integer pageSize);

    /**
     * 根据id删除管理员信息
     * @param id
     * @return
     */
    JsonResult deleteById(Long id);

    /**
     * 保存管理员信息（新增和更新）
     * @param adProducerParam
     * @return
     */

    JsonResult save(AdProducerParam adProducerParam);

}
