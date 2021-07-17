package com.example.train.dao;

import com.example.train.pojo.depart.Depart;
import com.example.train.pojo.depart.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门 dao层
 */
@Repository
public interface DepartmentDao {

    /**
     * 根据id查询部门信息
     * @param id
     * @return
     */
    List<Department> selectById(String id);

    List<Department> selectBySuperId(String id);

    /**
     * 查询所有的organization
     * @return
     */
    List<Depart> selectAll();

    /**
     * 根据id查询organization
     * @param id
     * @return
     */
    Depart selectDepartById(Integer id);

    /**
     * 修改上下级节点的关系
     * @param parent
     * @param son
     * @return
     */
    boolean updateDep(@Param("parent")Integer parent,
                      @Param("son") Integer son);

    /**
     * 修改organization的信息
     * @param depart
     * @return
     */
    boolean updateDepart(Depart depart);

    /**
     * 根据id删除organization的组织
     * @param id
     * @return
     */
    Integer deleteDepartById(Integer id);

    /**
     * 根据父级id删除所有的下级节点集合
     * @param superId
     * @return
     */
    Integer deleteDepartBySuperId(Integer superId);

    /**
     * 查询节点的所有子节点的id
     * @param superId
     * @return
     */
    List<Integer> selectIdBySuperId(Integer superId);
}
