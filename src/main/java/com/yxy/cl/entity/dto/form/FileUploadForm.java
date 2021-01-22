package com.yxy.cl.entity.dto.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class FileUploadForm {
    private String oldName;

    private MultipartFile file;

    private Long userId;

}
