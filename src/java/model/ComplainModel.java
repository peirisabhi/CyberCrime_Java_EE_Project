package model;

import db.DBConnection;
import dto.Complain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComplainModel {
    public static boolean saveComplain(String title, String category,  String date, String time, String description, int userId) {

        Connection connection = null;
        boolean status = true;

        try {

            connection = DBConnection.getConnection();

            PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO complain  (`title`, `category`, `date`, `time`, `description`, `user_id`, `active_status`, `status`) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement1.setString(1, title);
            preparedStatement1.setString(2, category);
            preparedStatement1.setString(3, date);
            preparedStatement1.setString(4, time);
            preparedStatement1.setString(5, description);
            preparedStatement1.setInt(6, userId);
            preparedStatement1.setInt(7, 1);
            preparedStatement1.setString(8, " ");

            preparedStatement1.executeUpdate();



        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                status = false;
            }
        }

        return status;
    }


    public static Complain getComplainById(int id){
        Connection connection = null;
        Complain complain = null;

        try {

            connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM  complain INNER JOIN `user` WHERE `complain`.user_id = `user`.id  AND `complain`.`active_status` = 1 AND complain.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                complain = new Complain();
                complain.setId(resultSet.getInt(1));
                complain.setTitle(resultSet.getString(2));
                complain.setCategory(resultSet.getString(3));
                complain.setDate(resultSet.getString(4));
                complain.setTime(resultSet.getString(5));
                complain.setDescription(resultSet.getString(6));
                complain.setCreatedAt(resultSet.getString(7));
                complain.setUserId(resultSet.getString(8));
                complain.setStatus(resultSet.getString(10));
                complain.setComplainer(resultSet.getString(12));
                complain.setComplainerEmail(resultSet.getString(13));
                complain.setComplainerMobile(resultSet.getString(14));


            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return complain;
    }


}
