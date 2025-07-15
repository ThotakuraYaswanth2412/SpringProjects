package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import com.ms.bo.CustomerBo;

public final class CustDaoOracleImpl implements ICustomerDao {

	private static final String sqlquery = "INSERT INTO CUSTOMERS_REALTIMES VALUES(CUSTOMER_SEQUENCE.NEXTVAL,?,?,?,?,?,?)";
	private DataSource datasource;
	private String osname;
	private String username;
	private String pathdata;

	public String getOsname() {
		return osname;
	}

	public void setOsname(String osname) {
		this.osname = osname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPathdata(String pathdata) {
		this.pathdata = pathdata;
	}

	public CustDaoOracleImpl(DataSource datasource) {
		System.out.println("custDAoOracleImpl:1-param constructor created");
		this.datasource = datasource;
	}

	@Override
	public int insert(CustomerBo bo) throws Exception {
		System.out.println(username+"...."+osname+"..."+pathdata);

		int count = 0;
		try (Connection con = datasource.getConnection(); PreparedStatement ps = con.prepareStatement(sqlquery)) {
			// set Query Values to Insert
			ps.setString(1, bo.getCustomername());
			ps.setString(2, bo.getCustomeraddress());
			ps.setFloat(3, bo.getPrincipleamount());
			ps.setFloat(4, bo.getTimeofintrest());
			ps.setFloat(5, bo.getRateofintrest());
			ps.setFloat(6, bo.getSimpleintrest());

			// excute the Query
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return count;
	}

}
