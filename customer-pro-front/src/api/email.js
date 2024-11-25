import request from "@/utils/request.js"
const emailSave = (data) => {
    return request({

        url: "/seapi/email/emailsave",
        headers: { "Content-Type": "application/json;charset=utf-8" },
        method: "post",
        data


    })

}

const deleteemail = (id) => {
    return request({

        url: `/seapi/email/emaildelete/${id}`,
        method: "get"


    })

}
const getemail = (cuid) => {
    return request({

        url: `/seapi/email/emailget/${cuid}`,
        method: "get"


    })

}

const updateemail = (data) => {
    return request({

        url: "/seapi/email/emailupdate",
        headers: { "Content-Type": "application/json;charset=utf-8" },
        method: "post",
        data


    })

}
const getNums = (cuid) => {
    return request({

        url: `/seapi/email/emailnum/${cuid}`,
        method: "get"


    })

}


export { deleteemail, emailSave, getemail, updateemail, getNums }