package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import express.utils.Utils;

import java.sql.*;
import java.util.List;

public class Database {
    private Connection conn;

    public Database(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:todoey.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Items> getItems(){
        List<Items> items = null;

        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM items");
            ResultSet rs = stmt.executeQuery();

            Items[] itemsFromRS = (Items[])Utils.readResultSetToObject(rs,Items[].class);
            items = List.of(itemsFromRS);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void createItem (Items item){
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO items(title) VALUES(?)");
            stmt.setString(1,item.getTitle());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
