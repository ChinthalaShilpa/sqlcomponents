package org.sqlcomponents.core.crawler;

import org.junit.jupiter.api.Test;
import org.sqlcomponents.core.exception.ScubeException;
import org.sqlcomponents.core.model.Application;
import org.sqlcomponents.core.model.relational.Database;

import java.sql.SQLException;


class CrawlerTest {

    @Test
    void getDatabase() throws SQLException, ScubeException {
        Application application = new Application();
        application.setName("Movie");
        application.setUrl("jdbc:postgresql://localhost:5432/moviedb");
        application.setUserName("moviedb");
        application.setPassword("moviedb");
        application.setSchemaName("moviedb");

        Database database = new Crawler().getDatabase(application);

        System.out.println("Crawled");
    }
}