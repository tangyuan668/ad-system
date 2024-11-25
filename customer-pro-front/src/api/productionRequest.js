//@表示src路径
//导入axios对象
import request from "@/utils/request.js"
//登录,data对象，传后端数据

//删除渠道
const deleteproductionRequest = (id) => {
    return request({

        url: `/seapi/productionRequest/productionRequestdelete/${id}`,
        method: "get"


    })

}
//分页获取productionRequest数据
const productionRequestpage = (data) => {
    return request({

        url: "/seapi/productionRequest/productionRequestpage",
        method: "post",
        data

    })


}
//根据id查询用户
const findById = (id) => {
    return request({

        url: `/seapi/productionRequest/detail/${id}`,
        method: "get"


    })


}
const save = (data) => {
    return request({

        url: "/seapi/productionRequest/productionRequestsave",
        headers: { "Content-Type": "application/json;charset=utf-8" },
        method: "post",
        data

    })


}



export { deleteproductionRequest, productionRequestpage, findById, save }