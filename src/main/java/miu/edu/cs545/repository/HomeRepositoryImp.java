package miu.edu.cs545.repository;

import miu.edu.cs545.domain.*;
import miu.edu.cs545.service.AccountService;
import miu.edu.cs545.service.BuyerService;
import miu.edu.cs545.service.ProductService;
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
import java.util.Optional;

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
        Map<String, Object> params = new HashMap<>();
        List<Product> result = jdbcTemplate.query("SELECT * from product ORDER BY id DESC LIMIT 8", params, new ProductMapper());
        return result;


    }

    @Autowired
    private BuyerService buyerService;
    @Override
    public List<Seller> getFollowerByBuyer(String username) {
       Buyer buyer = buyerService.getByUsername(username);
        return buyer.getFollowerList();
    }

    @Override
    public List<Product> getFollowerProducts(List<Seller> sellerList) {
        Map<String, Object> params = new HashMap<>();
        String sqlstring="SELECT * from product";
        if(sellerList!=null){
            sqlstring+=" WHERE sellerid in (";
            String collect=" 0";
            for (Seller sel:sellerList) {
                collect+= ",'" +sel.getUsername()+"'";

            }
            sqlstring+= collect+")";
        }
        sqlstring+=" ORDER BY id DESC LIMIT 8";

        List<Product> result = jdbcTemplate.query(sqlstring, params, new ProductMapper());
        return result;
    }



    private static final class ProductMapper implements RowMapper<Product> {
        @Autowired
        private ProductService productService;
        @Autowired
        SellerRepository sellerRepository;
//        @Autowired
//        HomeRepository homeRepository;
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
//            product =productService.getById(rs.getInt("ID")).get();
            product.setId(rs.getInt("ID"));
            product.setName(rs.getString("NAME"));
            product.setDescription(rs.getString("DESCRIPTION"));
            product.setPrice(rs.getDouble("PRICE"));
            Category category= new Category();
            category.setId(rs.getInt("CATEGORY_ID"));
            product.setCategory(category);
            product.setPhoto(rs.getString("PHOTO"));
            List<Review> list= product.getListReview();//homeRepository.getReviewsByProduct(product.getId());//config later
            product.setListReview(list);
            Seller seller=sellerRepository.findById(rs.getString("SELLER_ID")).get();
            product.setSeller(seller);
            product =productService.getById(product.getId()).get();
            return product;
        }

    }



    @Override
    public List<Product> getProductsByCategory(String category) {
        Map<String, Object> params = new HashMap<>();
        params.put("category", category);
        List<Product> result = jdbcTemplate.query("SELECT * FROM PRODUCT WHERE CATEGORY_ID = :category", params,
                new ProductMapper());
        return result;
    }

    @Autowired
    private ProductService productService;
    @Override
    public List<Review> getReviewsByProduct(Integer productid) {
       Product pro= productService.getById(productid).get();
        return pro.getListReview();
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
