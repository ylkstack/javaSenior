package at.ylkstack.team.service;

/**
 * @describe  表示员工的状态
 * @author: ylkstack
 * @Date: 2021/6/3 22:30
 */
//public class Status {
//    private final String NAME;
//    private Status(String name) {
//        this.NAME = name;
//    }
//    public static final Status FREE = new Status("FREE");
//    public static final Status VOCATION = new Status("VOCATION");
//    public static final Status BUSY = new Status("BUSY");
//    public String getNAME() {
//        return NAME;
//    }
//    @Override
//    public String toString() {
//        return NAME;
//    }
//}

public enum Status{
    FREE,BUSY,VOCATION;
}