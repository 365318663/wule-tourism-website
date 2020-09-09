package cn.itcast.service.impl;

import cn.itcast.dao.CategoryDao;
import cn.itcast.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("categoryService")
public class CategoryServiceImpl implements cn.itcast.service.CategoryService {
    @Autowired
    CategoryDao categoryDao;
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
