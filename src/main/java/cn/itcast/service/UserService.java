package cn.itcast.service;

import cn.itcast.domain.User;


public interface UserService {
    boolean register(User user);

    boolean active(String code);

    User login(String username, String password);

    User login(User u) ;
}
