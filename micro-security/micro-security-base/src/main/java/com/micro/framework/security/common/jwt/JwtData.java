package com.micro.framework.security.common.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author tb
 * @date 2019/1/2 10:55
 */
@Getter
@Setter
@AllArgsConstructor
public class JwtData implements IJwt, Serializable {

    private String username;
    private String userId;
    private String name;


    @Override
    public String getUniqueName() {
        return this.username;
    }

    @Override
    public String getId() {
        return this.userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JwtData jwtInfo = (JwtData) o;
        if (!Objects.equals(username, jwtInfo.username)) {
            return false;
        }
        return Objects.equals(userId, jwtInfo.userId);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
