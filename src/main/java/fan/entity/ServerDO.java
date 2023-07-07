package fan.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 服务实体类
 *
 * @author Fan
 * @since 2023/7/7 10:54
 */
@Data
@TableName("server")
public class ServerDO {

    @TableId
    private String id;

    private String code;

    private String name;

    private String flag;

    private Timestamp createTime;

    private Timestamp updateTime;
}
