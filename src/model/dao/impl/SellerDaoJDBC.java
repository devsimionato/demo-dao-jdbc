package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName " //selececione todos elementos de seller e department apenas nome recebendo DepName
					+ "FROM seller INNER JOIN department" //cruze os dados das duas tabelas
					+ " ON seller.DepartmentId = department.id " //aonde o ID de seller for igual o ID department
					+ "WHERE seller.Id = ?"); //deste Id vendedor
			st.setInt(1, id);
			rs = st.executeQuery(); //a consulta preparada é executada no banco de dados usando o método executeQuery().
			//O resultado da consulta é armazenado em um objeto ResultSet chamado rs
			
			//Agora precisamos transformar o rs (formato tabela) para formato objeto
			if (rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller obj = instantiateSeller(rs, dep);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		//"throws SQLException" = apenas propagar a exceção pois já está sendo tratada na outra chamada
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        st = conn.prepareStatement(
	                "SELECT seller.*, department.Name AS DepName" +
	                " FROM seller INNER JOIN department" +
	                " ON seller.DepartmentId = department.Id " +
	                "WHERE departmentId = ?" +
	                " ORDER BY Name");
	        st.setInt(1, department.getId());

	        rs = st.executeQuery();

	        List<Seller> list = new ArrayList<>();
	        // Foi criada uma lista que retornará o resultado da chamada, pois poderá haver mais de um "Seller" com este "Department"
	        Map<Integer, Department> map = new HashMap<>();

	        while (rs.next()) {
	            // Enquanto houver resultados no ResultSet

	            Department dep = map.get(rs.getInt("DepartmentId"));
	            // Foi criada uma estrutura Map com (chave, department)
	            // É feita então uma verificação se essa classe já existe na estrutura Map através da chave passada
	            // A chave passada é o DepartmentId da tabela do banco de dados Seller, que por sua vez é a chave de Map
	            // Se retornar "null", será instanciado um novo "Department"
	            if (dep == null) {
	                dep = instantiateDepartment(rs);
	                map.put(rs.getInt("DepartmentId"), dep);
	            }

	            Seller obj = instantiateSeller(rs, dep);
	            // Ao final, os objetos "Seller" irão apontar para o mesmo Department "dep"
	            list.add(obj);
	        }
	        return list;
	    } catch (SQLException e) {
	        throw new DbException(e.getMessage());
	    } finally {
	        DB.closeStatement(st);
	        DB.closeResultSet(rs);
	    }
	}
	
}
