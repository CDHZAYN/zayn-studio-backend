package cc.zayn.backend.dao;

import cc.zayn.backend.config.MyException;
import cc.zayn.backend.model.po.TestPO;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TestDao(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }
    public int test(){
        Query query = new Query(Criteria.where("_id").is(new ObjectId("631837f7af231421fe825617")));
        System.err.println(mongoTemplate.findOne(query,TestPO.class, "test"));
//        throw new MyException("0001");
        return mongoTemplate.findOne(query,TestPO.class, "test").getX();
    }

}
