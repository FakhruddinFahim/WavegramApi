package com.fakhruddin.wavegram.server.db;

import com.fakhruddin.mtproto.tl.MTProtoScheme;
import com.fakhruddin.wavegram.Config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SaltDatabase extends Database {
    public static final String TBL_NAME = "future_salt";

    public SaltDatabase() {
        createTable();
    }

    public SaltDatabase(Connection connection) {
        super(connection);
        createTable();
    }

    private void createTable() {
        try {
            createDb(Config.MYSQL_DBNAME);
            String[] columnsDefinition = {
                    "`id` BIGINT(255) NOT NULL PRIMARY KEY AUTO_INCREMENT",
                    "`auth_id` BIGINT(255) NOT NULL",
                    "`salt` BIGINT(255) NOT NULL",
                    "`valid_since` BIGINT(255) NOT NULL",
                    "`valid_until` BIGINT(255) NOT NULL"
            };
            createTable(columnsDefinition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTableName() {
        return TBL_NAME;
    }

    public List<MTProtoScheme.FutureSalt2> getSalts(long authId, int limit) {
        List<MTProtoScheme.FutureSalt2> futureSalts = new ArrayList<>();
        try (ResultSet resultSet = query(null, "auth_id = " + authId + " AND valid_until > " +
                        (System.currentTimeMillis() / 1000), null, null,
                "valid_since ASC", String.valueOf(limit))){
            while (resultSet.next()) {
                MTProtoScheme.FutureSalt2 futureSalt = new MTProtoScheme.FutureSalt2();
                futureSalt.salt = resultSet.getLong(3);
                futureSalt.validSince = resultSet.getInt(4);
                futureSalt.validUntil = resultSet.getInt(5);
                futureSalts.add(futureSalt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return futureSalts;
    }

    public void setSalts(long authId, List<MTProtoScheme.FutureSalt2> salts) {
        for (MTProtoScheme.FutureSalt2 futureSalt : salts) {
            LinkedHashMap<String, Object> columnValue = new LinkedHashMap<>();
            columnValue.put("auth_id", authId);
            columnValue.put("salt", futureSalt.salt);
            columnValue.put("valid_since", futureSalt.validSince);
            columnValue.put("valid_until", futureSalt.validUntil);
            try {
                insert(columnValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteExpiredSalt(long authId){
        String selection = "auth_id = " + authId + " AND " +
                " valid_until < " + (System.currentTimeMillis() / 1000);
        try {
            delete(selection, null, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
