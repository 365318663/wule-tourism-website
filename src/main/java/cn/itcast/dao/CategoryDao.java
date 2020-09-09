package cn.itcast.dao;



import cn.itcast.domain.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryDao {
    @Select("select * from tab_category order by cid ")
    public List<Category> findAll();
}
