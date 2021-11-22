package at.ylkstack.team.domain;

/**
 * @describe  笔记本类
 * @author: ylkstack
 * @Date: 2021/6/3 21:54
 */
public class NoteBook implements Equipment{
    private String model;//机器的型号
    private double price;//价格
    public NoteBook(){
    }
    public NoteBook(String model,double price){
        super();
        this.model = model;
        this.price = price;
    }
    public void setModel(String model){
        this.model = model;
    }
    public String getModel(){
        return model;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public double getPrice(){
        return price;
    }

    /**
     * 显示笔记本信息
     * @Date 2021/6/3 22:11
     * @param
     * @return 笔记本信息
     */
    @Override
    public String getDescription() {
        return getModel() + "(" + getPrice() + ")";
    }
}
