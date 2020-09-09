package cn.itcast.dao;


import cn.itcast.domain.Favorite;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface FavoriteDao {
    @Select("select * from tab_favorite where uid=#{uid} and rid=#{rid}")
    Favorite findByUidAndRid(@Param("uid") int uid,@Param("rid") int rid);

    @Select("select count(*) from tab_favorite where  rid=#{rid}")
    int findCountByRid(@Param("rid") int rid);

    @Insert("insert into tab_favorite(rid,date,uid) values (#{rid},#{date},#{uid})")
    void addFavorite(@Param("uid") int uid, @Param("date")Date date, @Param("rid") int rid);

    @Delete("delete from tab_favorite where rid=#{rid} uid=#{uid}")
    void cancelFavorite(@Param("uid") int uid,@Param("rid") int rid);
}
