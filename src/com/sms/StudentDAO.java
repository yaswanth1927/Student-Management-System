package com.sms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDAO {

    public void addStudent(Student student) {

        String sql = "INSERT INTO students(name, age, course, email) VALUES(?, ?, ?, ?)";

        try {
        	Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            ps.setString(4, student.getEmail());

            ps.executeUpdate();
            
            System.out.println("---------------------------------------------");
            System.out.println("Student Added Successfully!");
            System.out.println("---------------------------------------------");

            


            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewStudents() {

        String sql = "SELECT * FROM students";

        try {
        	Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            
                    System.out.println("+------+----------------------+-----+------------------+");
                    System.out.printf("| %-4s | %-20s | %-3s | %-18s |%n",
                            "ID", "NAME", "AGE", "COURSE");
                    System.out.println("+------+----------------------+-----+------------------+");

                    while (rs.next()) {

                        System.out.printf("| %-4d | %-20s | %-3d | %-18s |%n",
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("age"),
                                rs.getString("course"));
                    }

                    System.out.println("+------+----------------------+-----+------------------+");
                

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateStudent(Student student) {

        String sql = "UPDATE students SET name=?, age=?, course=?, email=? WHERE id=?";

        try {
        	Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            ps.setString(4, student.getEmail());
            ps.setInt(5, student.getId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("--------------------------------------------");
                System.out.println("Student Updated Successfully!");
                System.out.println("--------------------------------------------");

            } else {
                System.out.println("---------------------------------------------");
                System.out.println("Student ID Not Found!");
                System.out.println("---------------------------------------------");

            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id=?";

        try {
        	Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("---------------------------------------------");
                System.out.println("Student Deleted Successfully!");
                System.out.println("---------------------------------------------");

            } else {
                System.out.println("---------------------------------------------");
                System.out.println("Student ID Not Found!");
                System.out.println("---------------------------------------------");

            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchStudent(int id) {

        String sql = "SELECT * FROM students WHERE id=?";

        try {
        	Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n----- Student Details -----");
                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("Name : " + rs.getString("name"));
                System.out.println("Age : " + rs.getInt("age"));
                System.out.println("Course : " + rs.getString("course"));
                System.out.println("Email : " + rs.getString("email"));

            } else {
                System.out.println("Student Not Found!");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchStudentByName(String name) {

        String sql = "SELECT * FROM students WHERE name = ?";

        try {
        	Connection con = DBConnection.getConnection();
        	
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("ID      : " + rs.getInt("id"));
                System.out.println("Name    : " + rs.getString("name"));
                System.out.println("Age     : " + rs.getInt("age"));
                System.out.println("Course  : " + rs.getString("course"));
                System.out.println("Email   : " + rs.getString("email"));
                System.out.println("------------------------------");
            }

            if (!found) {
                System.out.println("---------------------------------------------");
                System.out.println("Student Not Found!");
                System.out.println("---------------------------------------------");

            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void countStudents() {

        String sql = "SELECT COUNT(*) FROM students";

        try {
        	Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Total Students : " + rs.getInt(1));
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[]args) {
    	StudentDAO dao = new StudentDAO();
    	dao.viewStudents();
    }
}
