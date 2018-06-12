/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import DAO.ProjetoDAO;
import Model.ProjetoModel;
import java.sql.SQLException;

/**
 *
 * @author Bond
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        ProjetoDAO pdao = new ProjetoDAO();
        for(ProjetoModel p : pdao.obterTodos()){
            System.out.println(p);
        }
    }
    }
    
