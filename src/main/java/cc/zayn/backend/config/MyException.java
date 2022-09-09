package cc.zayn.backend.config;

import java.util.HashMap;
import java.util.Map;

public class MyException extends RuntimeException{

    private final String code;

    public MyException(String code){
        super(map.get(code));
        this.code = code;
    }

    private static final Map<String, String> map = new HashMap<String, String>(){{
        put("0001", "something went wrong...");
    }};

    public String getCode(){
        return this.code;
    }

    public String getMsg(){
        return map.get(this.code);
    }

    @Override
    public String toString(){
        return code+":"+getMsg();
    }

}
