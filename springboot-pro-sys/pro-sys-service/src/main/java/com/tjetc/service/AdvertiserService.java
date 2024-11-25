package com.tjetc.service;

import com.tjetc.common.JsonResult;
import com.tjetc.common.advertiser.vo.param.AdvertiserParam;
import com.tjetc.common.advertiser.vo.param.AdvertiserParam;
import org.springframework.transaction.annotation.Transactional;

public interface AdvertiserService {
    /**
     * 根据id查询Advertiser信息
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
     * @param advertiserParam
     * @return
     */

    JsonResult save(AdvertiserParam advertiserParam);
    JsonResult insert(String username,String password);

    //@Transactional 用在service方法上，保证事务 只对运行时异常起作用(RuntimeException)

}
