/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Service.Service;
import entities.Medecin;
import entities.Role;
import entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MATHIS
 */
public class UserDao implements IDao<User> {
    private final String SQL_LOGIN="SELECT * FROM user WHERE login = ? AND password = ?";
    
    private final String SQL_INSERT="INSERT INTO `user` (`nom`, `prenom`, `login`,`password`,`role_id`) "
            + "VALUES ('?', '?', '?', '?','?')";
    
    private final String SQL_SELECT_MEDECIN_BY_SPECIALITE="SELECT * FROM user WHERE role_id='5' AND specialite_id=?";
    
    DataBase database= new DataBase();

    @Override
    public int insert(User user) {
        int id=0;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_INSERT);
            database.getPs().setString(1, user.getNom());
            database.getPs().setString(2, user.getPrenom());
            database.getPs().setString(3,user.getLogin());
            database.getPs().setString(4, user.getPassword());
            database.getPs().setInt(5, user.getRole().getId_role());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs= database.getPs().getGeneratedKeys();
            if(rs.next()){
                id= rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return id;
    }

    @Override
    public int update(User ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public User findUserByloginAndPassword(String login, String password){
        User user =null;
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_LOGIN);
            database.getPs().setString(1, login);
            database.getPs().setString(2, password);
            ResultSet rs= database.executeSelect(SQL_LOGIN);
            if(rs.next()){
                Service service = new Service();
                user = new User();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                //Role
                Role role = service.findRoleById(rs.getInt("role_id"));
                user.setRole(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnexion();
        }
        return user;
    }
    
    public List<Medecin> findMedecinBySpecialite(int id){
        List <Medecin> meds = new ArrayList();
        database.openConnexion();
        try {
            database.initPrepareStatement(SQL_SELECT_MEDECIN_BY_SPECIALITE);
            database.getPs().setInt(1,id);
            ResultSet rs = database.executeSelect(SQL_SELECT_MEDECIN_BY_SPECIALITE);
            while(rs.next()){
                Medecin med = new Medecin();
                med.setNom(rs.getString("nom"));
                med.setPrenom(rs.getString("prenom"));
                med.setId(rs.getInt("id"));
                meds.add(med);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return meds;
    }
}
