package org.pi.Config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;


public class DBconfig {
    private static HikariDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            String host = "localhost";
            String port = "3306";
            String user = "root";
            String dbName = "ejemplo2";
            String username = "mariana";
            String password = "1234";
            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

            HikariConfig conf = new HikariConfig();
            conf.setJdbcUrl(url);
            conf.setUsername(user);
            conf.setPassword(password);
            conf.setDriverClassName("com.mysql.cj.jdbc.Driver");

            dataSource = new HikariDataSource(conf);
        }
        return dataSource;
    }

}