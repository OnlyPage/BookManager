package com.example.bookManager.service.response;

import com.example.bookManager.domain.RoleDetail;
import com.example.bookManager.domain.UserDetail;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class UserDetailResponse
{
    private String username;
    private String phoneNumber;
    private String email;
    private Date birthday;
    private Date createTime;
    private String address;
    private String roleDetail;

    public UserDetailResponse(String username, String password, String phoneNumber, String email, Date birthday, Date createTime, String address, String roleDetail) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
        this.createTime = createTime;
        this.address = address;
        this.roleDetail = roleDetail;
    }

    public UserDetailResponse(UserDetail userDetail)
    {
        this.username = userDetail.getUsername();
        this.phoneNumber = userDetail.getPhoneNumber();
        this.email = userDetail.getEmail();
        this.createTime = userDetail.getCreateTime();
        this.address = userDetail.getAddress();
        this.roleDetail = userDetail.getRoleDetail().getRoleName();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoleDetail() {
        return roleDetail;
    }

    public void setRoleDetail(String roleDetail) {
        this.roleDetail = roleDetail;
    }
}
