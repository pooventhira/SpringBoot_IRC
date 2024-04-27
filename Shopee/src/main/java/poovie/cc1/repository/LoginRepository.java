package poovie.cc1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poovie.cc1.model.LoginModel;

@Repository
public interface LoginRepository extends JpaRepository <LoginModel, Integer>
{
    Optional<LoginModel> findByEmail(String email);

}