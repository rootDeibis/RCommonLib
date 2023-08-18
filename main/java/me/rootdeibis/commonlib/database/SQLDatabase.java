package me.rootdeibis.commonlib.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class SQLDatabase {

    private HikariConfig hikariConfig;

    private HikariDataSource dataSource;

    public SQLDatabase(HikariConfig config) {
        hikariConfig = config;

        loadDataSource();

    }

    public SQLDatabase() {

    }

    public void setConfig(HikariConfig config) {
        this.hikariConfig =config;
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }
    public void loadDataSource() {
        this.dataSource = new HikariDataSource(this.hikariConfig);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void addPerformanceParams(HikariConfig config) {
        // disabled by default - will return the same prepared statement instance
        config.addDataSourceProperty("cachePrepStmts", true);
        // default prepStmtCacheSize 25 - amount of cached statements
        config.addDataSourceProperty("prepStmtCacheSize", 250);
        // default prepStmtCacheSqlLimit 256 - length of SQL
        config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        // default false - available in newer versions caches the statements server-side
        config.addDataSourceProperty("useServerPrepStmts", true);
        // default false - prefer use of local values for autocommit and
        // transaction isolation (alwaysSendSetIsolation) should only be enabled if we always use the set* methods
        // instead of raw SQL
        // https://forums.mysql.com/read.php?39,626495,626512
        config.addDataSourceProperty("useLocalSessionState", true);
        // rewrite batched statements to a single statement, adding them behind each other
        // only useful for addBatch statements and inserts
        config.addDataSourceProperty("rewriteBatchedStatements", true);
        // cache result metadata
        config.addDataSourceProperty("cacheResultSetMetadata", true);
        // cache results of show variables and collation per URL
        config.addDataSourceProperty("cacheServerConfiguration", true);
        // default false - set auto commit only if not matching
        config.addDataSourceProperty("elideSetAutoCommits", true);

        // default true - internal timers for idle calculation -> removes System.getCurrentTimeMillis call per query
        // Some platforms are slow on this, it could affect the throughput about 3% according to MySQL
        // performance gems presentation
        // In our case it can be useful to see the time in error messages
        // config.addDataSourceProperty("maintainTimeStats", false);
    }





}
