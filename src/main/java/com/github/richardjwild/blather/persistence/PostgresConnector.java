package com.github.richardjwild.blather.persistence;

import com.github.richardjwild.blather.user.User;

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
        String insertUserStatement = "INSERT INTO public.\"User\" (\"Name\") VALUES (\'" + username + "\')";
        Connection conn = getConnection(host, user, password);

        PreparedStatement st = conn.prepareStatement(insertUserStatement);
        int affectedRows = st.executeUpdate();

        System.out.println(affectedRows);

        st.close();
        conn.close();
    }

    public User find_user(String name) {
        String insertUserStatement = "SELECT * FROM public.\"User\" WHERE \"User\".\"Name\" = \'" + name + "\'";

        String userName = null;
        try {
            Connection conn = null;
            conn = getConnection(host, user, password);
            PreparedStatement st = conn.prepareStatement(insertUserStatement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery();

            rs.first();

            userName = rs.getString(2);
            System.out.println(rs.getString(2));

            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        if(userName != null)
            return new User(userName);
        return null;
    }
}
