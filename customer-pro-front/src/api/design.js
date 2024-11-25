//@表示src路径
//导入axios对象
import request from "@/utils/request.js"
//登录,data对象，传后端数据

//删除渠道
const deletedesign = (id) => {
    return request({

        url: `/seapi/design/designdelete/${id}`,
        method: "get"


    })

}
//分页获取design数据
const designpage = (data) => {
    return request({

        url: "/seapi/design/designpage",
        method: "post",
        data

    })


}
//根据id查询用户
const findById = (id) => {
    return request({

        url: `/seapi/design/detail/${id}`,
        method: "get"


    })


}
const save = (data) => {
    return request({

        url: "/seapi/design/designsave",
        headers: { "Content-Type": "application/json;charset=utf-8" },
        method: "post",
        data

    })


}



export { deletedesign, designpage, findById, save }