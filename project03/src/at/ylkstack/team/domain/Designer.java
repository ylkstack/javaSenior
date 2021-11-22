package at.ylkstack.team.domain;

/**
 * @describe 设计师类
 * @author: ylkstack
 * @Date: 2021/6/3 22:43
 */
public class Designer extends Programmer{
    private double bonus;//奖金

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Designer() {
    }

    public Designer(int id, String name, int age, double salary,
                     Equipment equipment, double bonus) {
        super(id, name, age, salary,  equipment);
        this.bonus = bonus;
    }

    public String getDetailsForTeam(){
        return getMemberDetails() + "\t设计师\t" + getBonus();
   }

    @Override
    public String toString() {
        return getDetails() + "\t\t设计师\t" + getStatus() + "\t" +
                getBonus() +"\t\t\t\t" + getEquipment().getDescription();
    }
}
