package cn.itcast.service.impl;

import cn.itcast.dao.FavoriteDao;
import cn.itcast.domain.Favorite;
import cn.itcast.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    FavoriteDao favoriteDao;
    @Override
    public boolean isFavorite(int uid, String rid) {
        int i = Integer.parseInt(rid);
        Favorite favorite = favoriteDao.findByUidAndRid(uid,i);
        if (favorite==null){
            return false;
        }else {
            return true;
        }

    }

    @Override
    public void addFavorite(int uid, String rid) {
        int i = Integer.parseInt(rid);
        Date date = new Date();
        favoriteDao.addFavorite(uid,date,i);
    }

    @Override
    public void cancelFavorite(int uid, String rid) {
        int i = Integer.parseInt(rid);
        favoriteDao.cancelFavorite(uid,i);
    }
}
