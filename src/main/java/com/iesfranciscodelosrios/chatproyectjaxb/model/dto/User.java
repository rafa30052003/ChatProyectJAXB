package com.iesfranciscodelosrios.chatproyectjaxb.model.dto;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.Objects;


public class User {

    private String nickname;


    private Date entryTime;

    public User() {
    }

    public User(String nickname, Date entryTime) {
        this.nickname = nickname;
        this.entryTime = entryTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(nickname, user.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", entryTime=" + entryTime +
                '}';
    }
}
