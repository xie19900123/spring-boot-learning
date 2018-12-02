package cn.lqdev.learning.springboot.chapter35.biz.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author oKong
 * @since 2018-12-02
 */
@Data
@Accessors(chain = true)
public class User implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1779270373648636358L;
	/**
     * 唯一标示
     */
    private Long id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    
    /**
     * 状态1 启用 0 停用
     */
    private StatusEnum status;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
}
