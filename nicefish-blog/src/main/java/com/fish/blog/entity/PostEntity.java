package com.fish.blog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 大漠穷秋
 * @version 创建时间：2018-12-31 17:00
 */
@Entity
@Table(name="blog_post")
public class PostEntity {
    @Id
    @Column(name="PostId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="PostTitle")
    private String title;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="PostTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    @Lob
    @Column(name="PostContent",columnDefinition="text")
    private String content;
    @Column(name="PostSummary")
    private String summary;
    @Column(name="OriginalUrl")
    private String originalURL="";
    @Column(name="PostType")
    private Integer postType=0;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="LastModifyTime")
    private Date lastModifyTime=new Date();
    @Column(name = "ReadTimes")
    private Integer readTimes=1;
    @Column(name="LikedTimes")
    private Integer likedTimes=0;
    @Column(name="CommentTimes")
    private Integer commentTimes=0;
    @Column(name="UserId")
    private Integer userId;
    @Basic(optional=true)
    @Column(name="UserName")
    private String userName;
    @Column(name="NickName")
    private String nickName;
    @Column(name="EnableComment")
    private Integer enableComment=1;
    @Column(name="Status")
    private Integer status=4;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public Integer getPostType() {
        return postType;
    }

    public void setPostType(Integer postType) {
        this.postType = postType;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(Integer readTimes) {
        this.readTimes = readTimes;
    }

    public Integer getLikedTimes() {
        return likedTimes;
    }

    public void setLikedTimes(Integer likedTimes) {
        this.likedTimes = likedTimes;
    }

    public Integer getCommentTimes() {
        return commentTimes;
    }

    public void setCommentTimes(Integer commentTimes) {
        this.commentTimes = commentTimes;
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

    public Integer getEnableComment() {
        return enableComment;
    }

    public void setEnableComment(Integer enableComment) {
        this.enableComment = enableComment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
