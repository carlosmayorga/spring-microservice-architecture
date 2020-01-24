package com.cmayorga.spring.test.user.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.cmayorga.spring.test.common.model.entity.User;

@RepositoryRestResource(path = "admin")
public interface IUserDao extends PagingAndSortingRepository<User, Long>{
    
    @RestResource(path="findbyusername")
    public User findByUsername(@Param("username") String username);
   
    @Query("select u from User u where u.username=?1")
    public User getUserByUsername(String username);

}
