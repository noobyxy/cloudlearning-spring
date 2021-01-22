package com.yxy.cl.dao;

import com.yxy.cl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query(value = "select p from User p where p.userName=:name and p.password=:password")
    public List<User> findUserByNameAndPassword(@Param("name") String name, @Param("password")String password);

    @Query(value = "select p from User p where p.userName=:name")
    List<User> findUserByName(@Param("name") String name);

    @Query(value = "select p.userName from User p where p.id=?1")
    String findNameById(Long id);
}