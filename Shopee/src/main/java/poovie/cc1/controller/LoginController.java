package poovie.cc1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poovie.cc1.model.LoginModel;
import poovie.cc1.service.LoginService;

@RestController
@RequestMapping("/api/users")
public class LoginController 
{
    @Autowired
    private LoginService serv;

    @PostMapping("/createUser")
    public ResponseEntity<LoginModel> createUser(@NonNull @RequestBody LoginModel user) {
        LoginModel createdUser = serv.createNewUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/readAllUsers")
    public ResponseEntity<List<LoginModel>> getAllUsers() {
        List<LoginModel> users = serv.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/readUser/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        Optional<LoginModel> user = serv.getUserByEmail(email);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/updateUser/{email}")
    public ResponseEntity<LoginModel> updateUser(@NonNull @PathVariable String email,@RequestBody LoginModel updateRequest) {
    LoginModel updated = serv.updatUser(email, updateRequest);
    return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}