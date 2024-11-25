//@表示src路径
//导入axios对象
import request from "@/utils/request.js"
//登录,data对象，传后端数据

//删除渠道
const deletesign = (id) => {
    return request({
        url: `/seapi/sign/signdelete/${id}`,
        method: "get"
    })

}
//分页获取sign数据
const signpage = (data) => {
    return request({

        url: "/seapi/sign/signpage",
        method: "post",
        data

    })


}
//根据id查询用户
const findById = (id) => {
    return request({

        url: `/seapi/sign/detail/${id}`,
        method: "get"


    })


}
const save = (data) => {
    return request({

        url: "/seapi/sign/signsave",
        headers: { "Content-Type": "application/json;charset=utf-8" },
        method: "post",
        data

    })


}



export { deletesign, signpage, findById, save }