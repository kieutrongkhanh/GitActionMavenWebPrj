/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhkt.mvc.webmvcmaven.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Kieu Trong Khanh
 */
public class DBHelpers {
    public static Connection makeConnection() 
        throws NamingException, SQLException {
        //1. get current context
        Context context = new InitialContext();
        //2. get servercontext
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        //3. get Datasource
        DataSource ds = (DataSource)tomcatContext.lookup("DS007");
        //4. create con from ds
        Connection con = ds.getConnection();
        
        return con;

    }

}
