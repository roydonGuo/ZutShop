package edu.zut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.entity.GoodsCategory;
import edu.zut.mapper.GoodsCategoryMapper;
import edu.zut.service.GoodsCategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * (GoodsCategory)表服务实现类
 *
 * @author roydon
 * @since 2022-12-24 19:06:34
 */
@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory> implements GoodsCategoryService {

    /**
     * 获取树形结构
     */
    @Override
    public List<GoodsCategory> getListTree() {
        List<GoodsCategory> goodsCategoryList = list();
        List<GoodsCategory> returnList = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        for (GoodsCategory c : goodsCategoryList) {
            tempList.add(c.getId());
        }
        for (GoodsCategory c : goodsCategoryList) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(c.getParentId())) {
                recursionFn(goodsCategoryList, c);
                returnList.add(c);
            }
        }
        if (returnList.isEmpty()) {
            returnList = goodsCategoryList;
        }
        return returnList;
    }



    /**
     * 递归列表
     */
    private void recursionFn(List<GoodsCategory> list, GoodsCategory t) {
        // 得到子节点列表
        List<GoodsCategory> childList = getChildList(list, t);
        t.setChileList(childList);
        for (GoodsCategory tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<GoodsCategory> getChildList(List<GoodsCategory> list, GoodsCategory t) {
        List<GoodsCategory> tlist = new ArrayList<>();
        Iterator<GoodsCategory> it = list.iterator();
        while (it.hasNext()) {
            GoodsCategory n = it.next();
            if (n.getParentId() != null && n.getParentId() == t.getId()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<GoodsCategory> list, GoodsCategory t) {
        return getChildList(list, t).size() > 0;
    }

}

