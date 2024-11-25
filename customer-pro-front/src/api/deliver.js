//@表示src路径
//导入axios对象
import request from "@/utils/request.js"
//登录,data对象，传后端数据

//删除渠道
const deletedeliver = (id) => {
    return request({

        url: `/seapi/deliver/deliverdelete/${id}`,
        method: "get"


    })

}
//分页获取deliver数据
const deliverpage = (data) => {
    return request({

        url: "/seapi/deliver/deliverpage",
        method: "post",
        data

    })


}
//根据id查询用户
const findById = (id) => {
    return request({

        url: `/seapi/deliver/detail/${id}`,
        method: "get"


    })


}
const save = (data) => {
    return request({

        url: "/seapi/deliver/deliversave",
        headers: { "Content-Type": "application/json;charset=utf-8" },
        method: "post",
        data

    })


}



export { deletedeliver, deliverpage, findById, save }