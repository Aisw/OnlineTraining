package com.example.train.service;

import com.example.train.OnlineTrainingApplication;
import com.example.train.pojo.Score;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OnlineTrainingApplication.class)
class ScoreServiceTest {

    @Autowired
    private ScoreService service;

    @Test
    void addScore() {
        Score score = new Score();
        score.setEmpId(1);
        score.setDocId(2);
        score.setName("签到");
        score.setScore(1.2);
        Date date = new Date(System.currentTimeMillis());
        score.setTime(date);
        System.out.println(service.addScore(score));
    }

    @Test
    void getTotalByEmpId(){
        System.out.println(service.getTotalByEmpId(150));
    }

    @Test
    void getScoresByEmpId(){
        List<Score> scores = service.getScoresByEmpId(150);
        for (Score score : scores) {
            System.out.println(score);
        }
    }
}