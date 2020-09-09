package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.util.MailUtils;
import cn.itcast.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public boolean register(User user) {
        User userByName = userDao.findUserByUsername(user.getUsername());
        if(userByName!=null){
            return false;
        }else{
            String uuid = UuidUtil.getUuid();
            user.setCode(uuid);
            user.setStatus("N");
            userDao.save(user);
            String content  = "<a href='http://localhost:8080/ssm_war/user/active?code="+uuid+"'>点击激活【黑马旅游网】</a>";
            MailUtils.sendMail(user.getEmail(),content,"激活邮件");
            return true;
        }

    }

    @Override
    public boolean active(String code) {

        User user = userDao.findByCode(code);
        if(user!=null){
            user.setStatus("Y");
            userDao.updateStatus(user);
            return true;
        }
        return false;
    }

    @Override
    public User login(String username, String password) {
        return null;
    }

    @Override
    public User login(User u) {
        User user= userDao.findByUsernameAndPassword(u);
        return user;
    }
}
