package com.fish.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-31 17:00
 */
@Entity
@Table(name="blog_comment")
public class CommentEntity implements Serializable {
    @Id
    @Column(name="CommentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="PostId")
    private Integer postId;
    @Lob
    @Column(name = "Content",columnDefinition="text")
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CommentTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    @Column(name="CommentIp")
    private String ip;
    @Column(name="p_id")
    private String pId;
    @Column(name = "UserId")
    private Integer userId;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "NickName")
    private String nickName;
    @Column(name = "Email")
    private String email;
    @Column(name = "Status")
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}
