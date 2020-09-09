package cn.itcast.dao;

import cn.itcast.domain.Route;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface RouteDao {
    @Select("<script>" +
            "select * from tab_route " +
            "<where> " +
            "  <if test='cid!=null' >" +
            "      and cid=#{cid} " +
            "  </if>" +
            "  <if test='rname!=null'> " +
            "      and rname like #{rname}" +
            "  </if>" +
            "</where>" +
            " limit #{start},#{pageSize}" +
            "</script>")
    List<Route> findByPage(@Param("start") int start, @Param("pageSize") int pageSize,@Param("cid") int cid,@Param("rname") String rname);

    @Select("<script>" +
            "select count(rid) from tab_route " +
            "<where> " +
            "  <if test='cid!=null' >" +
            "      and cid=#{cid} " +
            "  </if>" +
            "  <if test='rname!=null'> " +
            "      and rname like #{rname}" +
            "  </if>" +
            "</where>" +
            "</script>")
    int findTotalCount(@Param("cid") int cid,@Param("rname") String rname);

    @Select("select * from tab_route where rid=#{rid}")
    Route findOne(@Param("rid") int rid);
}
