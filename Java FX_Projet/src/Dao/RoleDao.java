/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import entities.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MATHIS
 */
public class RoleDao implements IDao<Role> {
    DataBase database = new DataBase();
    private final String SQL_SELECT_BY_ID="SELECT * FROM role WHERE id_role = ?";

    @Override
    public int insert(Role ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Role ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Role> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role findById(int id) {
        Role role = null;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_BY_ID);
            database.getPs().setInt(1,id);
            ResultSet rs = database.executeSelect(SQL_SELECT_BY_ID);
            if(rs.next()){
                role = new Role (rs.getInt("id_role"), rs.getString("libelle"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return role;
    }
    
}
