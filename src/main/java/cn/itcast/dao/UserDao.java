package cn.itcast.dao;


import cn.itcast.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    @Select("select * from tab_user where username=#{name}")
    public User findUserByUsername(String usrname);

    @Insert("insert into tab_user (username,password,name,birthday,sex,telephone,email,status,code) values(#{username},#{password}" +
            ",#{name}" +
            ",#{birthday},#{sex},#{telephone},#{email},#{status},#{code})")
    public void save(User user);

    @Select("select * from tab_user where code=#{code}")
    User findByCode(String code);

    @Update("update tab_user set status=#{status} where code=#{code}")
    void updateStatus(User user);

    @Select("select * from tab_user where username=#{username} and password=#{password}")
    User findByUsernameAndPassword(User user);
}
