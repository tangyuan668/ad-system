//@表示src路径
//导入axios对象
import request from "@/utils/request.js"
//登录,data对象，传后端数据

//删除渠道
const deleteproposal = (id) => {
    return request({

        url: `/seapi/proposal/proposaldelete/${id}`,
        method: "get"


    })

}
//分页获取proposal数据
const proposalpage = (data) => {
    return request({

        url: "/seapi/proposal/proposalpage",
        method: "post",
        data

    })


}
//根据id查询用户
const findById = (id) => {
    return request({

        url: `/seapi/proposal/detail/${id}`,
        method: "get"


    })


}
const save = (data) => {
    return request({

        url: "/seapi/proposal/proposalsave",
        headers: { "Content-Type": "application/json;charset=utf-8" },
        method: "post",
        data

    })


}



export { deleteproposal, proposalpage, findById, save }