package com.zy.ds.web.vo;

import com.zy.ds.common.OrderInfoDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.FastDateFormat;


@Data
@NoArgsConstructor
public class OrderInfoVO {

    public static final FastDateFormat ISO_DATE_FORMAT  = FastDateFormat.getInstance("yyyy-MM-dd");

    private Long orderId;
    private Long isbn;
    private String bookTitle;
    private String author;
    private Float price;
    private String purchaseDate;

    public OrderInfoVO(OrderInfoDto dto) {

        this.orderId = dto.getId();
        this.isbn = dto.getIsbn();
        this.bookTitle = dto.getBookTitle();
        this.author = dto.getAuthor();
        this.price = dto.getPrice();
        this.purchaseDate = ISO_DATE_FORMAT.format(dto.getPurchaseDate());
    }

}
