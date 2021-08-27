package com.hackathon.demo.repository;

import com.hackathon.demo.entity.Asset;
import com.hackathon.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, String> {

    @Query(value="SELECT * FROM user where user_name=:myid", nativeQuery = true)
    User findThisUserById(@Param("myid") String name);
}
