package org.example.util;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public final class DataSourceProvider {
    public static DataSource dataSource()  {
        Properties props = new Properties();
        try {
            props.load(new FileReader("../database.properties"));
        } catch (IOException e) {
            // Unreachable
            e.printStackTrace();
        }
        String databaseType = System.getenv("DATABASE_TYPE") == null
                ? "postgres" : System.getenv("DATABASE_TYPE");

        return getDataSource(props.getProperty(databaseType+".datasource.url")
        ,props.getProperty(databaseType+".datasource.username"),
                props.getProperty(databaseType+".datasource.password"));
    }

    public static DataSource getDataSource(final String url
            ,final String userName
            ,final String password) {
        if(url.startsWith("jdbc:postgresql:")) {
            PGSimpleDataSource ds = new PGSimpleDataSource();
            ds.setURL(url);
            ds.setUser(userName);
            ds.setPassword(password);
            return ds;
        }
        if(url.startsWith("jdbc:mysql:")) {
            MysqlDataSource mySQLDataSource = new MysqlDataSource();
            mySQLDataSource.setUrl(url);
            mySQLDataSource.setUser(userName);
            mySQLDataSource.setPassword(password);
            return mySQLDataSource;
        }
        return null;
    }
}
