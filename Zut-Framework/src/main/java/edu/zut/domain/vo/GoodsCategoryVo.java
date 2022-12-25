package edu.zut.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsCategoryVo {
    //主键@TableId
    private Integer id;

    //父分类id
    private Integer parentId;
    //名称
    private String name;
    //状态   1：正常   0：删除
    private Integer status;
    //排序号
    private Integer sortOrder;

    //子分类
    private List<GoodsCategoryVo> chileList = new ArrayList<>();


}

