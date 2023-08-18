package me.rootdeibis.commonlib.database;

import com.zaxxer.hikari.HikariConfig;
import org.sqlite.JDBC;

import java.io.File;

public class SQLiteDatabase extends SQLDatabase {

    private static final String SQLITE_DRIVER = "org.sqlite.SQLiteDataSource";
    public SQLiteDatabase(File dbFile) {
        try {

            if (!dbFile.exists())
                dbFile.createNewFile();

        }catch (Exception e) {
            e.printStackTrace();
        }



        HikariConfig config = new HikariConfig();

        config.setDataSourceClassName(SQLITE_DRIVER);

        config.setConnectionTestQuery("SELECT 1");
        config.setMaximumPoolSize(1);

        config.addDataSourceProperty("url", JDBC.PREFIX + dbFile);


        this.setConfig(config);

        this.loadDataSource();


    }
}
