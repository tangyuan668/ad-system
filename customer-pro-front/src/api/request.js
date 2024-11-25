//@表示src路径
//导入axios对象
import request from "@/utils/request.js"
//登录,data对象，传后端数据

//删除渠道
const deleteRequest = (id) => {
    return request({

        url: `/seapi/request/requestdelete/${id}`,
        method: "get"


    })

}
//分页获取request数据
const requestpage = (data) => {
    return request({

        url: "/seapi/request/requestpage",
        method: "post",

        data

    })


}
//根据id查询用户
const findById = (id) => {
    return request({

        url: `/seapi/request/detail/${id}`,
        method: "get"


    })


}
const save = (data) => {
    return request({

        url: "/seapi/request/requestsave",
        headers: { "Content-Type": "application/json;charset=utf-8" },
        method: "post",
        data

    })


}



export { deleteRequest, requestpage, findById, save }