package com.tjetc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.common.JwtTokenUtil;
import com.tjetc.common.customer.vo.data.CustomerView;
import com.tjetc.common.customer.vo.param.CustomerParam;
import com.tjetc.dao.CustomerMapper;
import com.tjetc.entity.Customer;
import com.tjetc.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {
    @Value("${token.expiration}")
    private int tokeRxpiration;
    @Autowired
    private CustomerMapper CustomerMapper;
    LocalDateTime now=LocalDateTime.now();
    @Override
    public JsonResult findById(Long id) {
        Customer Customer=CustomerMapper.selectById(id);
       //selectById是mybatisplus提供的单表查询操作，生成sql语句都是mybatisplus内置了
        CustomerView CustomerView=new CustomerView();
        BeanUtils.copyProperties(Customer,CustomerView);
        return JsonResult.success(CustomerView);
    }

    @Override
    public JsonResult login(String username, String password) {
        //StringUtils.isAnyBlank 实参中任意一个为空，就返回true
        if(StringUtils.isAnyBlank(username,password))
        {
            return JsonResult.fail("用户名不能为空");
        }
        //根据用户名和密码查询Customer
Customer Customer=CustomerMapper.selectByUsernameAndPassword(username,password);
        if(Customer==null)
        {
            return JsonResult.fail("用户名或密码错误");
        }
        else{

            //用户名或密码正确，生成token
            Map<String,Object> claims=new HashMap<>();
            String token= JwtTokenUtil.generateToken(claims,"Customer",tokeRxpiration);
            claims.put("CustomerId",Customer.getId());
            //Customer-->CustomerView

            //token和Customer对象全部给前端
            return JsonResult.success(token,Customer);

        }
    }

    @Override
    public JsonResult finPage(String username, Integer pageNo, Integer pageSize) {
       //设置页码和每页数量
        Page<Customer> page = Page.of(pageNo, pageSize);
//把CustomerPage传入mapper查询中，CustomerPage就是分页结果
     Page<Customer> CustomerPage =  CustomerMapper.selectPageLikeUsername(page,username);
//convert方法转换Page中 records集合元素类型 Customer--->CustomerView
        //new Function<Customer, Object>() {}表示创建一个实现Function接口的匿名类对象
     IPage<CustomerView> CustomerViewPage=CustomerPage.convert(new Function<Customer, CustomerView>() {
         @Override
         public CustomerView apply(Customer Customer)
         {CustomerView CustomerView = new CustomerView();
             //Spring的BeanUtils工具类进行属性copy,两个对象的属性名要保持一致
             BeanUtils.copyProperties(Customer,CustomerView);

//             CustomerView.setId(Customer.getId());
//             CustomerView.setUsername(Customer.getUsername());
//CustomerView.setCreateTime(Customer.getCreateTime());
//CustomerView.setUpdateTime(Customer.getUpdateTime());
return CustomerView;
         }

     });
        return JsonResult.success(CustomerViewPage);
    }

    @Override
    public JsonResult deleteById(Long id) {

        int rows= CustomerMapper.deleteById(id);
     if(rows>0){return JsonResult.success("删除成功");}
       else{ return JsonResult.fail("删除失败");}
    }

   // @Transactional 用在service方法上，保证事务 只对运行时异常起作用(RuntimeException)
    @Transactional
    @Override
    public JsonResult save(CustomerParam CustomerParam) {
        //通过判断id值有无判断新增还是更新，id值无新增，id值有就是更新
        Customer Customer=null;
        LocalDateTime now= LocalDateTime.now();
        if(CustomerParam.getId()==null||CustomerParam.getId()<=0)
        {//新增
            //判断用户名或则和密码不能为空
            if(StringUtils.isAnyBlank(CustomerParam.getUsername(),CustomerParam.getPassword()))
            {
                return JsonResult.fail("新增用户，用户名和密码不能为空");
            }
            //判断用户名不能重复
            Customer existCustomer = CustomerMapper.selectByUsername(CustomerParam.getUsername());
            if(existCustomer!=null)
            {return  JsonResult.fail("用户名已存在");}
            //新增
             Customer=new Customer();
            BeanUtils.copyProperties(CustomerParam,Customer);

            Customer.setUpdateTime(now);
            Customer.setCreateTime(now);
            CustomerMapper.insert(Customer);
       return JsonResult.success("新增成功",null);
        }
        else{
            //更新
//根据id查询Customer信息
            Customer = CustomerMapper.selectById(CustomerParam.getId());
          if(Customer==null)
          {
              return JsonResult.fail("用户不存在，更新失败");
          }
          //要更新的字段
Customer.setImagePath(CustomerParam.getImagePath());
          Customer.setPassword(CustomerParam.getPassword());
          Customer.setUpdateTime(now);
CustomerMapper.updateById(Customer);
int rows=CustomerMapper.updateById(Customer);
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
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);

        customer.setCreateTime(now);
        customer.setUpdateTime(now);
        int i= CustomerMapper.insert(customer);

        if(i>0){
            return JsonResult.success(customer);

        }
        else {return JsonResult.fail("添加失败");}
    }


}
