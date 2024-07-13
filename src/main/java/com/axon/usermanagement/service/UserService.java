package com.axon.usermanagement.service;

import com.axon.usermanagement.action.AuditingUserListener;
import com.axon.usermanagement.domain.entity.User;
import com.axon.usermanagement.domain.mapper.UserMapper;
import com.axon.usermanagement.domain.repository.UserRepository;
import com.commonlib.annotation.EntityListener;
import com.commonlib.service.dto.user.UserDTO;
import com.commonlib.util.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
//@Transactional
@EntityListener(listenerClass = AuditingUserListener.class,
        createMethods = {"create"},
        updateMethods = {"update"},
        deleteMethods = {"remove"})
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Cacheable(value = "users", key = "#id")
    public UserDTO findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new BadRequestException("User not found");
        }
        return userMapper.toDto(user.get());
    }

//    @Cacheable(value = "users")
    public Set<UserDTO> getAll() {
        return userRepository.findAll().stream().map(user -> userMapper.toDto(user)).
                sorted(Comparator.comparing(UserDTO::getId)).
                collect(Collectors.toSet());
    }

//    @CachePut(value = "users", key = "userDTO.id")
//    @CacheEvict(value = "users", allEntries = true)
//    @CachePut(value = "users", key = "#userDTO.name")
    @CacheEvict(value = "users", allEntries = true)
    public UserDTO create(UserDTO userDTO) {
        User createdUser = userRepository.save(userMapper.toEntity(userDTO));

        return userMapper.toDto(createdUser);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void remove(UserDTO userDTO) {
        userRepository.delete(userMapper.toEntity(userDTO));
    }

    @CachePut(value = "users", key = "#userDTO.username")
//    @CacheEvict(value = "users", allEntries = true)
    public UserDTO update(UserDTO userDTO) {

        User updatedUser = userRepository.save(userMapper.toEntity(userDTO));

        return userMapper.toDto(updatedUser);
    }

    @Cacheable(value = "users", key = "#username")
    public UserDTO findByUsernameAndPassword(String username, String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isEmpty()) {
            throw new BadRequestException("User not found");
        }
        return userMapper.toDto(user.get());
    }


    @Cacheable(value = "users", key = "#username")
    public UserDTO findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new BadRequestException("User not found");
        }
        return userMapper.toDto(user.get());
    }
}
