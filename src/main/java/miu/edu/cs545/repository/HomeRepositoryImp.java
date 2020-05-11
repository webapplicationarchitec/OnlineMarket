package miu.edu.cs545.repository;

import miu.edu.cs545.domain.Product;
import miu.edu.cs545.domain.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HomeRepositoryImp implements HomeRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getAllProducts() {
        Map<String, Object> params = new HashMap<>();
        List<Product> result = jdbcTemplate.query("SELECT * from product", params, new ProductMapper());
        return result;
    }

    @Override
    public List<Product> getTopProducts() {
        return null;
    }

    @Override
    public List<Product> getFollowerProducts(List<Seller> sellerList) {
        return null;
    }

    private static final class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
//            product.setProductId(rs.getString("ID"));
//            product.setName(rs.getString("NAME"));
//            product.setDescription(rs.getString("DESCRIPTION"));
//            product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
//            product.setManufacturer(rs.getString("MANUFACTURER"));
//            product.setCategory(rs.getString("CATEGORY"));
//            product.setCondition(rs.getString("CONDITION"));
//            product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
//            product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
//            product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
            return product;
        }

    }



    @Override
    public List<Product> getProductsByCategory(String category) {
        Map<String, Object> params = new HashMap<>();
        params.put("category", category);
        List<Product> result = jdbcTemplate.query("SELECT * FROM PRODUCT WHERE CATEGORY = :category", params,
                new ProductMapper());
        return result;
    }

    @Override
    public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        List<Product> result = jdbcTemplate.query(
                "SELECT * FROM PRODUCT WHERE CATEGORY in (:categories) AND SELLER IN (:sellers)", filterParams,
                new ProductMapper());
        return result;
    }

    @Override
    public Product getProductById(String productId) {
        Map<String, Object> params = new HashMap<>();
        params.put("productId", productId);
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM PRODUCT WHERE ID = :productId", params, new ProductMapper());
        }catch(DataAccessException e) {
//            throw new ProductNotFoundException(productId);
            throw null;
        }

    }



}
