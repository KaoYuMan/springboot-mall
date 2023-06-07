package com.milly.springbootmall.dao.impl;

import com.milly.springbootmall.constant.ProductCategory;
import com.milly.springbootmall.dao.ProductDao;
import com.milly.springbootmall.dao.ProductQueryParams;
import com.milly.springbootmall.dto.ProductRequest;
import com.milly.springbootmall.model.Product;
import com.milly.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SELECT  product_id,product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date " +
                "FROM product WHERE 1 = 1";
                //加上WHERE 1 = 1，雖無意義但主要是為了拼接條件，ex:" AND category = :category"

        Map<String,Object> map = new HashMap<>();

        if(productQueryParams.getCategory() != null){
                       // AND 的前面一定要加上一格空白鍵，才不會與sql語句重疊
            sql = sql + " AND category = :category";
            map.put("category",productQueryParams.getCategory().name());
        }
        if(productQueryParams.getSerch() != null){
                       // AND 的前面一定要加上一格空白鍵，才不會與sql語句重疊
            sql = sql + " AND product_name LIKE :serch";
            //模糊查詢 % 要使用map來加入，不能直接加入在sql語句中
            map.put("serch","%" + productQueryParams.getSerch() + "%");
        }

        List<Product> productList = namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());

        return productList;
    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT  product_id,product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date " +
                "FROM product WHERE product_id =:productId";
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productList.size() > 0) {
            return productList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer creatProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product (product_name, category, image_url, " +
                "price, stock, description, created_date, last_modified_date) " +
                "VALUES (:productName, :category, :imageURL , :price, " +
                ":stock , :description, :createdDate,:lastModifiedDate)";

        Map<String,Object> map = new HashMap<>();
        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageURL",productRequest.getImageURL());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());

        Date now = new Date();

        map.put("createdDate",now);
        map.put("lastModifiedDate",now);
        //取得DB自動生成的Id
        KeyHolder keyHolder =new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),keyHolder);

        int productId = keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product SET product_name = :productName, category = :category, " +
                "image_url = :imageURL, price = :price, stock = :stock, description = :description, " +
                "last_modified_date = :lastModifiedDate WHERE product_id = :productId";

        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);

        map.put("productName",productRequest.getProductName());
        map.put("category",productRequest.getCategory().toString());
        map.put("imageURL",productRequest.getImageURL());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());

        map.put("lastModifiedDate",new Date());

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id =:productId";

        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);

        namedParameterJdbcTemplate.update(sql,map);
    }

}
