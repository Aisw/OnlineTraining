package com.example.train.dao;

import com.example.train.pojo.program.ResNamePath;
import com.example.train.pojo.program.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/**
 * 对资源的处理
 */
public interface ResourceDao {

    /**
     * 添加资源获取id
     * @param resource
     * @return
     */
    boolean insertResource(Resource resource);

    /**
     * 根据id修改资源
     * @param resource
     * @return
     */
    boolean updateResource(Resource resource);

    /**
     * 根据id删除资源
     * @param id
     * @return
     */
    boolean deleteResource(Integer id);

    /**
     * 根据id修改resource的路径
     * @param id
     * @param path
     * @return
     */
    boolean updatePathById(Integer id,String path);

    /**
     * 根据id查询路径
     * @param id
     * @return
     */
    String selectPathById(Integer id);

    /**
     * 根据id查询resource信息
     * @param resId
     * @return
     */
    Resource selectByResId(Integer resId);

    /**
     * 根据id查询简单的资源
     * @param id
     * @return
     */
    List<ResNamePath> selectResNamePathById(Integer id);
}
