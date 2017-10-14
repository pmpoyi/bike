package com.patrice.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.jdbc.Work;

public class MyWork implements Work {
	
    private long id;
    private String value;

    public MyWork( long id, String value )
    {
        this.id = id;
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public void execute( Connection connection ) throws SQLException
    {
    	PreparedStatement ps = null;
		String updateTableSQL = "update Personne set prenom = ? where id = ? ";
		try {
			
			ps = connection.prepareStatement(updateTableSQL);
			ps.setString(1, value);
			ps.setLong(2, id);
            ps.executeUpdate();
            
            System.out.println("Table Personne updated ");
            
		} finally {
			if (ps != null) ps.close();
		}
    }

}
