package cn.itcast.controller;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.Route;
import cn.itcast.domain.User;
import cn.itcast.service.FavoriteService;
import cn.itcast.service.RouteService;
import cn.itcast.service.impl.RouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/route")
public class RouteController {
    @Autowired
    private RouteService service;
    @Autowired
    private FavoriteService favoriteServiceservice;
    @RequestMapping("/pageQuery")
    public @ResponseBody PageBean<Route> pageQuery(@RequestParam(value = "currentPage",defaultValue = "1") String currentPageStr,
                                                   @RequestParam(value = "pageSize",defaultValue = "12") String pageSizeStr,
                                                   String cid, String rname) throws UnsupportedEncodingException {
        System.out.println(currentPageStr+" "+pageSizeStr+" "+cid+" "+rname);
        if (rname.equals("")||rname.equals("null")){
            rname = null;
        }
        if (rname!=null) {
            rname = "%"+rname+"%";
        }

        int cidi = 0;
        if (cid!=null&&cid.length()>0){
            cidi=Integer.parseInt(cid);
        }
        int currentPage = 1;
        if (currentPageStr!=null&&currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }
        int pageSize = 12;
        if (pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }

        PageBean<Route> onePage = service.findOnePage(currentPage, pageSize, cidi, rname);
        return onePage;
    }
    @RequestMapping("/findOne")
    public @ResponseBody Route findOne(String rid) {


        int rid1 = 0;
        if (rid!=null&&rid.length()>0){
            rid1=Integer.parseInt(rid);
        }
        Route route = service.findOne(rid1);
        return route;
    }
    @RequestMapping("/isFavorite")
    public @ResponseBody boolean isFavorite(String rid, HttpSession session){


        User user = (User) session.getAttribute("user");
        int uid = 0;
        if (user!=null){
            uid=user.getUid();
        }else {

            return false;
        }

        boolean favorite = favoriteServiceservice.isFavorite(uid, rid);
        System.out.println(favorite);
        return  favorite;
    }

    @RequestMapping("/addFavorite")
    public @ResponseBody boolean addFavorite(String rid, HttpSession session) throws ServletException, IOException {
        System.out.println(rid);
        User user = (User) session.getAttribute("user");
        int uid = 0;
        if (user!=null){
            uid=user.getUid();
        }else {

            return true;
        }

        favoriteServiceservice.addFavorite(uid, rid);
        return true;
    }
    @RequestMapping("/cancelFavorite")
    public void cancelFavorite(String rid, HttpSession session)  {
        User user = (User) session.getAttribute("user");
        int uid = 0;
        if (user!=null){
            uid=user.getUid();
        }else {

            return;
        }
        favoriteServiceservice.cancelFavorite(uid, rid);

    }
}
