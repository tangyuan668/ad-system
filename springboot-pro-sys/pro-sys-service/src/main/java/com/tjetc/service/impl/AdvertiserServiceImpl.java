package com.tjetc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.common.JwtTokenUtil;
import com.tjetc.common.advertiser.vo.data.AdvertiserView;
import com.tjetc.common.advertiser.vo.param.AdvertiserParam;
import com.tjetc.dao.AdvertiserMapper;
import com.tjetc.entity.AdProducer;
import com.tjetc.entity.Advertiser;
import com.tjetc.service.AdvertiserService;
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
public class AdvertiserServiceImpl implements AdvertiserService {
    @Value("${token.expiration}")
    private int tokeRxpiration;
    @Autowired
    private AdvertiserMapper AdvertiserMapper;
    @Override
    public JsonResult findById(Long id) {
        Advertiser Advertiser=AdvertiserMapper.selectById(id);
       //selectById是mybatisplus提供的单表查询操作，生成sql语句都是mybatisplus内置了
        AdvertiserView AdvertiserView=new AdvertiserView();
        BeanUtils.copyProperties(Advertiser,AdvertiserView);
        return JsonResult.success(AdvertiserView);
    }

    @Override
    public JsonResult login(String username, String password) {
        //StringUtils.isAnyBlank 实参中任意一个为空，就返回true
        if(StringUtils.isAnyBlank(username,password))
        {
            return JsonResult.fail("用户名不能为空");
        }
        //根据用户名和密码查询Advertiser
Advertiser Advertiser=AdvertiserMapper.selectByUsernameAndPassword(username,password);
        if(Advertiser==null)
        {
            return JsonResult.fail("用户名或密码错误");
        }
        else{

            //用户名或密码正确，生成token
            Map<String,Object> claims=new HashMap<>();
            String token= JwtTokenUtil.generateToken(claims,"Advertiser",tokeRxpiration);
            claims.put("AdvertiserId",Advertiser.getId());
            //Advertiser-->AdvertiserView

            //token和Advertiser对象全部给前端
            return JsonResult.success(token,Advertiser);

        }
    }

    @Override
    public JsonResult finPage(String username, Integer pageNo, Integer pageSize) {
       //设置页码和每页数量
        Page<Advertiser> page = Page.of(pageNo, pageSize);
//把AdvertiserPage传入mapper查询中，AdvertiserPage就是分页结果
     Page<Advertiser> AdvertiserPage =  AdvertiserMapper.selectPageLikeUsername(page,username);
//convert方法转换Page中 records集合元素类型 Advertiser--->AdvertiserView
        //new Function<Advertiser, Object>() {}表示创建一个实现Function接口的匿名类对象
     IPage<AdvertiserView> AdvertiserViewPage=AdvertiserPage.convert(new Function<Advertiser, AdvertiserView>() {
         @Override
         public AdvertiserView apply(Advertiser Advertiser)
         {AdvertiserView AdvertiserView = new AdvertiserView();
             //Spring的BeanUtils工具类进行属性copy,两个对象的属性名要保持一致
             BeanUtils.copyProperties(Advertiser,AdvertiserView);

//             AdvertiserView.setId(Advertiser.getId());
//             AdvertiserView.setUsername(Advertiser.getUsername());
//AdvertiserView.setCreateTime(Advertiser.getCreateTime());
//AdvertiserView.setUpdateTime(Advertiser.getUpdateTime());
return AdvertiserView;
         }

     });
        return JsonResult.success(AdvertiserViewPage);
    }

    @Override
    public JsonResult deleteById(Long id) {

        int rows= AdvertiserMapper.deleteById(id);
     if(rows>0){return JsonResult.success("删除成功");}
       else{ return JsonResult.fail("删除失败");}
    }

    //@Transactional 用在service方法上，保证事务 只对运行时异常起作用(RuntimeException)
    @Transactional
    @Override
    public JsonResult save(AdvertiserParam AdvertiserParam) {
        //通过判断id值有无判断新增还是更新，id值无新增，id值有就是更新
        Advertiser Advertiser=null;
        LocalDateTime now= LocalDateTime.now();
        if(AdvertiserParam.getId()==null||AdvertiserParam.getId()<=0)
        {//新增
            //判断用户名或则和密码不能为空
            if(StringUtils.isAnyBlank(AdvertiserParam.getUsername(),AdvertiserParam.getPassword()))
            {
                return JsonResult.fail("新增用户，用户名和密码不能为空");
            }
            //判断用户名不能重复
            Advertiser existAdvertiser = AdvertiserMapper.selectByUsername(AdvertiserParam.getUsername());
            if(existAdvertiser!=null)
            {return  JsonResult.fail("用户名已存在");}
            //新增
             Advertiser=new Advertiser();
            BeanUtils.copyProperties(AdvertiserParam,Advertiser);

            Advertiser.setUpdateTime(now);
            Advertiser.setCreateTime(now);
            AdvertiserMapper.insert(Advertiser);
       return JsonResult.success("新增成功",null);
        }
        else{
            //更新
//根据id查询Advertiser信息
            Advertiser = AdvertiserMapper.selectById(AdvertiserParam.getId());
          if(Advertiser==null)
          {
              return JsonResult.fail("用户不存在，更新失败");
          }
          //要更新的字段
Advertiser.setImagePath(AdvertiserParam.getImagePath());
          Advertiser.setPassword(AdvertiserParam.getPassword());
          Advertiser.setUsername(AdvertiserParam.getUsername());
          Advertiser.setUpdateTime(now);

AdvertiserMapper.updateById(Advertiser);
int rows=AdvertiserMapper.updateById(Advertiser);
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
       Advertiser advertiser = new Advertiser();
        advertiser.setUsername(username);
        advertiser.setPassword(password);

        advertiser.setCreateTime(now);
        advertiser.setUpdateTime(now);
        int i= AdvertiserMapper.insert(advertiser);

        if(i>0){
            return JsonResult.success(advertiser);

        }
        else {return JsonResult.fail("添加失败");}
    }



}
