package com.tjetc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.common.JwtTokenUtil;
import com.tjetc.common.admin.vo.data.AdminView;
import com.tjetc.common.admin.vo.param.AdminParam;
import com.tjetc.dao.AdminMapper;
import com.tjetc.entity.Admin;
import com.tjetc.entity.Channel;
import com.tjetc.entity.Customer;
import com.tjetc.service.AdminService;
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
public class AdminServiceImpl implements AdminService {
    @Value("${token.expiration}")
    private int tokeRxpiration;
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public JsonResult findById(Long id) {
        Admin admin=adminMapper.selectById(id);
       //selectById是mybatisplus提供的单表查询操作，生成sql语句都是mybatisplus内置了
        AdminView adminView=new AdminView();
        BeanUtils.copyProperties(admin,adminView);
        return JsonResult.success(adminView);
    }

    @Override
    public JsonResult login(String username, String password) {
        //StringUtils.isAnyBlank 实参中任意一个为空，就返回true
        if(StringUtils.isAnyBlank(username,password))
        {
            return JsonResult.fail("用户名不能为空");
        }
        //根据用户名和密码查询Admin
Admin admin=adminMapper.selectByUsernameAndPassword(username,password);
        if(admin==null)
        {
            return JsonResult.fail("用户名或密码错误");
        }
        else{

            //用户名或密码正确，生成token
            Map<String,Object> claims=new HashMap<>();
            String token= JwtTokenUtil.generateToken(claims,"admin",tokeRxpiration);
            claims.put("adminId",admin.getId());
            //Admin-->AdProducerView

            //token和admin对象全部给前端
            return JsonResult.success(token,admin);

        }
    }

    @Override
    public JsonResult finPage(String username, Integer pageNo, Integer pageSize) {
       //设置页码和每页数量
        Page<Admin> page = Page.of(pageNo, pageSize);
//把adminPage传入mapper查询中，adminPage就是分页结果
     Page<Admin> adminPage =  adminMapper.selectPageLikeUsername(page,username);
//convert方法转换Page中 records集合元素类型 Admin--->AdProducerView
        //new Function<Admin, Object>() {}表示创建一个实现Function接口的匿名类对象
     IPage<AdminView> adminViewPage=adminPage.convert(new Function<Admin, AdminView>() {
         @Override
         public AdminView apply(Admin admin)
         {AdminView adminView = new AdminView();
             //Spring的BeanUtils工具类进行属性copy,两个对象的属性名要保持一致
             BeanUtils.copyProperties(admin,adminView);

//             adminView.setId(admin.getId());
//             adminView.setUsername(admin.getUsername());
//adminView.setCreateTime(admin.getCreateTime());
//adminView.setUpdateTime(admin.getUpdateTime());
return adminView;
         }

     });
        return JsonResult.success(adminViewPage);
    }

    @Override
    public JsonResult deleteById(Long id) {

        int rows= adminMapper.deleteById(id);
     if(rows>0){return JsonResult.success("删除成功");}
       else{ return JsonResult.fail("删除失败");}
    }

    //@Transactional 用在service方法上，保证事务 只对运行时异常起作用(RuntimeException)
    @Transactional
    @Override
    public JsonResult save(AdminParam adminParam) {
        //通过判断id值有无判断新增还是更新，id值无新增，id值有就是更新
        Admin admin=null;
        LocalDateTime now= LocalDateTime.now();
        if(adminParam.getId()==null||adminParam.getId()<=0)
        {//新增
            //判断用户名或则和密码不能为空
            if(StringUtils.isAnyBlank(adminParam.getUsername(),adminParam.getPassword()))
            {
                return JsonResult.fail("新增用户，用户名和密码不能为空");
            }
            //判断用户名不能重复
            Admin existAdmin = adminMapper.selectByUsername(adminParam.getUsername());
            if(existAdmin!=null)
            {return  JsonResult.fail("用户名已存在");}
            //新增
             admin=new Admin();
            BeanUtils.copyProperties(adminParam,admin);

            admin.setUpdateTime(now);
            admin.setCreateTime(now);
            adminMapper.insert(admin);
       return JsonResult.success("新增成功",null);
        }
        else{
            //更新
//根据id查询Admin信息
            admin = adminMapper.selectById(adminParam.getId());
          if(admin==null)
          {
              return JsonResult.fail("用户不存在，更新失败");
          }
            Admin existAdmin = adminMapper.selectByUsername(adminParam.getUsername());
            if(existAdmin!=null)
            {return  JsonResult.fail("用户名已存在");}
          //要更新的字段
admin.setImagePath(adminParam.getImagePath());
          admin.setUsername(adminParam.getUsername());
          admin.setPassword(adminParam.getPassword());
          admin.setUpdateTime(now);
adminMapper.updateById(admin);
int rows=adminMapper.updateById(admin);
//要判断更新影响行数
if(rows>0)
{
    return JsonResult.success("更新成功",null);
}
else{return JsonResult.fail("更新失败");}

        }




    }

    @Override
    public JsonResult findChannelPage(String username, Integer pageNo, Integer pageSize) {
        //设置页码和每页数量
        Page<Channel> page = Page.of(pageNo, pageSize);
//把adminPage传入mapper查询中，adminPage就是分页结果
        Page<Channel> channelPage =  adminMapper.selectChannelPageLikeName(page,username);
//convert方法转换Page中 records集合元素类型 Admin--->AdProducerView
        //new Function<Admin, Object>() {}表示创建一个实现Function接口的匿名类对象

        return JsonResult.success(channelPage);
    }

    @Override
    public JsonResult channelDeleteById(int id) {
        int rows= adminMapper.deleteChannel(id);
        if(rows>0){return JsonResult.success("删除成功");}
        else{ return JsonResult.fail("删除失败");}
    }

    @Override
    public JsonResult insert(String username,String password) {
        LocalDateTime now=LocalDateTime.now();
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);

        admin.setCreateTime(now);
        admin.setUpdateTime(now);
   Admin existAdmin=adminMapper.selectByUsername(username);
   if(existAdmin!=null)
   {
       return JsonResult.fail("用户名已存在");

   }
        int i= adminMapper.insert(admin);

        if(i>0){
            return JsonResult.success("添加成功",admin);

        }
        else {return JsonResult.fail("添加失败");}
    }



}
