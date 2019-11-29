package com.example.learn.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.learn.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	
	@Query(value="UPDATE user SET firstname=?1 Where id=?2",nativeQuery = true)
	Optional<User> updateFirstnameById(User user);
	
	
	 
	  
	  
	  
}
