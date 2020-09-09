package cn.itcast.dao;


import cn.itcast.domain.Seller;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerDao {
    @Select("select * from tab_seller where sid=#{sid}")
    Seller findOne(@Param("sid") int sid);
}
