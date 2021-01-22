package com.yxy.cl.service.Impl;

import com.yxy.cl.entity.Notice;
import com.yxy.cl.dao.NoticeRepository;
import com.yxy.cl.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yxy.cl.Utils.TimeUtils.nowTime;

@Service
public class NoticeService implements INoticeService {
    @Autowired
    private NoticeRepository noticeDao;

    @Override
    public void addUnreadNotice(Long userid, String content) {
        Notice notice = new Notice();
        notice.setStatus("未读");
        notice.setContent(content);
        notice.setUserid(userid);
        notice.setTime(nowTime());
        noticeDao.save(notice);
    }

    @Override
    public void changeNoticeStatus(Integer noticeid,String status) {
        noticeDao.updateStatusById(noticeid,status);
    }

    @Override
    public List<Notice> getNoticeByUserId(Integer userid) {
        return noticeDao.findNoticesByUseridOrderByTimeDesc(userid);
    }

    @Override
    public Integer findNoticeNum(Integer userid) {
        return noticeDao.countByUseridAndStatus(userid, "未读");
    }
}
