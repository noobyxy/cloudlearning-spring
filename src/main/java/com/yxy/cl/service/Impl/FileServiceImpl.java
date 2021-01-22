package com.yxy.cl.service.Impl;

import com.yxy.cl.dao.UserfileRepository;
import com.yxy.cl.entity.Userfile;
import com.yxy.cl.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements IFileService {
    @Autowired
    private UserfileRepository fileDao;

    @Override
    public Userfile save(Userfile file) {
        return fileDao.save(file);
    }
}
