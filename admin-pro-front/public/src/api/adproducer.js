//@表示src路径
//导入axios对象
import request from "@/utils/request.js"
//登录,data对象，传后端数据
const login = (data) => {
    //调用axios
    return request({
        //这里与proxy配置有关系
        ///apapi/login代理成http://localhost:8081/apapi/login 进行访问
        url: "/apapi/adproducer/login",
        method: "post",
        // 简化属性
        data

    })


}
//分页获取adproducer数据
const page = (data) => {
    return request({

        url: "/apapi/adproducer/page",
        method: "post",
        data

    })


}
//根据id查询用户
const findById = (id) => {
    return request({

        url: `/apapi/adproducer/detail/${id}`,
        method: "get"


    })


}
const deleteById = (id) => {
    return request({

        url: `/apapi/adproducer/delete/${id}`,
        method: "get"


    })




}
//保存adproducer数据（用于新增和更新） 参数数据格式json
const save = (data) => {
    return request({

        url: "/apapi/adproducer/save",
        method: "post",
        //表示请求的数据格式为json
        headers: { "Content-Type": "application/json;charset=utf-8" },
        data

    })


}




export { login, page, findById, deleteById, save }