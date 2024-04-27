package poovie.cc1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "login_credentials")
public class LoginModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int userId;
    private String email;
    private String password;
}
