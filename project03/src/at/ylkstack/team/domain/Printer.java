package at.ylkstack.team.domain;

/**
 * @describe 打印机类
 * @author: ylkstack
 * @Date: 2021/6/3 22:04
 */
public class Printer implements Equipment{
    private String name;//打印机名字
    private String type;//打印机类型

    public Printer(){
    }
    public Printer(String name,String type){
        super();
        this.name = name;
        this.type = type;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }


    /**
     * 显示打印机信息
     * @Date 2021/6/3 22:11
     * @param
     * @return 打印机信息
     */
    @Override
    public String getDescription() {
        return getName() + "(" + getType() + ")";
    }
}
