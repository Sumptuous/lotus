package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.product.Img;
import com.lotus.core.dao.product.ImgMapper;
import com.lotus.core.query.product.ImgQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wyy
 */
@Service
public class ImgServiceImpl implements ImgService{

    @Resource
    private ImgMapper imgMapper;

    /**
     * 插入数据库
     *
     * @return
     */
    public Integer addImg(Img img) {
        return imgMapper.addImg(img);
    }

    /**
     * 根据主键查找
     */
    @Transactional(readOnly = true)
    public Img getImgByKey(Integer id) {
        return imgMapper.getImgByKey(id);
    }

    @Transactional(readOnly = true)
    public List<Img> getImgsByKeys(List<Integer> idList) {
        return imgMapper.getImgsByKeys(idList);
    }

    /**
     * 根据主键删除
     *
     * @return
     */
    public Integer deleteByKey(Integer id) {
        return imgMapper.deleteByKey(id);
    }

    public Integer deleteByKeys(List<Integer> idList) {
        return imgMapper.deleteByKeys(idList);
    }

    /**
     * 根据主键更新
     *
     * @return
     */
    public Integer updateImgByKey(Img img) {
        return imgMapper.updateImgByKey(img);
    }

    @Transactional(readOnly = true)
    public Pagination getImgListWithPage(ImgQuery imgQuery) {
        Pagination p = new Pagination(imgQuery.getPageNo(),imgQuery.getPageSize(),imgMapper.getImgListCount(imgQuery));
        p.setList(imgMapper.getImgListWithPage(imgQuery));
        return p;
    }

    @Transactional(readOnly = true)
    public List<Img> getImgList(ImgQuery imgQuery) {
        return imgMapper.getImgList(imgQuery);
    }
}
