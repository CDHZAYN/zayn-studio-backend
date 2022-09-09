package cc.zayn.backend.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<Response> handleAppException(MyException exception) {
        return new ResponseEntity<Response>(Response.res(exception.getCode(), exception.getMsg()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleAppExceptionForAll(Exception e) {
        return new ResponseEntity<Response>(Response.res("ZZZZZ", "fatal Error:" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
