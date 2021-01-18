package com.yxy.cl.mapper;

import com.yxy.cl.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends JpaRepository<User,Integer> {
//    @Select("select * from usertable order by user_id desc")
//    List<User> getUserList();

    @Query(value = "select p from User p where p.username=:name and p.password=:password")
    public List<User> findUserByNameAndPassword(@Param("name") String name, @Param("password")String password);

    @Query(value = "select p from User p where p.username=:name")
    List<User> findUserByName(@Param("name") String name);

    @Query(value = "select p.username from User p where p.id=?1")
    String findNameById(Integer id);

//    @Modifying
//    @Query("update User p set  = true where username=:name")
//    public void deleteByIds(@Param(value = "ids") List<String> ids);

//    @Transactional
//    @Query()
//    void insertSelective(User user);

//    @Modifying
//    @Query(value = "delete from Person p where p.name= :name")
//    public int  deleteByName(@Param("name") String name);
//
//    @Modifying
//    @Query(value = "insert into person(name,age,address) value(?1,?2,?3)",nativeQuery = true)
//    public int Add(String name, int age,String address);
//
//    @Modifying
//    @Query(value = "update person set name=?2 where id=?1",nativeQuery = true)
//    public int modify(int id,String name);

}
