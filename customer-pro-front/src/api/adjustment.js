//@表示src路径
//导入axios对象
import request from "@/utils/request.js"
//登录,data对象，传后端数据

//删除渠道
const deleteadjustment = (id) => {
    return request({
        url: `/seapi/adjustment/adjustmentdelete/${id}`,
        method: "get"
    })

}
//分页获取adjustment数据
const adjustmentpage = (data) => {
    return request({

        url: "/seapi/adjustment/adjustmentpage",
        method: "post",
        data

    })


}
//根据id查询用户
const findById = (id) => {
    return request({

        url: `/seapi/adjustment/detail/${id}`,
        method: "get"


    })


}
const save = (data) => {
    return request({

        url: "/seapi/adjustment/adjustmentsave",
        headers: { "Content-Type": "application/json;charset=utf-8" },
        method: "post",
        data

    })


}



export { deleteadjustment, adjustmentpage, findById, save }