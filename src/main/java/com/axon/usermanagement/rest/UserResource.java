package com.axon.usermanagement.rest;
 
import com.axon.usermanagement.service.UserService;
import com.commonlib.service.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService userService;

        @GetMapping("/id/{id}")
        public UserDTO findById(@PathVariable Long id) {
            return userService.findById(id);
        }

    @GetMapping("/all")
    public Set<UserDTO> getAll() {
        return userService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public void create(@RequestBody UserDTO toDoDTO) {
        userService.create(toDoDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/remove/id/{id}")
    public void remove(@PathVariable("id") Long id) {
        userService.remove(userService.findById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update")
    public UserDTO update(@RequestBody UserDTO toDoDTO) {
        return userService.update(toDoDTO);
    }


    @GetMapping("/username/{username}/password/{password}")
    public UserDTO findByUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
        return userService.findByUsernameAndPassword(username, password);
    }

    @GetMapping("/username/{username}")
    public UserDTO findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }
}