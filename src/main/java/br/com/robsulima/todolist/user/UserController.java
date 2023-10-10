package br.com.robsulima.todolist.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Modificadores:
 * Public
 * Private
 * Protected
 */

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody UserModel user)
    {
        var username = this.userRepository.findByUsername(user.getUsername());
        if (username != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }
        var userCreated = this.userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
