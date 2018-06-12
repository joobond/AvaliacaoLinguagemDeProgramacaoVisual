package DAO;

import Model.ProjetoModel;
import Util.conexãoSingleton;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bond
 */
public class ProjetoDAO {
    
    public void salvar(ProjetoModel p) throws SQLException {
        String sql = "INSERT INTO projeto "
                + "(titulo,data_inicial,data_final,carga_horaria,participantes,"
                + "valor_hora,valor_consumo,valor_estimado,prioridade) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";

        Connection conn = conexãoSingleton.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, p.getTitulo());
        stmt.setDate(2, java.sql.Date.valueOf(p.getData_inicial()));
        stmt.setDate(3, java.sql.Date.valueOf(p.getData_final()));
        stmt.setFloat(4, p.getCarga_horaria());
        stmt.setInt(5, p.getParticipantes());
        stmt.setDouble(6, p.getValor_hora());
        stmt.setDouble(7, p.getValor_consumo());
        stmt.setDouble(8, p.getValor_estimado());
        stmt.setInt(9, p.getPrioridade());

        stmt.execute();
    }

    public List<ProjetoModel> obterTodos() throws SQLException {
        String sql = "select * from projeto";
        List<ProjetoModel> lista = new ArrayList<>();

        Connection conn = conexãoSingleton.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();
        return montaProjetos(rs);
    }

    /*
      JavaDoc
    * ret: null se não tiver elemento com essa chae primária no banco
    */
    
    public ProjetoModel obeterPorPK(int pk) throws SQLException {
        String sql = "select * from projeto where id = ?";
        Connection conn = conexãoSingleton.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, pk);
        ResultSet rs = stmt.executeQuery();
        ArrayList<ProjetoModel> array = montaProjetos(rs);
        if (array.size()>0) {
            return array.get(0);
        }
        return null;
    }
    
    private ArrayList<ProjetoModel> montaProjetos(ResultSet rs) throws SQLException{
        ArrayList<ProjetoModel> retorno = new ArrayList();
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (rs.next()) {
            ProjetoModel p = new ProjetoModel();
            p.setId(rs.getInt("id"));
            p.setTitulo(rs.getString("titulo"));
            LocalDate data = rs.getDate("data_inicial").toLocalDate();
            p.setData_inicial(data);
            LocalDate dataf = rs.getDate("data_final").toLocalDate();
            p.setData_final(dataf);
            p.setCarga_horaria(rs.getFloat("carga_horaria"));
            p.setParticipantes(rs.getInt("participantes"));
            p.setValor_hora(rs.getDouble("valor_hora"));
            p.setValor_consumo(rs.getDouble("valor_consumo"));
            p.setValor_estimado(rs.getDouble("valor_estimado"));
            p.setPrioridade(rs.getInt("prioridade"));
            retorno.add(p);
        }
        return retorno;
    }
    
    public boolean excluir(int pk) throws SQLException{
        String sql = "delete from projeto where id = ?";
        Connection conn = conexãoSingleton.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, pk);
        int ret = stmt.executeUpdate();
        
        return ret != 0;
    }
    
    //Sobrecarga de método. Mudando a assinatura do método.
    public boolean excluir(ProjetoModel pro) throws SQLException{
        return this.excluir(pro.getId());
    } 
    
     public void atualizar(ProjetoModel p) throws SQLException {
       String sql = "UPDATE projeto "
                + "SET titulo = ? ,data_inicial = ?,data_final = ?,carga_horaria = ?,participantes = ?,"
                + "valor_hora = ?,valor_consumo = ?,valor_estimado = ?,prioridade = ? "
                + "WHERE id = ?";

        Connection conn = conexãoSingleton.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, p.getTitulo());
        stmt.setDate(2, java.sql.Date.valueOf(p.getData_inicial()));
        stmt.setDate(3, java.sql.Date.valueOf(p.getData_final()));
        stmt.setFloat(4, p.getCarga_horaria());
        stmt.setInt(5, p.getParticipantes());
        stmt.setDouble(6, p.getValor_hora());
        stmt.setDouble(7, p.getValor_consumo());
        stmt.setDouble(8, p.getValor_estimado());
        stmt.setInt(9, p.getPrioridade());
        stmt.setInt(10, p.getId());

        stmt.execute();
     }
     
     public ResultSet conexaoRel(String SQL) throws SQLException{
         
         try{
             Connection conn = conexãoSingleton.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            return rs;
         }catch(SQLException ex){
             System.out.println("Deu erro!");
             return null;
         }
         
     }
}
