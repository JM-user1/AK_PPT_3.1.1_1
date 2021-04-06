package com.jm.spring.springboot.service;

import com.jm.spring.springboot.entity.Role;
import com.jm.spring.springboot.entity.User;
import com.jm.spring.springboot.repository.RoleRepository;
import com.jm.spring.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private  EntityManager em;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public User findUserById(Long userId) {
        Optional<User> userFromDB = userRepository.findById(userId);
        return userFromDB.orElse(new User());
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    @Override
    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }
//  public void edit(Long id, User user) {
//    User updatedUser = userRepository.getOne(id);
//    updatedUser.setUsername(user.getUsername());
//
//    userRepository.saveAndFlush(updatedUser);
//  }

    @Override
    public User getById(Long id) {
        User user = userRepository.getOne(id);
        System.out.println(user.getUsername());
        return user;
    }



}
