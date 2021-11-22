package at.ylkstack.team.domain;

import at.ylkstack.team.service.Status;

/**
 * @describe 程序员类
 * @author: ylkstack
 * @Date: 2021/6/3 22:18
 */
public class Programmer extends Employee{
    private int memberld;//开发团队中的ID   TID
    private Status status = Status.FREE;//工作状态
    private Equipment equipment;//领用设备

    public Programmer() {
    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getMemberld() {
        return memberld;
    }

    public void setMemberld(int memberld) {
        this.memberld = memberld;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
    /**
     * 获得成员详情
     * Description: 获得成员详情
     * @Date 2021/6/3 22:41
     * @param
     * @return 返回成员信息
     */
    protected String getMemberDetails(){
        return getMemberld() + "\t" + getDetails();
    }
    /**
     * 获取团队详细信息
     * @Date 2021/6/3 22:36
     * @param
     * @return 成员详情
     */
    public String getDetailsForTeam(){
        return getMemberDetails() + "\t程序员";
    }

    @Override
    public String toString() {
        return getDetails() + "\t\t程序员\t" + status + "\t\t\t\t\t\t" + equipment.getDescription();
    }
}
