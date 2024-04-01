package com.zy.ds.api;

import com.zy.ds.common.BookInfoDto;
import com.zy.ds.common.Response;

import java.util.List;

public interface StoreService {

    Response<BookInfoDto> getBookInfoByIsbn(Long isbn);
    Response<List<BookInfoDto>> getAllBooks();
    Response<String> addBook(BookInfoDto bookInfoDto);
    Response<String> modifyBookinfo(BookInfoDto bookInfoDto);
    Response<String> removeBookByIsbn(Long isbn);
}