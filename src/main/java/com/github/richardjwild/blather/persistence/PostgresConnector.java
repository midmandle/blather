package com.github.richardjwild.blather.persistence;

import com.github.richardjwild.blather.user.User;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class PostgresConnector {
    private String host;
    private String user;
    private String password;
    private Connection conn;

    public PostgresConnector(String host, String user, String password) {
        this.host = host;
        this.user = user;
        this.password = password;
        conn = null;
    }

    public void add_user(String username) throws SQLException {
        String insertUserStatement = "INSERT INTO public.\"User\" (\"Name\") VALUES (\'" + username + "\')";
        establishConnection();

        if(find_user(username) == null) {
            PreparedStatement st = conn.prepareStatement(insertUserStatement);
            st.executeUpdate();
            st.close();
        }

        closeConnection();
    }

    public User find_user(String name) throws SQLException {
        String insertUserStatement = "SELECT * FROM public.\"User\" WHERE \"User\".\"Name\" = \'" + name + "\'";

        String userName = null;
        establishConnection();
        PreparedStatement st = conn.prepareStatement(insertUserStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = st.executeQuery();

        if(rs.next() == false) {
            return null;
        }

        rs.first();

        userName = rs.getString(2);

        st.close();
        closeConnection();


        if(userName != null)
            return new User(userName);
        return null;
    }

    private void closeConnection() throws SQLException {
        if(!conn.isClosed() || conn == null)
            conn.close();
    }

    private void establishConnection() throws SQLException {
        if(conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(host, user, password);
        }
    }
}
