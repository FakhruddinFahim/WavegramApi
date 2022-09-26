package com.fakhruddin.wavegram.server.db;

import com.fakhruddin.wavegram.Config;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
/**
 * Created by Fakhruddin Fahim on 22/07/2022
 */
public class Database {
    private static final String TAG = Database.class.getSimpleName();
    public static final String ENGINE = "MyISAM";
    public static final String CHARSET = "UTF8";
    public static final String URL = "jdbc:mysql://localhost:3306/" + Config.MYSQL_DBNAME + "?useSSL=false";

    protected Connection connection;
    protected Statement statement;
    private String tableName = null;

    public Database() {
        connection = getConnection();
        if (connection != null) {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Database(String tableName) {
        this.tableName = tableName;
        connection = getConnection();
        if (connection != null) {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Database(Connection connection) {
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, Config.MYSQL_USERNAME, Config.MYSQL_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int createDb(String name) throws SQLException {
        String query = "CREATE DATABASE IF NOT EXISTS " + name + ";";
        return executeUpdate(query);
    }

    public int createTable(String[] columns) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS " + getTableName() +
                " (" + String.join(", ", columns) + ");";
        return executeUpdate(query);
    }


    public int insert(LinkedHashMap<String, Object> columnValue) throws SQLException {
        String values = columnValue.values().stream().map(o -> {
            if (o instanceof Integer || o instanceof Long ||
                    o instanceof Float || o instanceof Double) {
                return o.toString();
            } else {
                return "\"" + o + "\"";
            }
        }).collect(Collectors.joining(", "));
        String query = "INSERT INTO " + getTableName() + " (" + String.join(", ", columnValue.keySet()) + ") " +
                "VALUES (" + values + ")";
        return executeUpdate(query);
    }

    public int insert(LinkedHashMap<String, Object> columnValue, String[] insertProjection, String[] projection,
                      String selection, String[] selectionArgs, String orderBy, String limit) throws SQLException {
        String values = columnValue.values().stream().map(o -> {
            if (o instanceof Integer || o instanceof Long ||
                    o instanceof Float || o instanceof Double) {
                return o.toString();
            } else {
                return "\"" + o + "\"";
            }
        }).collect(Collectors.joining(", "));

        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append(getTableName()).append(" (")
                .append(String.join(", ", insertProjection))
                .append(", ")
                .append(String.join(", ", columnValue.keySet()));
        if (projection != null) {
            query.append(") SELECT ")
                    .append(String.join(", ", projection))
                    .append(", ")
                    .append(String.join(", ", values))
                    .append(" FROM ")
                    .append(getTableName())
                    .append(" ");
            if (selection != null) {
                if (selectionArgs != null) {
                    for (String selectArg : selectionArgs) {
                        selection = selection.replaceFirst("\\?", selectArg);
                    }
                }
                query.append(" WHERE ").append(selection).append(" ");
            }
            if (orderBy != null) {
                query.append("ORDER BY ").append(orderBy).append(" ");
            }
            if (limit != null) {
                query.append("LIMIT ").append(limit).append(" ");
            }
        } else {
            query.append(") VALUES(").append(String.join(", ", values)).append(") ");
        }

        return executeUpdate(query.toString());
    }

    public int update(LinkedHashMap<String, Object> columnValue, String selection, String[] selectionArgs,
                      String limit) throws SQLException {
        String values = columnValue.entrySet().stream().map(o -> {
            if (o.getValue() instanceof Integer || o.getValue() instanceof Long ||
                    o.getValue() instanceof Float || o.getValue() instanceof Double) {
                return o.getKey() + " = " + o.getValue();
            } else {
                return o.getKey() + " = \"" + o.getValue() + "\"";
            }
        }).collect(Collectors.joining(", "));

        StringBuilder query = new StringBuilder("UPDATE " + getTableName() + " " +
                "SET ");
        query.append(values).append(" ");

        if (selection != null) {
            if (selectionArgs != null) {
                for (String selectArg : selectionArgs) {
                    selection = selection.replaceFirst("\\?", selectArg);
                }
            }
            query.append("WHERE ").append(selection).append(" ");
        }

        if (limit != null) {
            query.append("LIMIT ").append(limit);
        }

        return executeUpdate(query.toString());
    }

    public int updateFullJoin(LinkedHashMap<String, Object> columnValue, String selection, String[] selectionArgs,
                              String limit, String[] joinTables, String[] joinSelection,
                              String[][] joinSelectionArgs) throws SQLException {
        return updateJoin(columnValue, selection, selectionArgs, limit, new String[]{"FULL"}, joinTables,
                joinSelection, joinSelectionArgs);
    }

    public int updateInnerJoin(LinkedHashMap<String, Object> columnValue, String selection, String[] selectionArgs,
                               String limit, String[] joinTables, String[] joinSelection,
                               String[][] joinSelectionArgs) throws SQLException {
        return updateJoin(columnValue, selection, selectionArgs, limit, new String[]{"INNER"}, joinTables,
                joinSelection, joinSelectionArgs);
    }

    public int updateLeftJoin(LinkedHashMap<String, Object> columnValue, String selection, String[] selectionArgs,
                              String limit, String[] joinTables, String[] joinSelection,
                              String[][] joinSelectionArgs) throws SQLException {
        return updateJoin(columnValue, selection, selectionArgs, limit, new String[]{"LEFT"}, joinTables,
                joinSelection, joinSelectionArgs);
    }

    public int updateRightJoin(LinkedHashMap<String, Object> columnValue, String selection, String[] selectionArgs,
                               String limit, String[] joinTables, String[] joinSelection,
                               String[][] joinSelectionArgs) throws SQLException {
        return updateJoin(columnValue, selection, selectionArgs, limit, new String[]{"RIGHT"}, joinTables,
                joinSelection, joinSelectionArgs);
    }


    public int updateJoin(LinkedHashMap<String, Object> columnValue, String selection, String[] selectionArgs,
                          String limit, String[] joinNames, String[] joinTables, String[] joinSelection,
                          String[][] joinSelectionArgs) throws SQLException {

        String values = columnValue.entrySet().stream().map(o -> {
            if (o.getValue() instanceof Integer || o.getValue() instanceof Long ||
                    o.getValue() instanceof Float || o.getValue() instanceof Double) {
                return o.getKey() + " = " + o.getValue();
            } else {
                return o.getKey() + " = \"" + o.getValue() + "\"";
            }
        }).collect(Collectors.joining(", "));

        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(getTableName()).append(" ");

        for (int i = 0; i < joinTables.length; i++) {
            query.append(i <= joinNames.length - 1 ? joinNames[i] : joinNames[joinNames.length - 1]).append(" JOIN ").append(joinTables[i]);
            if (joinSelection != null) {
                query.append(" ON ").append(makeSelection(joinSelection[i], joinSelectionArgs != null ? joinSelectionArgs[i] : null)).append(" ");
            }
        }

        query.append(" SET ").append(values).append(" ");

        if (selection != null) {
            query.append("WHERE ").append(makeSelection(selection, selectionArgs)).append(" ");
        }

        if (limit != null) {
            query.append("LIMIT ").append(limit);
        }
        return executeUpdate(query.toString());
    }

    public int delete(String selection, String[] selectionArgs, String orderBy, String limit) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ").append(getTableName());
        if (selection != null) {
            if (selectionArgs != null) {
                for (String selectArg : selectionArgs) {
                    selection = selection.replaceFirst("\\?", selectArg);
                }
            }
            query.append(" WHERE ").append(selection);
        }
        if (orderBy != null) {
            query.append(" ORDER BY ").append(orderBy);
        }
        if (limit != null) {
            query.append(" LIMIT ").append(limit);
        }
        return executeUpdate(query.toString());
    }

    public String getTableName() {
        return tableName;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        return statement.executeUpdate(query);
    }

    public boolean execute(String query) throws SQLException {
        return statement.execute(query);
    }

    public ResultSet query(String selection, String limit) throws SQLException {
        return query(null, selection, null, null, null, limit);
    }

    public ResultSet query(String[] projection, String selection, String[] selectionArgs, String groupBy, String orderBy,
                           String limit) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT ");
        if (projection != null) {
            query.append(String.join(", ", projection)).append(" ");
        } else {
            query.append("* ");
        }

        query.append("FROM ").append(getTableName()).append(" ");

        if (selection != null) {
            if (selectionArgs != null) {
                for (String selectArg : selectionArgs) {
                    selection = selection.replaceFirst("\\?", selectArg);
                }
            }
            query.append("WHERE ").append(selection).append(" ");
        }

        if (groupBy != null) {
            query.append("GROUP BY ").append(groupBy).append(" ");
        }

        if (orderBy != null) {
            query.append("ORDER BY ").append(orderBy).append(" ");
        }
        if (limit != null) {
            query.append("LIMIT ").append(limit);
        }
        return executeQuery(query.toString());
    }

    public ResultSet queryFullJoin(String[] projection, String selection, String[] selectionArgs, String groupBy,
                                   String orderBy, String limit, String[] joinTables, String[] joinSelection,
                                   String[][] joinSelectionArgs) throws SQLException {
        return queryJoin(projection, selection, selectionArgs, groupBy, orderBy, limit, new String[]{"FULL"},
                joinTables, joinSelection, joinSelectionArgs);
    }

    public ResultSet queryRightJoin(String[] projection, String selection, String[] selectionArgs, String groupBy,
                                    String orderBy, String limit, String[] joinTables, String[] joinSelection,
                                    String[][] joinSelectionArgs) throws SQLException {
        return queryJoin(projection, selection, selectionArgs, groupBy, orderBy, limit, new String[]{"RIGHT"},
                joinTables, joinSelection, joinSelectionArgs);
    }

    public ResultSet queryLeftJoin(String[] projection, String selection, String[] selectionArgs, String groupBy,
                                   String orderBy, String limit, String[] joinTables, String[] joinSelection,
                                   String[][] joinSelectionArgs) throws SQLException {
        return queryJoin(projection, selection, selectionArgs, groupBy, orderBy, limit, new String[]{"LEFT"},
                joinTables, joinSelection, joinSelectionArgs);
    }

    public ResultSet queryInnerJoin(String[] projection, String selection, String[] selectionArgs, String groupBy,
                                    String orderBy, String limit, String[] joinTables, String[] joinSelection,
                                    String[][] joinSelectionArgs) throws SQLException {
        return queryJoin(projection, selection, selectionArgs, groupBy, orderBy, limit, new String[]{"INNER"},
                joinTables, joinSelection, joinSelectionArgs);
    }

    public ResultSet queryJoin(String[] projection, String selection, String[] selectionArgs, String groupBy, String orderBy,
                               String limit, String[] joinNames, String[] joinTables, String[] joinSelection,
                               String[][] joinSelectionArgs) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT ");
        if (projection != null) {
            query.append(String.join(", ", projection)).append(" ");
        } else {
            query.append("* ");
        }

        query.append("FROM ").append(getTableName()).append(" ");

        for (int i = 0; i < joinTables.length; i++) {
            query.append(i <= joinNames.length - 1 ? joinNames[i] : joinNames[joinNames.length - 1]).append(" JOIN ").append(joinTables[i]);
            if (joinSelection != null) {
                query.append(" ON ").append(makeSelection(joinSelection[i], joinSelectionArgs != null ? joinSelectionArgs[i] : null)).append(" ");
            }
        }

        if (selection != null) {
            query.append("WHERE ").append(makeSelection(selection, selectionArgs)).append(" ");
        }

        if (groupBy != null) {
            query.append("GROUP BY ").append(groupBy).append(" ");
        }
        if (orderBy != null) {
            query.append("ORDER BY ").append(orderBy).append(" ");
        }
        if (limit != null) {
            query.append("LIMIT ").append(limit);
        }
        return executeQuery(query.toString());
    }

    public ResultSet queryUnion(String[] tables, String[][] projections, String[] selections,
                                String[][] selectionArgs,

                                String[][] joinNames, String[][] joinTables, String[][] joinSelection,
                                String[][] joinSelectionArgs,

                                String[] groupsBy, String[] ordersBy,
                                String[] limits,

                                String groupBy, String orderBy, String limit) throws SQLException {

        StringBuilder[] queries = new StringBuilder[tables.length];
        for (int i = 0; i < tables.length; i++) {
            StringBuilder query = new StringBuilder();
            query.append("(SELECT ");
            if (projections[i] != null) {
                query.append(String.join(", ", projections[i])).append(" ");
            } else {
                query.append(tables[i]).append(".* ");
            }
            query.append("FROM ").append(tables[i]).append(" ");

            if (joinTables != null && joinTables[i] != null) {
                for (int j = 0; j < joinTables[i].length; j++) {
                    query.append(j <= joinNames[i].length - 1 ? joinNames[i][j] : joinNames[i][joinNames.length - 1]).append(" JOIN ").append(joinTables[i][j]);
                    if (joinSelection != null) {
                        query.append(" ON ").append(makeSelection(joinSelection[i][j], joinSelectionArgs != null ? joinSelectionArgs[j] : null)).append(" ");
                    }
                }
            }

            if (selections != null && selections[i] != null) {
                query.append("WHERE ").append(makeSelection(selections[i],
                        selectionArgs == null ? null : selectionArgs[i])).append(" ");
            }
            if (groupsBy != null && groupsBy[i] != null) {
                query.append("GROUP BY ").append(groupsBy[i]).append(" ");
            }
            if (ordersBy != null && ordersBy[i] != null) {
                query.append("ORDER BY ").append(ordersBy[i]).append(" ");
            }
            if (limits != null && limits[i] != null) {
                query.append("LIMIT ").append(limits[i]);
            }
            query.append(") ");
            queries[i] = query;
        }

        StringBuilder allQuery = new StringBuilder();
        allQuery.append("(").append(String.join("UNION ", queries)).append(")");
        if (groupBy != null) {
            allQuery.append(" GROUP BY ").append(groupBy);
        }
        if (orderBy != null) {
            allQuery.append(" ORDER BY ").append(orderBy);
        }
        if (limit != null) {
            allQuery.append(" LIMIT ").append(limit);
        }
        return executeQuery(allQuery.toString());
    }

    private String makeSelection(String selection, String[] selectionArgs) {
        if (selectionArgs != null) {
            for (String arg : selectionArgs) {
                selection = selection.replaceFirst("\\?", arg);
            }
        }
        return selection;
    }

    public void close() throws SQLException {
        connection.close();
    }

}
