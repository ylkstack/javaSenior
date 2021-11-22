package at.ylkstack.team.service;

/**
 * @describe 自定义异常类
 * @author: ylkstack
 * @Date: 2021/6/4 22:36
 */
public class TeamException extends Exception {
    static final long serialVersionUID = -3387516993124229948L;

    public TeamException(){
        super();
    }
    public TeamException(String message){
        super(message);
    }
}
