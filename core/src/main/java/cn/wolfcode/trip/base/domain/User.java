package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * 注册用户
 */
@Setter
@Getter

public class User extends BaseDomain{

    public static final int MAN = 1;//男
    public static final int WOMEN = 0;//女
    public static final int SECRET = -1;//保密

    //邮箱
    private String email;
    //昵称
    private String nickName;
    //密码
    private String password;
    //地区
    private String place;
    //头像
    private String headImgUrl;
    //性别
    private Integer gender = SECRET;
    //封面
    private String coverImgUrl;
    //签名
    private String sign;


    public String getGenderName(){
        String temp = "保密";
        if(this.gender==MAN){
            temp = "男";
        }else if(this.gender==WOMEN){
            temp = "女";
        }
        return temp;
    }

    /**
     * id,nickName,headImgUrl相同才认为是同一对象
     * @param o
     * @return
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nickName, user.nickName) &&
                Objects.equals(headImgUrl, user.headImgUrl)&&
                Objects.equals(id, user.id);
    }
    public int hashCode() {
        return Objects.hash(id,nickName, headImgUrl);
    }
}