package com.zy.ds.favorites.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FavoritesInfo {

    private Long userId;
    private Long isbn;

    public FavoritesInfo(Long userId, Long isbn) {
        this.userId = userId;
        this.isbn = isbn;
    }
}
