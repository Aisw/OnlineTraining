package com.example.train.controller;

import com.example.train.pojo.Score;
import com.example.train.pojo.document.Document;
import com.example.train.pojo.employee.Employee;
import com.example.train.pojo.program.ProNamePath;
import com.example.train.pojo.program.Program;
import com.example.train.pojo.program.Resource;
import com.example.train.service.*;
import com.example.train.utils.JSONUtil;
import com.example.train.utils.JWTTokenUtil;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * 移动端控制
 */
@RequestMapping(value = "/and")
@RestController
public class AndroidController {

    @Autowired
    private ProgramService programService;

    @Autowired
    private ResDocService resDocService;
    @Autowired
    private DocumentService documentService;

    @Autowired
    private ScoreService scoreService;
    /**
     * 获取培训图片
     * @return
     */
    @RequestMapping(value = "/swiper")
    public String showSwiperImg(){
        System.out.println("/and/swiper端口执行");
        List<Program> programs = programService.findAll();
        List<String> paths=  programs.stream()
                .filter(program -> !"".equals(program.getPath())&&null != program.getPath())
                .limit(6)
                .map(Program::getPath).collect(Collectors.toList());
        return JSONUtil.JSONSuccessToString(paths);
    }

    /**
     * 获取3类培训及其对应两类资源
     * @return
     */
    @RequestMapping(value = "/group")
    public String getProResNamePath(){
        List<ProNamePath> proNamePaths = programService.getProNamePath();
        return JSONUtil.JSONSuccessToString(proNamePaths);
    }

    /**
     * 查询改用户应该进行的培训
     * @param token
     * @return
     */
    @RequestMapping(value = "/pro")
    public String getProResNamePathById(@RequestHeader("token")String token ){
        System.out.println(token);
        List<ProNamePath> proNamePaths = new ArrayList<>();
        if (null != token){
            try {
                // 截取JWT前缀
                Integer id = Integer.parseInt(JWTTokenUtil.getMemberIdByJwtToken(token));
                System.out.println(id);
                proNamePaths = programService.getProNamePathByEmpId(id);
                System.out.println("proNamePaths:"+proNamePaths);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return JSONUtil.JSONSuccessToString(proNamePaths);
    }

    /**
     * 为用户添加积分
     * @param token
     * @param resId
     */
    @RequestMapping(value = "score")
    public void addIntegration(@RequestHeader("token")String token ,
                               @Param("resId") Integer resId,
                               @Param("second")Integer second){
        System.out.println("resId + second:"+resId+"--"+second);
        List<Integer> list = resDocService.findDocIdsByResId(resId);
        Integer docId = list.get(0);
        System.out.println("docId:"+docId);
        if (null != token){
            // 截取JWT前缀
            Integer empId = Integer.parseInt(JWTTokenUtil.getMemberIdByJwtToken(token));
            System.out.println(empId);
            Document document = documentService.findById(docId);
            System.out.println("document.getDuration():"+document.getDuration());

            if (document.getDuration()*60 > second){
                return;
            }
            Score score = new Score();
            score.setEmpId(empId);
            score.setDocId(docId);
            score.setName(document.getName());
            score.setTime(new Date(System.currentTimeMillis()));
            score.setScore(document.getScore());

            try{
                scoreService.addScore(score);
            }catch (Exception e){
                System.out.println("重复插入");
            }
        }
    }

    /**
     * 获取用户的全部积分
     * @param token
     * @return
     */
    @RequestMapping(value = "/scores")
    public String getScore(@RequestHeader("token") String token){
        if (null != token && !"".equals(token)){
            Integer empId = Integer.parseInt(JWTTokenUtil.getMemberIdByJwtToken(token));
            Double total = scoreService.getTotalByEmpId(empId);
            return JSONUtil.JSONSuccessToString(total);
        }
        return JSONUtil.JSONFailedToString("未能获取到结果");
    }

    /**
     * 获取用户的积分明细
     * @param token
     * @return
     */
    @RequestMapping(value = "show")
    public String getScores(@RequestHeader("token") String token){
        if (null != token && !"".equals(token)){
            Integer empId = Integer.parseInt(JWTTokenUtil.getMemberIdByJwtToken(token));
            List<Score> scores = scoreService.getScoresByEmpId(empId);

            return JSONUtil.JSONSuccessToString(scores);
        }
        return JSONUtil.JSONFailedToString("未能获取到结果");
    }
}
