package com.zy.ds.store.persist;

import com.zy.ds.store.model.Bookinfo;

import java.util.List;

public interface BookPersistCmpt {

    Bookinfo getBook(Long isbn);
    List<Bookinfo> getAllBooks();
    int saveBook(Bookinfo bookinfo);
    int updateBook(Bookinfo bookinfo);
    int deleteBook(Long isbn);
}
