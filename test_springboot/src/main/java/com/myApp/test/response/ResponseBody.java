package com.myApp.test.response;

import com.myApp.test.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBody {
    private int code;
    private Object data;
    private String message;
    private User user;

    public ResponseBody(int i, Serializable serializable) {
    }

    public ResponseBody(int code, User user) {
        this.code = code;
        this.user = user;
    }
}