/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.db;

import java.sql.SQLException;
import repository.Repository;

/**
 *
 * @author HP
 */
public interface DbRepository<T> extends Repository<T> {
    default public void connect() throws SQLException{
        DbConnectionFactory.getInstance().getConnection();
    }
    
    default public void disconnect() throws SQLException{
        DbConnectionFactory.getInstance().getConnection().close();
    }
    
    default public void commit() throws SQLException{
        DbConnectionFactory.getInstance().getConnection().commit();
    }
    
    default public void rollback() throws SQLException{
        DbConnectionFactory.getInstance().getConnection().rollback();
    }
}
