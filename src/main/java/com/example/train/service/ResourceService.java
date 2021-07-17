package com.example.train.service;

import com.example.train.dao.ResourceDao;
import com.example.train.pojo.program.Program;
import com.example.train.pojo.program.ResNamePath;
import com.example.train.pojo.program.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * 对于资源的操作
 */
public class ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    /**
     * 添加资源
     * @param resource
     * @return
     */
    public boolean addResource(Resource resource){
        return resourceDao.insertResource(resource);
    }

    /**
     * 根据id值修改resource资源
     * @param resource
     * @return
     */
    public boolean modifyResource(Resource resource){
        return resourceDao.updateResource(resource);
    }

    /**
     * 根据id删除资源
     * @param id
     * @return
     */
    public boolean deleteResource(Integer id){
        return resourceDao.deleteResource(id);
    }

    /**
     * 根据id修改资源路径
     * @param id
     * @param path
     * @return
     */
    public boolean modifyPathById(Integer id,String path){
        return resourceDao.updatePathById(id,path);
    }

    /**
     * 根据id查询获得路径
     * @param id
     * @return
     */
    public String getPathById(Integer id){
        return resourceDao.selectPathById(id);
    }

    /**
     * 根据resId查询得到资源的信息
     * @param resId
     * @return
     */
    public Resource getByResId(Integer resId){
        return resourceDao.selectByResId(resId);
    }

    /**
     * 根据培训id查询资源
     * @param proId
     * @return
     */
    public List<ResNamePath> getResNamePathByProId(Integer proId){
        return resourceDao.selectResNamePathById(proId);
    }
}
