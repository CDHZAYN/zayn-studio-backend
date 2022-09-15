package cc.zayn.backend.controller;

import cc.zayn.backend.config.Response;
import cc.zayn.backend.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
public class BannerController {

    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService){
        this.bannerService = bannerService;
    }

    @GetMapping("/get")
    public ResponseEntity<Response> getBanner(){
        return new ResponseEntity<Response>(Response.res(bannerService.getBanner()), HttpStatus.OK);
    }

}
