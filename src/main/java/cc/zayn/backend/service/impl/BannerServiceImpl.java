package cc.zayn.backend.service.impl;

import cc.zayn.backend.dao.BannerDao;
import cc.zayn.backend.model.po.BannerPO;
import cc.zayn.backend.model.vo.BannerVO;
import cc.zayn.backend.service.BannerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    private final BannerDao bannerDao;

    @Autowired
    public BannerServiceImpl(BannerDao bannerDao){
        this.bannerDao = bannerDao;
    }

    @Override
    public List<BannerVO> getBanner() {
        List<BannerPO> bannerPOList = bannerDao.getBanner();
        List<BannerVO> bannerVOList = new ArrayList<>();
        for(BannerPO bannerPO : bannerPOList){
            BannerVO bannerVO = new BannerVO();
            BeanUtils.copyProperties(bannerPO, bannerVO);
            bannerVOList.add(bannerVO);
        }
        return bannerVOList;
    }

}
