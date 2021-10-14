package indi.xm.vo;

import javax.persistence.Id;
import java.util.List;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.vo
 * @ClassName: CategoryVO
 * @Author: albert.fang
 * @Description: 需要传给前端展示的 View 专门用来展示的
 * @Date: 2021/10/14 11:18
 */
public class CategoryVO {
    /**
     * 二级分类id
     */
    private Integer id;

    /**
     * 二级分类名称
     */
    private String name;

    /**
     * 二级分类类型
     */
    private Integer type;

    /**
     * 二级分类父id
     */
    private Integer fatherId;

    // 三级分类
    private List<SubCategoryVO> subCategoryVOS;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public List<SubCategoryVO> getSubCategoryVOS() {
        return subCategoryVOS;
    }

    public void setSubCategoryVOS(List<SubCategoryVO> subCategoryVOS) {
        this.subCategoryVOS = subCategoryVOS;
    }
}
