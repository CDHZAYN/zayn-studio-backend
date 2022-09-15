package cc.zayn.backend.dao;

import cc.zayn.backend.model.po.BannerPO;
import cc.zayn.backend.model.po.TestPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BannerDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public BannerDao(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    public List<BannerPO> getBanner(){
        Query query = new Query();
        return mongoTemplate.find(query, BannerPO.class, "banner");
    }

}
