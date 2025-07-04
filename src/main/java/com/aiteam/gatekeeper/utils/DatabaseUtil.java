package com.aiteam.gatekeeper.utils;

public class DatabaseUtil {

    public static String buildJdbcUrl(String host, int port, String dbName) {
        return "jdbc:mysql://" + host + ":" + port + "/" + dbName +
                "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    }
}
