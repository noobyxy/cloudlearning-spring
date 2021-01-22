package com.yxy.cl.controller;

import com.yxy.cl.Utils.ResultUtil;
import com.yxy.cl.entity.Notice;
import com.yxy.cl.entity.User;
import com.yxy.cl.entity.Userfile;
import com.yxy.cl.entity.dto.form.FileUploadForm;
import com.yxy.cl.entity.dto.response.Result;
import com.yxy.cl.service.IFileService;
import com.yxy.cl.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

@RestController
public class UserFileController {
    @Autowired
    private IFileService fileService;
    @Autowired
    private INoticeService noticeService;


    @PostMapping(value="/upload")
    @ResponseBody
    public Result<Long> upLoadProduct(HttpServletRequest request, FileUploadForm fileUploadForm) {
        MultipartFile file = fileUploadForm.getFile();
        if (file == null) {
            return ResultUtil.fail("选择要上传的文件！");
        }
        if (file.getSize() > 1024 * 1024 * 1000) {
            return ResultUtil.fail("文件大小不能超过1000M！");
        }
//        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if (!"zip,rar".toUpperCase().contains(suffix.toUpperCase())) {
            return ResultUtil.fail("请选择rar或zip格式的压缩文件！");
        }
        String savePath = "/home/yxy/project_upload/";
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            savePathFile.mkdir();
        }
        String filename = new Date().getTime() + "." + suffix;
        try {
            //将文件保存指定目录
            file.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.fail("保存文件异常");
        }
        var userfile = new Userfile();
        userfile.setOldFileName(fileUploadForm.getOldName());
        userfile.setNewFileName(filename);
        userfile.setPath(savePath + filename);
        userfile.setSize(savePathFile.getTotalSpace());
        userfile.setUploadTime(new Timestamp(new Date().getTime()));

        userfile = fileService.save(userfile);
        var content = "用户" + fileUploadForm.getUserId() + "已上传文件" + fileUploadForm.getOldName();
        noticeService.addUnreadNotice(fileUploadForm.getUserId(), content);
        return ResultUtil.success(userfile.getId());
    }
}
