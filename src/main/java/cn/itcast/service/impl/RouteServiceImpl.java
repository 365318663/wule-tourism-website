package cn.itcast.service.impl;

import cn.itcast.dao.FavoriteDao;
import cn.itcast.dao.RouteDao;
import cn.itcast.dao.RouteImgDao;
import cn.itcast.dao.SellerDao;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.Route;
import cn.itcast.domain.RouteImg;
import cn.itcast.domain.Seller;
import cn.itcast.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteDao routeDao ;
    @Autowired
    private RouteImgDao imgDao;
    @Autowired
    private SellerDao sellerDao ;
    @Autowired
    private FavoriteDao favoriteDao ;
    @Override
    public PageBean<Route> findOnePage(int currentPage, int pageSize, int cid, String rname) {
        PageBean<Route> routePageBean = new PageBean<>();
        int start = (currentPage-1)*pageSize;

        int totalCount = routeDao.findTotalCount(cid,rname);
        List<Route> routes = routeDao.findByPage( start, pageSize, cid,rname);
        routePageBean.setCurrentPage(currentPage);
        routePageBean.setPageSize(pageSize);
        routePageBean.setRoutes(routes);
        routePageBean.setTotalCount(totalCount);
        int totalPage = totalCount%pageSize==0 ? totalCount/pageSize: totalCount/pageSize+1;
        routePageBean.setTotalPage(totalPage);
        return routePageBean;
    }

    @Override
    public Route findOne(int rid) {
        Route route = routeDao.findOne(rid);
        List<RouteImg> routeImgs = imgDao.queryImgs(rid);
        Seller seller = sellerDao.findOne(route.getSid());
        int favoriteCount = favoriteDao.findCountByRid(rid);

        route.setRouteImgList(routeImgs);
        route.setCount(favoriteCount);
        route.setSeller(seller);
        return route;
    }
}
