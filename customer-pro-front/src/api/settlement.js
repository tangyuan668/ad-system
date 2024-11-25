//@表示src路径
//导入axios对象
import request from "@/utils/request.js"
//登录,data对象，传后端数据

//删除渠道
const deletesettlement = (id) => {
    return request({
        url: `/seapi/settlement/settlementdelete/${id}`,
        method: "get"
    })

}
//分页获取settlement数据
const settlementpage = (data) => {
    return request({

        url: "/seapi/settlement/settlementpage",
        method: "post",
        data

    })


}
//根据id查询用户
const findById = (id) => {
    return request({

        url: `/seapi/settlement/detail/${id}`,
        method: "get"


    })


}
const save = (data) => {
    return request({

        url: "/seapi/settlement/settlementsave",
        headers: { "Content-Type": "application/json;charset=utf-8" },
        method: "post",
        data

    })


}

const update = () => {
    return request({
        url: "/seapi/settlement/updateAmout",
        method: "post",


    })


}

const updatePaymentTime = (id) => {

    return request({
        url: `/seapi/settlement/updatePaymentTime/${id}`,
        method: "get",


    })

}



export { deletesettlement, settlementpage, findById, save, update, updatePaymentTime }