package cn.sixboys.domain;

import lombok.*;

/**
 * 字典明细实体类
 * @author FuqiangWu
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Dictionarydetails {

    private Long id;
    private String title;
    private Long sequence;
    private Long dictionaryId;

}
