package com.zy.ds.api;

import com.zy.ds.common.BookInfoDto;
import com.zy.ds.common.Response;

import java.util.List;

public interface FavoriteService {

    Response<String> addUserFavoriteBook(Long userId, Long isbn);
    Response<String> delUserFavoriteBook(Long userId, Long isbn);
    Response<List<BookInfoDto>> queryUserFavorites(Long userId);
}
