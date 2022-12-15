package edu.zut.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Author: roydon - 2022/12/15
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    private Integer id;
    private String parent;
    private String code;
    private String name;

    private List<Area> areaList;

}
