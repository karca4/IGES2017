/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Account;
import utils.DriverManagerConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends AbstractDAO<Account> {

    
    private final String doRetriveByIdQuery = "SELECT * FROM account WHERE email = ?";
    private final String doRetriveAllQuery = "SELECT * FROM account";
    
    
    //private final String doDeleteQuery = "DELETE FROM Account WHERE email = ?";
    //private final String doInsertQuery = "INSERT INTO Account(email,password,path_foto,tipo) VALUES(?,?,?,?);";
    //private final String doUpdateQuery = "UPDATE Account SET password = ?, path_foto = ?, tipo = ? WHERE email = ?";
    //private final String doUpdateEmail = "UPDATE Account SET email = ? WHERE email = ?";
    //private final String doRetrivePersonaAndBibliotecarioQuery = "SELECT * FROM Account WHERE NOT tipo = ?";

    /**
     *
     * @param id[0] si aspetta una email
     * @return un account in base all'email inserita.
     */
    @Override
    public Account doRetriveById(Object... id) {
        String email = (String) id[0];
        Account account = null;
        try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveByIdQuery);
            prst.setString(1, email);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();

                if (rs.next()) {
                    account = new Account(rs.getString("email"), rs.getString("password"), rs.getString("tipo"));
                }
                rs.close();
                return account;

            } catch (SQLException e) {
                con.rollback();

            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return account;
    }

    @Override
    public List<Account> doRetriveAll() {
        List<Account> accounts = new ArrayList<Account>();
        Account account = null;
        Connection con;
        try {
            con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doRetriveAllQuery);

            try {
                ResultSet rs = prst.executeQuery();
                con.commit();

                while (rs.next()) {
                    account = new Account(rs.getString("email"), rs.getString("password"), rs.getString("tipo"));
                    accounts.add(account);
                }
                rs.close();
                return accounts;

            } catch (SQLException e) {
                con.rollback();

            } finally {
                prst.close();
                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return accounts;
    }

    @Override
    public int doInsert(Account account) {
        /*try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doInsertQuery);
            prst.setString(1, account.getEmail());
            prst.setString(2, account.getPassword());
            prst.setString(4, account.getTipo());

            try {
                prst.execute();
                con.commit();
                return 0;
            } catch (SQLException e) {
                con.rollback();
                return -1;
            } finally {
                prst.close();

                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            return -1;
        }
*/
        return 0;
    }

    @Override
    public int doUpdate(Account account) {
        /*try {
            Connection con = DriverManagerConnectionPool.getConnection();
            PreparedStatement prst = con.prepareStatement(doUpdateQuery);

            prst.setString(1, account.getPassword());
            prst.setString(2, account.getPathFoto());
            prst.setString(3, account.getTipo());

            prst.setString(4, account.getEmail());

            try {
                prst.execute();
                con.commit();
                return 0;
            } catch (SQLException e) {
                con.rollback();
                return -1;
            } finally {
                prst.close();

                DriverManagerConnectionPool.releaseConnection(con);
            }

        } catch (SQLException e) {
            return -1;
        }
*/
        return 0;
    }
    
}
