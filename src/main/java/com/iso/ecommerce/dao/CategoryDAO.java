package com.iso.ecommerce.dao;

import com.iso.ecommerce.dao.constants.SqlScriptConstants;
import com.iso.ecommerce.model.Category;
import com.iso.ecommerce.model.Customer;
import com.iso.ecommerce.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements BaseDAO<Category> {
    @Override
    public void save(Category category) {

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CATEGORY_SAVE)) {

            ps.setString(1, category.getName());
            ps.setLong(2, category.getCreatedUser().getId());
            ps.setLong(3, category.getUpdatedUser().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Category findById(long id) {
        Category category = null;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CATEGORY_FIND_BY_ID)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    category = new Category();
                    category.setId(rs.getLong("id"));
                    category.setName(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             Statement s = connection.createStatement()) {
            try (ResultSet rs = s.executeQuery(SqlScriptConstants.CATEGORY_FIND_ALL)) {

                while (rs.next()) {
                    categories.add(new Category(rs.getLong("id"), rs.getString("name")));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(long id) {
        int effectedRowCount = 0;
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CATEGORY_DELETE)) {

            ps.setLong(1, id);
            effectedRowCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
