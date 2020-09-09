package cn.itcast.service;


import cn.itcast.domain.PageBean;
import cn.itcast.domain.Route;

public interface RouteService {


    PageBean<Route> findOnePage(int currentPage, int pageSize, int cid, String rname);

    Route findOne(int rid);
}
