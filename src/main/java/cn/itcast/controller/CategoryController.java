package cn.itcast.controller;

import cn.itcast.domain.Category;
import cn.itcast.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService service;
    @RequestMapping("/findAll")
    public @ResponseBody List<Category> findAll(){
        List<Category> all = service.findAll();
        System.out.println(all);
        return all;
    }
}
