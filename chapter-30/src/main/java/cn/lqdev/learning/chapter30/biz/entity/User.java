package cn.lqdev.learning.chapter30.biz.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体
 * @author oKong
 *
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
//@Table(name="CUSTOM_USER")//自定义表名
public class User implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -3752294262021766827L;
	/**
     * 唯一标示
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 创建时间
     */
    @CreatedDate //自动创建
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @LastModifiedDate //有修改时 会自动时间
    private Date gmtModified;
}
