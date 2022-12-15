package edu.zut.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: roydon - 2022/12/15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Area {

    private Integer id;
    private String parent;
    private String code;
    private String name;

}
