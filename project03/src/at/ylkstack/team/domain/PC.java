package at.ylkstack.team.domain;

/**
 * @describe PC类
 * @author: ylkstack
 * @Date: 2021/6/3 21:49
 */
public class PC implements Equipment{
    private String model;//机器的型号
    private String display;//显示器名称

    public void setModel(String model){
        this.model =model;
    }
    public String getModel(){
        return model;
    }
    public void setDisplay(String display){
        this.display = display;
    }
    public String getDisplay(){
        return display;
    }
    public PC(){
    }
    public PC(String model,String display){
        super();
        this.model = model;
        this.display = display;
    }


    /**
     * 显示PC的信息
     * @Date 2021/6/3 22:10
     * @param
     * @return PC的信息
     */
    @Override
    public String getDescription() {
        return getModel() + "(" + getDisplay() + ")";
    }
}
