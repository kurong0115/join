package com.matrix.join.protocol;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName Pagination
 * @Description 分页数据
 * @Author Administrator
 * @Date 2020/1/29 13:43
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Pagination implements Serializable {

    private static final long serialVersionUID = 6221096946051067486L;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 页面大小
     */
    private Long pageSize;

    /**
     * 当前页码
     */
    private Long currentPage;

    /**
     * 构建分页对象
     * @param page
     * @return
     */
    public static Pagination convertPage(IPage page) {
        if (Objects.isNull(page)) {
            return null;
        }
        return new Pagination().setTotal(page.getTotal()).setCurrentPage(page.getCurrent()).setPageSize(page.getSize());
    }

    /**
     * 转换
     * @param to
     * @param from
     * @return
     */
    public static void convert(Page<?> from, Page<?> to) {
        to.setTotal(from.getTotal()).setCurrent(from.getCurrent()).setSize(from.getSize());
    }
}
