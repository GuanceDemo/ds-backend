package com.zy.ds.order.remote;

import com.zy.ds.api.StoreService;
import com.zy.ds.common.BookInfoDto;
import com.zy.ds.common.Response;
import com.zy.ds.common.ResponseCode;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("storeClient")
public class StoreClient {

    private static final Logger log = LoggerFactory.getLogger(StoreClient.class);

    @DubboReference(version = "1.0.0")
    private StoreService storeService;

    public Response<BookInfoDto> getBookInfoByIsbn(Long isbn) {

        try {
            return storeService.getBookInfoByIsbn(isbn);
        }
        catch (Exception e) {
            log.error("store-service exception when storeService.getBookInfoByIsbn({})", isbn, e);
            return new Response<BookInfoDto>(ResponseCode.FAILURE).setMsg("store-service exception");
        }
    }
}
