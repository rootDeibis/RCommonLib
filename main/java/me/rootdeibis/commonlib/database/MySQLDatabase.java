package me.rootdeibis.commonlib.database;

import com.zaxxer.hikari.HikariConfig;

public class MySQLDatabase extends SQLDatabase {


    private static final String JDBC_PROTOCOL = "jdbc:";
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String MARIADB_DRIVER = "org.mariadb.jdbc.Driver";

    public MySQLDatabase(String driver,String host, String port, String database_name, String username, String password) {

        HikariConfig config = new HikariConfig();

        if (driver.equalsIgnoreCase("mariadb")) {
            config.setDriverClassName(MARIADB_DRIVER);
        } else if(driver.equalsIgnoreCase("mysql")) {
            config.setDriverClassName(MYSQL_DRIVER);
        }

        config.setUsername(username);
        config.setPassword(password);

        config.addDataSourceProperty("paranoid", true);

        config.setJdbcUrl(JDBC_PROTOCOL + driver + "://" + host + ':' + port + '/' + database_name);



        SQLDatabase.addPerformanceParams(config);


        this.setConfig(config);
        this.loadDataSource();


    }


}
