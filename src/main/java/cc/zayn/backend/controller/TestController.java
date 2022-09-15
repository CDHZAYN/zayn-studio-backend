package cc.zayn.backend.controller;

import cc.zayn.backend.config.Response;
import cc.zayn.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/huh")
    public ResponseEntity<?> test(@RequestParam String id) {
        return new ResponseEntity<>(Response.res(testService.test(id)), HttpStatus.OK);
    }

}
