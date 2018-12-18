/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.polyv;

import java.util.List;

/**
 * @author lyq
 **/
public class Question {

    /**
     * 视频id
     */
    private String vid;

    /**
     * 问题出现的秒数
     */
    private Integer seconds;

    /**
     * 问题的描述
     */
    private String question;

    /**
     * 答案，三个
     */
    private List<String> answerList;

    /**
     * 正确答案
     */
    private String rightAnswer;

    /**
     * 解答详情
     */
    private String answer;

    /**
     * 答错是否显示解答详情 1为显示，0不显示
     */
    private Integer wrongShow;

    /**
     * 答错退回秒数(第wrongTime秒),-1不回退
     */
    private Integer wrongTime;

    /**
     * 测试问题Id，为空的时候新创建一个，不为空则修改这条问题
     */
    private String examId;

    /**
     * 类型为整型，1表示正确答案，0表示错误答案
     */
    private int right;

    /**
     * //设置是否可以跳过问答，true表示可以跳过，不添加这个参数默认没有跳过的功能
     */
    private boolean skip;


    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getWrongShow() {
        return wrongShow;
    }

    public void setWrongShow(Integer wrongShow) {
        this.wrongShow = wrongShow;
    }

    public Integer getWrongTime() {
        return wrongTime;
    }

    public void setWrongTime(Integer wrongTime) {
        this.wrongTime = wrongTime;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    @Override
    public String toString() {
        return "Question{" +
                ", vid='" + vid + '\'' +
                ", seconds=" + seconds +
                ", question='" + question + '\'' +
                ", answerList=" + answerList +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", answer='" + answer + '\'' +
                ", wrongShow=" + wrongShow +
                ", wrongTime=" + wrongTime +
                ", examId='" + examId + '\'' +
                ", right=" + right +
                ", skip=" + skip +
                '}';
    }
}
