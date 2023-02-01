package com.app.cfd.daos.utilities.set;

import com.app.cfd.models.quantity.Quantity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuantityMapper implements ColumnMapper<Quantity> {
    @Override
    public Quantity map(ResultSet result, int columnNumber, StatementContext ctx) throws SQLException {
        String quantity_as_string = result.getString("quantity");
        ObjectMapper object_mapper = new ObjectMapper();
        Quantity quantity;
        try {
            quantity = object_mapper.readValue(quantity_as_string, Quantity.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot Map Quantity, "+ quantity_as_string, e);
        }
        return quantity;
    }
}