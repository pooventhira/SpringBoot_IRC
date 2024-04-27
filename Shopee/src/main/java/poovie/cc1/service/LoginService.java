package poovie.cc1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import poovie.cc1.model.LoginModel;
import poovie.cc1.repository.LoginRepository;

@Service
public class LoginService 
{
    @Autowired
    LoginRepository repo;

    // Creating User
    public LoginModel createNewUser (@NonNull LoginModel user) {
        return repo.save(user);
    }
    // Getting All Users
    public List<LoginModel> getAllUsers () {
        return repo.findAll();
    }
    // Getting User By Email
    public Optional<LoginModel> getUserByEmail (String email) {
        return repo.findByEmail(email);
    }
    // Updating User
    public LoginModel updatUser (@NonNull String email, @RequestBody LoginModel user) {
        return repo.findByEmail(email)
        .map(existingUser -> {
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            return repo.save(existingUser);
        }).orElseThrow(
            () -> new RuntimeException("User not found in this email: "+email)
        );
    }
}
