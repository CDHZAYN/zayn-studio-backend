package cc.zayn.backend.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class Response {

    private final String code;
    private final Object msg;

    public static Response res(String code, Object msg) {
        return new Response(code, msg);
    }

}
