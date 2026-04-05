package com.fakhruddin.wavegram.server.db;

import com.fakhruddin.mtproto.MTSession;
import com.fakhruddin.wavegram.Config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class SessionDatabase extends Database {
  private static final String TAG = SessionDatabase.class.getSimpleName();
  public static final String TBL_NAME = "session";


  public SessionDatabase() {
    createTable();
  }

  public SessionDatabase(Connection connection) {
    super(connection);
    createTable();
  }

  private void createTable() {
    try {
      createDb(Config.MYSQL_DBNAME);
      String[] columnsDefinition = {
        "`id` BIGINT(255) NOT NULL PRIMARY KEY AUTO_INCREMENT",
        "`auth_id` BIGINT(255) NOT NULL",
        "`session_id` BIGINT(255) NOT NULL",
        "`unique_id` BIGINT(255) NOT NULL",
        "`first_msg_id` BIGINT(255) NOT NULL",
        "`content_count` INT NOT NULL",
        "`other_party_last_seq_no` INT NOT NULL"
      };
      createTable(columnsDefinition);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public MTSession getSession(long authKeyId, long sessionId) {
    MTSession session = null;
    try {
      ResultSet resultSet = query("auth_id = " + authKeyId + " AND session_id = " + sessionId, "1");
      if (resultSet.next()) {
        session = new MTSession();
        session.sessionId = resultSet.getLong(3);
        session.uniqueId = resultSet.getLong(4);
        session.firstMessageId = resultSet.getLong(5);
        session.contentRelatedCount = resultSet.getInt(6);
        session.peerLastSeqNo = resultSet.getInt(7);
      }
      resultSet.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return session;
  }

  public boolean deleteSession(long authId, long sessionId) {
    try {
      return 0 < delete("auth_id = " + authId + " AND session_id = " + sessionId, null,
        null, "1");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public synchronized void setOrUpdateSession(long authId, MTSession session) {
    try (ResultSet resultSet = query("auth_id = " + authId + " AND unique_id = " + session.uniqueId, "1")) {
      if (resultSet.next()) {
        LinkedHashMap<String, Object> columnValue = new LinkedHashMap<>();
        columnValue.put("content_count", session.contentRelatedCount);
        columnValue.put("other_party_last_seq_no", session.peerLastSeqNo);
        String selection = "auth_id = " + authId + " AND unique_id = " + session.uniqueId;
        update(columnValue, selection, null, "1");
      } else {
        LinkedHashMap<String, Object> columnValue = new LinkedHashMap<>();
        columnValue.put("auth_id", authId);
        columnValue.put("session_id", session.sessionId);
        columnValue.put("unique_id", session.uniqueId);
        columnValue.put("first_msg_id", session.firstMessageId);
        columnValue.put("content_count", session.contentRelatedCount);
        columnValue.put("other_party_last_seq_no", session.peerLastSeqNo);
        insert(columnValue);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getTableName() {
    return TBL_NAME;
  }

}
