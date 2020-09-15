/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuyetnta.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import tuyetnta.dtos.CategoryDTO;
import tuyetnta.util.MyConnection;

/**
 *
 * @author tuyet
 */
public class CategoryDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement prStm = null;
    private ResultSet rs = null;

    private void closeConn() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (prStm != null) {
            prStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    public List<CategoryDTO> getAllCate() throws SQLException, NamingException{
        List<CategoryDTO> listCate = new ArrayList<>();
        try {
            conn = MyConnection.getMyConnection();
            String sql = "SELECT id, name FROM categorys";
            prStm = conn.prepareStatement(sql);
            rs = prStm.executeQuery();
            while(rs.next()){
                CategoryDTO cate = new CategoryDTO();
                cate.setId(rs.getInt("id"));
                cate.setName(rs.getNString("name"));
                listCate.add(cate);
            }
        } finally {
            closeConn();
        }
        return listCate;
    }
}
