package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter
@Setter
public class HotelOrder extends BaseDomain{

    public static final int STATE_live = 1;//入住
    public static final int STATE_cancel = 0;//取消

    private User user;//用户

    private HotelRoomDetail hotelroomdetail;//酒店

    private Integer roomNum;//房间数

    private Integer peopleNum;//入住人数

    private String tel;//电话

    private Long price;//价格

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;//入住时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;//离店时间

    private Integer state = STATE_live;//状态

}