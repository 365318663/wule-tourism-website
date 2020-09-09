package cn.itcast.service;

public interface FavoriteService {
    boolean isFavorite(int uid, String rid);

    void addFavorite(int uid, String rid);

    void cancelFavorite(int uid, String rid);
}
