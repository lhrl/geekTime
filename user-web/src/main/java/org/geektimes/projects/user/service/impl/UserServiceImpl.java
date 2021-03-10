package org.geektimes.projects.user.service.impl;

import org.geektimes.projects.user.context.ComponentContext;
import org.geektimes.projects.user.context.ComponentContextAware;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.domain.UserReqDTO;
import org.geektimes.projects.user.repository.UserRepository;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.util.BeanConvertUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.Validator;
import java.util.Collection;

/**
 * Desc: 用户服务
 * User: mercylhrl
 * Date: 2021-02-28 15:45
 */
public class UserServiceImpl implements UserService, ComponentContextAware {

    @Resource(name = "bean/userRepository")
    private  UserRepository userRepository;

    @Resource(name = "bean/entityManager")
    private  EntityManager entityManager;


    private ComponentContext componentContext;

    @Override
    public void setComponentContext(ComponentContext componentContext) {
        this.componentContext = componentContext;
    }

    @Override
    public void register(UserReqDTO userReqDTO) {
        User user = BeanConvertUtils.userDTO2User(userReqDTO);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
    }

    @PostConstruct
    public void init() {
        System.out.println("call UserServiceImpl.init method finished");
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
