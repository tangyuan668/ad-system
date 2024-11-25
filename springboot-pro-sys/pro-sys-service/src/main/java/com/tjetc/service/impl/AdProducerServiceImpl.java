package com.tjetc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.common.JwtTokenUtil;
import com.tjetc.common.adproducer.vo.data.AdProducerView;
import com.tjetc.common.adproducer.vo.param.AdProducerParam;
import com.tjetc.dao.AdProducerMapper;
import com.tjetc.entity.AdProducer;
import com.tjetc.entity.Admin;
import com.tjetc.service.AdProducerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class AdProducerServiceImpl implements AdProducerService {
    @Value("${token.expiration}")
    private int tokeRxpiration;
    @Autowired
    private AdProducerMapper AdProducerMapper;
    @Override
    public JsonResult findById(Long id) {
        AdProducer AdProducer=AdProducerMapper.selectById(id);
       //selectById是mybatisplus提供的单表查询操作，生成sql语句都是mybatisplus内置了
        AdProducerView AdProducerView=new AdProducerView();
        BeanUtils.copyProperties(AdProducer,AdProducerView);
        return JsonResult.success(AdProducerView);
    }

    @Override
    public JsonResult login(String username, String password) {
        //StringUtils.isAnyBlank 实参中任意一个为空，就返回true
        if(StringUtils.isAnyBlank(username,password))
        {
            return JsonResult.fail("用户名不能为空");
        }
        //根据用户名和密码查询AdProducer
AdProducer AdProducer=AdProducerMapper.selectByUsernameAndPassword(username,password);
        if(AdProducer==null)
        {
            return JsonResult.fail("用户名或密码错误");
        }
        else{

            //用户名或密码正确，生成token
            Map<String,Object> claims=new HashMap<>();
            String token= JwtTokenUtil.generateToken(claims,"AdProducer",tokeRxpiration);
            claims.put("AdProducerId",AdProducer.getId());
            //AdProducer-->AdProducerView

            //token和AdProducer对象全部给前端
            return JsonResult.success(token,AdProducer);

        }
    }

    @Override
    public JsonResult finPage(String username, Integer pageNo, Integer pageSize) {
       //设置页码和每页数量
        Page<AdProducer> page = Page.of(pageNo, pageSize);
//把AdProducerPage传入mapper查询中，AdProducerPage就是分页结果
     Page<AdProducer> AdProducerPage =  AdProducerMapper.selectPageLikeUsername(page,username);
//convert方法转换Page中 records集合元素类型 AdProducer--->AdProducerView
        //new Function<AdProducer, Object>() {}表示创建一个实现Function接口的匿名类对象
     IPage<AdProducerView> AdProducerViewPage=AdProducerPage.convert(new Function<AdProducer, AdProducerView>() {
         @Override
         public AdProducerView apply(AdProducer AdProducer)
         {AdProducerView AdProducerView = new AdProducerView();
             //Spring的BeanUtils工具类进行属性copy,两个对象的属性名要保持一致
             BeanUtils.copyProperties(AdProducer,AdProducerView);

//             AdProducerView.setId(AdProducer.getId());
//             AdProducerView.setUsername(AdProducer.getUsername());
//AdProducerView.setCreateTime(AdProducer.getCreateTime());
//AdProducerView.setUpdateTime(AdProducer.getUpdateTime());
return AdProducerView;
         }

     });
        return JsonResult.success(AdProducerViewPage);
    }

    @Override
    public JsonResult deleteById(Long id) {

        int rows= AdProducerMapper.deleteById(id);
     if(rows>0){return JsonResult.success("删除成功");}
       else{ return JsonResult.fail("删除失败");}
    }

    //@Transactional 用在service方法上，保证事务 只对运行时异常起作用(RuntimeException)
    @Transactional
    @Override
    public JsonResult save(AdProducerParam AdProducerParam) {
        //通过判断id值有无判断新增还是更新，id值无新增，id值有就是更新
        AdProducer AdProducer=null;
        LocalDateTime now= LocalDateTime.now();
        if(AdProducerParam.getId()==null||AdProducerParam.getId()<=0)
        {//新增
            //判断用户名或则和密码不能为空
            if(StringUtils.isAnyBlank(AdProducerParam.getUsername(),AdProducerParam.getPassword()))
            {
                return JsonResult.fail("新增用户，用户名和密码不能为空");
            }
            //判断用户名不能重复
            AdProducer existAdProducer = AdProducerMapper.selectByUsername(AdProducerParam.getUsername());
            if(existAdProducer!=null)
            {return  JsonResult.fail("用户名已存在");}
            //新增
             AdProducer=new AdProducer();
            BeanUtils.copyProperties(AdProducerParam,AdProducer);

            AdProducer.setUpdateTime(now);
            AdProducer.setCreateTime(now);
            AdProducerMapper.insert(AdProducer);
       return JsonResult.success("新增成功",null);
        }
        else{
            //更新
//根据id查询AdProducer信息
            AdProducer = AdProducerMapper.selectById(AdProducerParam.getId());
          if(AdProducer==null)
          {
              return JsonResult.fail("用户不存在，更新失败");
          }
          //要更新的字段
AdProducer.setImagePath(AdProducerParam.getImagePath());
          AdProducer.setUpdateTime(now);
AdProducerMapper.updateById(AdProducer);
int rows=AdProducerMapper.updateById(AdProducer);
//要判断更新影响行数
if(rows>0)
{
    return JsonResult.success("更新成功",null);
}
else{return JsonResult.fail("更新失败");}

        }




    }

    @Override
    public JsonResult insert(String username,String password) {
        LocalDateTime now=LocalDateTime.now();
        AdProducer adProducer = new AdProducer();
        adProducer.setUsername(username);
        adProducer.setPassword(password);

        adProducer.setCreateTime(now);
        adProducer.setUpdateTime(now);
        int i= AdProducerMapper.insert(adProducer);

        if(i>0){
            return JsonResult.success(adProducer);

        }
        else {return JsonResult.fail("添加失败");}
    }




}
