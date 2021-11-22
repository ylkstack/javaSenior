package at.ylkstack.team.service;

import at.ylkstack.team.domain.*;

import static at.ylkstack.team.service.Data.*;

/**
 * describe：
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/4 22:46
 */
public class NameListService {
    private Employee[] employees;//employees用来保存公司所有员工对象

    /**
     * 利用构造器 给employees及数组元素进行初始化
     */
    public NameListService() {
//        1.根据项目提供的Data类构建相应大小的employees数组
//        2.再根据Data类中的数据构建不同的对象，包括Employee、Programmer、Designer和Architect对象，
//        3.以及相关联的Equipment子类的对象
//                将对象存于数组中
        employees = new Employee[EMPLOYEES.length];//这样写因为上面导入了static data文件否则需要：Data.EMPLOYEES.length
        for (int i = 0; i < employees.length; i++) {
            //获取员工的类型
            int type = Integer.parseInt(EMPLOYEES[i][0]);
            /**
             * 获取Employee的四个基本信息
             */
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);

            Equipment equipment;//设备
            double bonus;//奖金
            int stock;//股票

            switch (type) {
                case EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    equipment = createEquipment(i);
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;
                case DESIGNER:
                    equipment = createEquipment(i);
                    bonus = Integer.parseInt(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case ARCHITECT:
                    equipment = createEquipment(i);
                    bonus = Integer.parseInt(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, equipment, bonus,
                            stock);
                    break;
            }
        }

    }


    private Equipment createEquipment(int index) {
        int key = Integer.parseInt(EQUIPMENTS[index][0]);
        String model = EQUIPMENTS[index][1];
        switch (key) {
            case PC://21
                String display = EQUIPMENTS[index][2];
                return new PC(model, display);
            case NOTEBOOK://22
                double price = Double.parseDouble(EQUIPMENTS[index][2]);
                return new NoteBook(model, price);
            case PRINTER://23
                String type = EQUIPMENTS[index][2];
                return new Printer(model, type);
        }
        return null;
    }

    /**
     * 获取当前所有的员工
     *
     * @param
     * @param i
     * @return at.ylkstack.team.domain.Employee[]
     * @Description:
     * @Date 2021/6/5 23:33
     */
    public Employee[] getAllEmployees(int i) {
        return employees;
    }

    /**
     * 获取指定ID的员工对象
     * @param id
     * @return at.ylkstack.team.domain.Employee
     * @Description: 获取指定ID的员工对象
     * @Date 2021/6/5 23:34
     */
    public Employee getEmployee(int id) throws TeamException {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == id) {
                return employees[i];
            }
        }
        throw new TeamException("找不到指定的员工");
    }

    public Employee[] getAllEmployees() {
        return employees;
    }
}
