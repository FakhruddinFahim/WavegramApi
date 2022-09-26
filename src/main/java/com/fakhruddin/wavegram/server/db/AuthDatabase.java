package com.fakhruddin.wavegram.server.db;

import com.fakhruddin.mtproto.AuthKey;
import com.fakhruddin.wavegram.Config;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class AuthDatabase extends Database {
    private static final String TAG = AuthDatabase.class.getSimpleName();
    public static final String TBL_NAME = "authorization";

    public AuthDatabase() {
        createTable();
    }

    public AuthDatabase(Connection connection) {
        super(connection);
        createTable();
    }

    private void createTable() {
        try {
            createDb(Config.MYSQL_DBNAME);
            String[] columnsDefinition = {
                    "`id` BIGINT(255) NOT NULL PRIMARY KEY AUTO_INCREMENT",
                    "`auth_id` BIGINT(255) NOT NULL",
                    "`auth_key` BLOB NOT NULL",
                    "`perm_auth_id` BIGINT(255) NULL DEFAULT 0",
                    "`expire_date` BIGINT(255) NULL DEFAULT -1",
                    "`user_id` BIGINT(255) DEFAULT NULL"
            };
            super.createTable(columnsDefinition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AuthKey getAuthKey(long authId) {
        AuthKey authKey = null;
        try (ResultSet resultSet = executeQuery("SELECT * FROM " + getTableName() + " WHERE auth_id = " + authId)){
            if (resultSet.next()) {
                authKey = new AuthKey();
                authKey.setAuthKeyId(resultSet.getLong("auth_id"));
                authKey.setAuthKey(resultSet.getBlob("auth_key").getBytes(1, 256));
                authKey.setExpireDate(resultSet.getInt("expire_date"));
                authKey.setType(authKey.getExpireDate() > 0 ? AuthKey.Type.TEMP_AUTH_KEY : AuthKey.Type.PERM_AUTH_KEY);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authKey;
    }

    public void setAuthKey(AuthKey authKey) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + getTableName() +
                    " (auth_id, auth_key, expire_date) VALUES("
                    + authKey.getAuthKeyId() + ", ?, " + authKey.getExpireDate() + ")");
            preparedStatement.setBlob(1, new ByteArrayInputStream(authKey.getAuthKey()));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loggedIn(long authId, long userId) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("user_id", userId);
        try {
            update(map, "auth_id = " + authId, null, "1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long getUserId(long authId) {
        try (ResultSet query = query("auth_id = " + authId, "1")){
            if (query.next()) {
                return query.getLong("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getTableName() {
        return TBL_NAME;
    }

    public boolean isAuthKeyExists(long authId) {
        String[] projection = {
                "1"
        };
        try (ResultSet query = query(projection, "auth_id = " + authId, null,
                null, null, "1")) {
            if (query.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteKey(long authId) {
        try {
            return 0 < delete("auth_id = " + authId, null, null, "1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
