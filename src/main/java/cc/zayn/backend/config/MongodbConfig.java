package cc.zayn.backend.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongodbConfig {

    @Value("${db-user}")
    private String dbUser;

    @Value("${db-pwd}")
    private String dbPwd;

    @Value("${db-server}")
    private String dbServer;

    @Value("${db-port}")
    private String dbPort;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://"+dbUser+":"+dbPwd+"@"+dbServer+":"+dbPort+"/studio-database");
//        return MongoClients.create("mongodb://" + dbUser + ":" + dbPwd + "@" + dbServer + ":" + dbPort );
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "studio-database");
    }
}