package com.yxy.cl.service;

import com.yxy.cl.entity.Notice;

import java.util.List;

public interface INoticeService {
    void addUnreadNotice(Long userid,String content);

    void changeNoticeStatus(Integer noticeid,String status);

    List<Notice> getNoticeByUserId(Integer userid);

    Integer findNoticeNum(Integer userid);
}
