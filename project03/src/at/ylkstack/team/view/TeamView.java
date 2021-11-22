package at.ylkstack.team.view;

import at.ylkstack.team.domain.Employee;
import at.ylkstack.team.domain.Programmer;
import at.ylkstack.team.service.NameListService;
import at.ylkstack.team.service.TeamException;
import at.ylkstack.team.service.TeamService;
import org.w3c.dom.NameList;

/**
 * @Describe：TODO
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/7 20:56
 */
public class TeamView {
    private NameListService listSvc = new NameListService();//供类中的方法使用
    private TeamService teamSvc = new TeamService();

    /**
     * 主界面显示及控制方法。
     * @Description TODO
     * @author ylkstack
     * @Date 2021/6/7 21:00
     *@return void
     */
    public void enterMainMenu(){
        boolean loopFlag = true;//loop循环 loopFlag循环标签
        char menu = 0;
        while (loopFlag){
            if (menu != '1') {
                listAllEmployees();
            }
            System.out.print("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
            menu = TSUtility.readMenuSelection();
            switch (menu){
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.print("确认是否退出（Y/N）：");
                    char isExit = TSUtility.readConfirmSelection();
                    if (isExit == 'Y') {
                        loopFlag = false;
                    }
                    break;
            }
        }
    }

    /**
     * 以表格形式列出公司所有成员
     * @Description TODO
     * @author ylkstack
     * @Date 2021/6/7 21:01
     *@return void
     */
    private void listAllEmployees(){
//        System.out.println("显示公司所有的员工信息");
        System.out.println("-------------------------------开发团队调度软件--------------------------------");
        Employee[] employees = listSvc.getAllEmployees();
        if (employees == null || employees.length == 0){//严密点写法null和length都写
            System.out.println("公司中没有任何员工信息");
        }else {
            System.out.println("ID\t姓名\t\t年龄\t工资\t\t\t职位\t\t状态\t\t奖金\t\t\t股票\t\t领用设备");

            for (int i = 0; i < employees.length; i++) {
                System.out.println(employees[i]);
            }
        }
        System.out.println("-------------------------------------------------------------------------------");

    }
    /**
     * 显示团队成员列表操作
     * @Description TODO
     * @author ylkstack
     * @Date 2021/6/7 21:02
     * @param
     *@return void
     */
    private void getTeam(){
        System.out.println("--------------------团队成员列表---------------------");
        Programmer[] team = teamSvc.getTeam();
        if (team == null || team.length == 0){
            System.out.println("开发团队目前没有成员！");
        }else{
                System.out.println("TID/ID\t姓名\t\t年龄\t工资\t\t职位\t\t奖金\t\t股票");
            for (int i = 0; i < team.length; i++) {
                System.out.println(team[i].getDetailsForTeam());
            }
        }
        System.out.println("-----------------------------------------------------");
    }

    /**
     * 实现添加成员操作
     * @Description TODO
     * @author ylkstack
     * @Date 2021/6/7 21:02
     * @param
     *@return void
     */
    private void addMember(){
        System.out.println("---------------------添加成员---------------------");
        System.out.print("请输入要添加的员工ID：");
        int id = TSUtility.readInt();
        Employee emp = null;
        try {
            emp = listSvc.getEmployee(id);
            teamSvc.addMember(emp);
            System.out.println("添加成功");
        } catch (TeamException e) {
            System.out.println("添加失败，原因：" +e.getMessage());
        }
        //按回车键继续
        TSUtility.readReturn();
    }

    /**
     * 实现删除成员操作
     * @Description TODO
     * @author ylkstack
     * @Date 2021/6/7 21:02
     * @param
     *@return void
     */
    private void deleteMember(){
        System.out.println("---------------------删除成员---------------------");
        System.out.print("请输入要删除员工的TID：");
        int memberId = TSUtility.readInt();

        System.out.print("确认是否删除(Y/N)：");
        char isDelete = TSUtility.readConfirmSelection();
        if (isDelete == 'N'){
            return;
        }

        try {
            teamSvc.removeMember(memberId);
            System.out.println("删除成功");
        } catch (TeamException e) {
            System.out.println("删除失败，原因：" + e.getMessage());
        }
        //按回车继续
        TSUtility.readReturn();
    }

    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        teamView.enterMainMenu();
    }
}
