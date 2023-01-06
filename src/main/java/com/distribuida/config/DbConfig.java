package com.distribuida.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@ApplicationScoped
public class DbConfig {
    Jdbi jdbi = null;
    @Inject
    @ConfigProperty(name="db.user")
    String dbUser;
    @Inject
    @ConfigProperty(name="db.password")
    String dbPassword;
    @Inject
    @ConfigProperty(name="db.url")
    String dbUrl;

    @PostConstruct
public void init () {
  //  System.out.println("***************Post Construct********");

 //   Config config = ConfigProvider.getConfig();
 //   String user = config.getValue("db.user", String.class);
 //   String passwd = config.getValue("db.password", String.class);
 //   String url = config.getValue("db.url", String.class);
//No depende de CDI
 //   System.out.println("op1 : user:" + user);
 //   System.out.println("op1 : password:" + passwd);
 //   System.out.println("op1 : url:" + url);
//solamente con CDI
      //  System.out.println("op2: user:"+ dbUser);
        //System.out.println("op2: password:"+ dbPassword);
        //System.out.println("op2: url:"+ dbUrl);


}
    public void test() {

    }
    @Produces
    @ApplicationScoped
    public DataSource dataSource(){
        HikariDataSource ds = new HikariDataSource();
      ds.setDriverClassName("org.postgresql.Driver");
      ds.setJdbcUrl(dbUrl);
      ds.setUsername(dbUser);
      ds.setPassword(dbPassword);
      return ds;
    }
    @Produces
    public Jdbi jdbi(){
        DataSource bd = dataSource();
        return Jdbi.create(bd).installPlugin(new SqlObjectPlugin());
    }

    @ApplicationScoped
    @Produces
    public Handle test2(){
        jdbi = Jdbi.create(dbUrl,dbUser,dbPassword);
        return jdbi.open();

    }
    }


