package at.ylkstack.team.domain;

/**
 * @describe 架构师类
 * @author: ylkstack
 * @Date: 2021/6/3 23:24
 */
public class Architect extends Designer{
    private int stock;//股票数量

    public Architect() {
    }

    public Architect(int id, String name, int age, double salary,
                     Equipment equipment, double bonus, int stock) {
        super(id, name, age, salary, equipment, bonus);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public String getDetailsForTeam() {
        return getMemberDetails() + "\t架构师\t" +
                getBonus() + "\t" + getStock();
    }
    public String toString() {
        return getDetails() + "\t\t架构师\t" + getStatus() + "\t" +
                getBonus() +"\t\t" + stock + "\t" + getEquipment().getDescription();
    }
}
