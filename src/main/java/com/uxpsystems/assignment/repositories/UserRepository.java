package com.uxpsystems.assignment.repositories;

import com.uxpsystems.assignment.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findAll();

    public User findById(long id);

    public void deleteById(long id);

    public void deleteAll();

    @Transactional
    @Modifying
    @Query("update User u set u.status =:status where u.id = :userId")
    void updateUser(@Param("userId") long userId, @Param("status") String status);
}
