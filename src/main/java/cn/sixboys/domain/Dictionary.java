package cn.sixboys.domain;

import lombok.*;

/**
 * 数据字典实体类
 * @author FuqiangWu
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Dictionary {
    private Long id;
    private String sn;
    private String title;
    private String intro;
}
