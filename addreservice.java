package services;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.Properties;


import connection.connect;
import model.addremod;

public class addreservice {
	Connection con = connect.getConnection();

	public int insert(addremod am) {
		// TODO Auto-generated method stub

		try {
			String s1 = "INSERT INTO `reminder`(`title`, `desc`, `date`) VALUES (?,?,?)";
			PreparedStatement ps = con.prepareStatement(s1);
			ps.setString(1, am.getTitle());
			ps.setString(2, am.getDesc());
			ps.setString(3, am.getDate());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return 0;
	}

	public ArrayList<addremod> selectAllRegistration() {
		addremod rm = null;
		ArrayList<addremod> asv = new ArrayList<addremod>();
		try {
			PreparedStatement p = con.prepareStatement("SELECT * FROM reminder");
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				rm = new addremod();
				rm.setId(rs.getInt(1));
				rm.setTitle(rs.getString(2));
				rm.setDesc(rs.getString(3));
				rm.setDate(rs.getString(4));
				asv.add(rm);

			}
			return asv;

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	public addremod selectByid(int Id) {

		addremod rm = null;
		ArrayList<addremod> arr1 = new ArrayList<addremod>();
		try {
			String query = "SELECT `title`, `desc`, `date` FROM `reminder` WHERE `id`=?";
			PreparedStatement p = con.prepareStatement(query);
			p.setInt(1, Id);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				rm = new addremod();
				rm.setTitle(rs.getString(1));
				rm.setDesc(rs.getString(2));
				rm.setDate(rs.getString(3));
				arr1.add(rm);
			}
			return rm;
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int updateRegisration(addremod am) {
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE `reminder` SET `title`=?,`desc`=?,`date`=? WHERE `id`=?");
			ps.setString(1, am.getTitle());
			ps.setString(2, am.getDesc());
			ps.setString(3, am.getDate());
			ps.setInt(4, am.getId());

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public void deleteRegistration(int id) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM reminder WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
