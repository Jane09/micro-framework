package framework.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCode {
    INVALID_USER(40101),
    INVALID_PWD(40001),
    INVALID_CLIENT(40301),
    FORBIDDEN_CLIENT(40331),
    ERROR_TOKEN(40101),
    FORBIDDEN_TOKEN(40301),
    SYSTEM(500)
    ;
    private int code;
}
