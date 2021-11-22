package at.ylkstack.team.service;

import at.ylkstack.team.domain.Architect;
import at.ylkstack.team.domain.Designer;
import at.ylkstack.team.domain.Employee;
import at.ylkstack.team.domain.Programmer;

/**
 * @Describe：TODO
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/6 23:02
 */
public class TeamService {
    private static int counter = 1;//用来为memberId赋值使用
    private static final int MAX_MEMBER = 5;//限制开发团队人数
    private Programmer[] team = new Programmer[MAX_MEMBER];//用来保存当前团队中的各成员对象
    private int total = 0;//记录团队成员的实际人数

    public TeamService(){
    }

    /**
     * 返回team中所有程序员构成的数组
     * @Description TODO 显示团队列表
     * @Date 2021/6/6 23:15
     * @param
     *@return at.ylkstack.team.domain.Programmer[]
     */
    public Programmer[] getTeam() {
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < team.length; i++) {
            team[i] = this.team[i];//this后面的team是属性，前面的是方法内声明的team
        }
        return team;
    }

    /**
     * 添加指定员工到团队中
     * @Description 将指定的员工添加到开发团队中
     * @Date 2021/6/6 23:13
     * @param e 输入需要添加的对象
     *@return void
     */
    public void addMember(Employee e) throws TeamException{
//        失败信息包含以下几种：
//        成员已满，无法添加
        if (total >= MAX_MEMBER){
            throw new TeamException("成员已满，无法添加");
        }
//        该成员不是开发人员，无法添加
        if (!(e instanceof Programmer)){
            throw new TeamException("该成员不是开发人员，无法添加");
        }
//        该员工已在本开发团队中
        if (isExist(e)){
         throw new TeamException("该员工已在本开发团队中");
        }
//        该员工已是某团队成员
//        该员正在休假，无法添加
        Programmer p = (Programmer)e;//一定不会出现ClassCastException异常
//        if ("BUSY".equalsIgnoreCase(p.getStatus().getNAME())) {//第二种方法 if (p.getStatus().getNAME().equals("BUSY"));
//            throw new TeamException("该员工已是某团队成员");
//        }else if("VOCATION".equalsIgnoreCase(p.getStatus().getNAME())){
//            throw new TeamException("该员正在休假，无法添加");
//        }
        //使用枚举类后的写法
        switch(p.getStatus()){//byte\short\char\int\String\枚举类对象
            case BUSY:
                throw new TeamException("该员工已是某团队成员");
            case VOCATION:
                throw new TeamException("该员正在休假，无法添加");
        }
//        团队中至多只能有一名架构师
//        团队中至多只能有两名设计师
//        团队中至多只能有三名程序员

        //做以上问题时,要获取team已有成员中架构师、设计师、程序员的人数
        int numOfArch = 0, numOfDes = 0,numOfPro = 0;
        for (int i = 0; i < total; i++) {
            if (team[i] instanceof Architect){
                numOfArch++;
            }else if (team[i] instanceof Designer){
                numOfDes++;
            }else if (team[i] instanceof Programmer){
                numOfPro++;
            }
        }
        if (p instanceof Architect){
            if (numOfArch >= 1){
                throw new TeamException("团队中至多只能有一名架构师");
            }
        }else if (p instanceof Designer){
            if (numOfDes >= 2){
                throw new TeamException("团队中至多只能有两名设计师");
            }
        }else if (p instanceof Programmer){
            if (numOfPro >= 3){
                throw new TeamException("团队中至多只能有三名程序员");
            }
        }

        //如果上面都通过，则下面。将P（或e）添加到现有的team中
        p.setStatus(Status.BUSY);
        p.setMemberld(counter++);
        //P的属性赋值
        team[total++] = p;

    }
    /**
     * @Description 判断员工是否已在数组中
     * @Date 2021/6/6 23:29
     * @param e
     *@return boolean
     */
    private boolean isExist(Employee e){
        for (int i = 0; i < total; i++) {
            if (team[i].getId() == e.getId())
                return true;
        }
        return false;
    }
    /**
     * 删除指定memberId的程序员
     * @Description TODO
     * @Date 2021/6/6 23:13
     * @param memberId  输入需要删除团队人员的 memberId
     *@return void
     */
    public void removeMember(int memberId) throws TeamException{
        int i = 0;
        //找到指定memberId的员工，并删除
        for (; i < total; i++) {
            if (team[i].getMemberld() == memberId) {
                team[i].setStatus(Status.FREE);
                break;
            }
        }

        //如果遍历一遍，都找不到，则报异常
        if (i == total){
            throw new TeamException("删除失败，原因：找不到该成员，无法删除");
        }
        //后一个元素覆盖前一个元素，实现删除操作
        for (int j = i + 1; j < total; j++) {
            team[j - 1] = team[j];
        }
        //写法一：
//        team[total - 1] = null;
//        total--;
        //写法二：
        team[--total] = null;

    }
}
