package edu.zut.domain.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (DictDistrict)表实体类
 *
 * @author roydon
 * @since 2022-12-15 12:14:02
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_dict_district")
public class DictDistrict  {

    @TableId
    private Integer id;

    private String parent;
    
    private String code;
    
    private String name;

}

