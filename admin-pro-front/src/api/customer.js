//@表示src路径
//导入axios对象
import request from "@/utils/request.js"
//登录,data对象，传后端数据
const login = (data) => {
    //调用axios
    return request({
        //这里与proxy配置有关系
        ///cuapi/login代理成http://localhost:8081/cuapi/login 进行访问
        url: "/cuapi/customer/login",
        method: "post",
        // 简化属性
        data

    })


}
//分页获取customer数据
const page = (data) => {
    return request({

        url: "/cuapi/customer/page",
        method: "post",
        data

    })


}
//根据id查询用户
const findById = (id) => {
    return request({

        url: `/cuapi/customer/detail/${id}`,
        method: "get"


    })


}
const deleteById = (id) => {
    return request({

        url: `/cuapi/customer/delete/${id}`,
        method: "get"


    })




}
//保存customer数据（用于新增和更新） 参数数据格式json
const save = (data) => {
    return request({

        url: "/cuapi/customer/save",
        method: "post",
        //表示请求的数据格式为json
        headers: { "Content-Type": "application/json;charset=utf-8" },
        data

    })


}
//保存customer数据（用于新增和更新） 
const register = (data) => {
    return request({

        url: "/cuapi/customer/add",
        method: "post",
        data

    })


}




export { login, page, findById, deleteById, save, register }