// src/api/moderation.js

import request from '@/utils/request';  // 使用你项目中的 request 模块

// 调用内容审核 API
export const moderateImage = (data) => {
    return request({
        url: '/seapi/moderateImage',  // 确保 URL 与后端匹配
        method: 'post',  // 使用 POST 方法
        data: data,  // 直接发送 JSON 数据
        headers: {
            'Content-Type': 'application/json',  // 设置内容类型为 JSON
        },
    });
};
