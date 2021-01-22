package com.yxy.cl.dao;

import com.yxy.cl.entity.Userfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserfileRepository extends JpaRepository<Userfile, Long>, JpaSpecificationExecutor<Userfile> {

}