package cn.itcast.dao;

import cn.itcast.domain.RouteImg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface RouteImgDao {
    @Select("select * from tab_route_img where rid = #{rid}")
    List<RouteImg> queryImgs(@Param("rid") int rid);
}
