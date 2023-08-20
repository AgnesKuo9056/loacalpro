package com.pinyun.java.gradle.Repository;
import com.pinyun.java.gradle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByPhoneNumber(String email);

}