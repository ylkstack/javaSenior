package at.ylkstack.team.junit;

import at.ylkstack.team.domain.Employee;
import at.ylkstack.team.service.NameListService;
import at.ylkstack.team.service.TeamException;
import org.junit.Test;

/**
 * 对NameListService类的测试
 * @Describe：
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/6 21:26
 */
public class NameListServiceTest {
    @Test
    public void testGetAllEmployees(){
        NameListService service = new NameListService();
        Employee[] employees = service.getAllEmployees();
        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }
    }

    @Test
    public void testGetEmployee(){
        NameListService service = new NameListService();
        int id = 10;
        try {
            Employee employee = service.getEmployee(id);
            System.out.println(employee);
        } catch (TeamException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
