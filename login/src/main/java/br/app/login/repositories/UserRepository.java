package br.app.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.app.login.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findById(long id);
	
	@Query(value="select * from applogin.user where email = :email and senha = :senha", nativeQuery = true)
	public User login(String email, String senha);
}
