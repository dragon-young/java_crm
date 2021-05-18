package cn.sixboys.domain;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.*;

import java.util.Date;

/**
 * @author mingjuntang
 * @Data 2021/5/17 9:01
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransRecord {
    private Long id;
    private Long customerId;
    private Long transUser;
    @DateTimeFormat(value = "yyyy-MM-dd")
    private Date transTime;
    private Long oldSeller;
    private Long newSellerId;
    private String transReason;
}
