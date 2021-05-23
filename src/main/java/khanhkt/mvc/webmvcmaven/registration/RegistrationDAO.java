/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhkt.mvc.webmvcmaven.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khanhkt.mvc.webmvcmaven.utils.DBHelpers;


/**
 *
 * @author Kieu Trong Khanh
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            //2. Create SQL String
            if (con != null) {
                String sql = "Select username "
                        + "From Registration "
                        + "Where username = ? And password = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    return true;
                }//end if 
            }//end if connection is opened
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }
    
    public void searchLastname(String searchValue) 
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            //2. Create SQL String
            if (con != null) {
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    
                    RegistrationDTO dto = new RegistrationDTO(
                            username, password, fullname, role);
                    
                    if(this.accountList == null) {
                        this.accountList = new ArrayList();
                    }
                    
                    this.accountList.add(dto);
                } //end while
            }//end if connection is opened
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public boolean deleteAccount(String username) 
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            //2. Create SQL String
            if (con != null) {
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Execute Query
                int row = stm.executeUpdate();
                //5. process result
                if (row > 0) {
                    return true;
                }
            }//end if connection is opened
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean createAccount(String username, String password,
            String fullName, boolean role) 
        throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            //2. Create SQL String
            if (con != null) {
                String sql = "Insert Into Registration(username, password, lastname, isAdmin) "
                        + "Values(?, ?, ?, ?)";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, fullName);
                stm.setBoolean(4, role);
                
                //4. Execute Query
                int row = stm.executeUpdate();
                //5. process result
                if (row > 0) {
                    return true;
                }
            }//end if connection is opened
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
