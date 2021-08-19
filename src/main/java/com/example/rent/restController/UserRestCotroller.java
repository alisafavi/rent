package com.example.rent.restController;

import com.example.rent.Exception.IdNotFoundException;
import com.example.rent.model.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserRestCotroller {
    @Resource
    private UserRestService userRestService;

    @GetMapping(value = "/search/{id}")
    public User search(@PathVariable int id) {
        Optional<User> users= userRestService.findUserById(id);
        if (!users.isPresent()) throw new IdNotFoundException();
        return users.get();
    }

    @GetMapping(value = "/getAll")
    public List<User> getAllUsers() {
        List<User> users= userRestService.getAllUsers();
        return users;
    }

    @PostMapping("/add")
    public String createUser(@RequestBody User user) {
        return userRestService.addUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        return userRestService.deleteUser(id);
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        return userRestService.updateUser(user);

    }

}
