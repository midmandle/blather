package com.github.richardjwild.blather.persistence;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class PostgresConnector {
    private String host;
    private String user;
    private String password;

    public PostgresConnector(String host, String user, String password) {
        this.host = host;
        this.user = user;
        this.password = password;
    }

    public void add_user(String username) throws SQLException {
        String insertUserStatement = "INSERT INTO public.\"User\" (\"ID\", \"Name\") VALUES (1, \'" + username + "\')";

        Connection conn = getConnection(host, user, password);

        PreparedStatement st = conn.prepareStatement(insertUserStatement);
        int affectedRows = st.executeUpdate();

        System.out.println(affectedRows);

        st.close();
        conn.close();
    }
}
