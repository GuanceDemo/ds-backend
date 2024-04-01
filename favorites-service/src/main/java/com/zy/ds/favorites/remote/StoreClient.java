package com.zy.ds.favorites.remote;

import com.zy.ds.api.StoreService;
import com.zy.ds.common.BookInfoDto;
import com.zy.ds.common.Response;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("storeClient")
public class StoreClient {

    private static final Logger log = LoggerFactory.getLogger(StoreClient.class);

    @DubboReference(version = "1.0.0")
    private StoreService storeService;

    public BookInfoDto getBookInfoByIsbn(Long isbn) {

        try {
            Response<BookInfoDto> resp = storeService.getBookInfoByIsbn(isbn);
            return resp.isFail() ? null : resp.getData();
        }
        catch (Exception e) {
            log.error("store-service exception when storeService.getBookInfoByIsbn({})", isbn, e);
            return null;
        }
    }
}
