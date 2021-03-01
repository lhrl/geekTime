package org.geektimes.projects.user.service.impl;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.repository.UserRepository;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.sql.DBConnectionManager;

import java.util.Collection;

/**
 * Desc: 用户服务
 * User: mercylhrl
 * Date: 2021-02-28 15:45
 */
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new DatabaseUserRepository(new DBConnectionManager());
    }


    @Override
    public boolean register(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }

    @Override
    public Collection<User> getAll() {
        return userRepository.getAll();
    }

}
