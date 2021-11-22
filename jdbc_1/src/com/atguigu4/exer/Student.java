package com.atguigu4.exer;

/**
 Type:
 IDCard:
 ExamCard:
 StudentName:
 Location:
 Grade:
 */
public class Student {
    private int flowID;//流水号
    private int type;//考试类型
    private String IDCard;//身份证号
    private String examCard;//准考证号
    private String studentName;//学生姓名
    private String location;//所在城市
    private int grade;//成绩

    public Student(int flowID, int type, String IDCard,String examCord, String studentName, String location, int grade) {
        this.flowID = flowID;
        this.type = type;
        this.examCard = examCard;
        this.studentName = studentName;
        this.location = location;
        this.grade = grade;
        this.IDCard = IDCard;
    }

    @Override
    public String toString() {
        System.out.println("========查询结果========");
        return "流水号：" + flowID +
                "\n四级/六级：" + type +
                "\n身份证号：" + IDCard +
                "\n准考证号：" + examCard +
                "\n学生姓名：" + studentName +
                "\n区域：" + location +
                "\n成绩：" + grade
                ;
    }

    public Student() {
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public int getFlowID() {
        return flowID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getExamCord() {
        return examCard;
    }

    public void setExamCord(String examCord) {
        this.examCard = examCord;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
