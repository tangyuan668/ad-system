//@表示src路径
//导入axios对象
import request from "@/utils/request.js"
//登录,data对象，传后端数据

//删除渠道
const deleteChannel = (id) => {
    return request({

        url: `/seapi/channel/channeldelete/${id}`,
        method: "get"


    })

}
//分页获取channel数据
const channelpage = (data) => {
    return request({

        url: "/seapi/channel/channelpage",
        method: "post",
        data

    })


}
//根据id查询用户
const findById = (id) => {
    return request({

        url: `/seapi/channel/detail/${id}`,
        method: "get"


    })


}
const save = (data) => {
    return request({

        url: "/seapi/channel/channelsave",
        method: "post",
        data

    })


}



export { deleteChannel, channelpage, findById, save }