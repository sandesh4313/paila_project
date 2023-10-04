package com.paila.ecommerce.repository;

import com.paila.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
   Optional<User> findUserByEmail(String email);

   @Query(
           nativeQuery = true,
           value = "select * from users where email=?1"
   )
   User getUSerByEmail(String email);

}
