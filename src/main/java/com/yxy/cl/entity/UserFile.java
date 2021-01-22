package com.yxy.cl.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "userfile")
@Entity
@Data
public class Userfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "userid", nullable = false)
    private Long userid;

    @Column(name = "new_file_name", nullable = false)
    private String newFileName;

    @Column(name = "old_file_name", nullable = false)
    private String oldFileName;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "upload_time")
    private Timestamp uploadTime;

}
