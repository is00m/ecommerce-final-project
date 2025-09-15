package com.iso.ecommerce.dao;

import com.iso.ecommerce.dao.constants.SqlScriptConstants;
import com.iso.ecommerce.model.Category;
import com.iso.ecommerce.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoryDAO implements BaseDAO<Category> {
    @Override
    public void save(Category category) {

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstants.CATEGORY_SAVE)){

            ps.setString(1,category.getName());
            ps.setLong(2,category.getCreatedUser().getId());
            ps.setLong(3,category.getUpdatedUser().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Category findById(long id) {
        return null;
    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(long id) {

    }
}
