package com.lidp.fare.dao;

import com.lidp.fare.domain.Fare;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FareRepository {
    private Connection conn;

    public FareRepository() throws SQLException, ClassNotFoundException {
        Mariadb db = new Mariadb();
        conn = db.getMariaDBConnection();
    }

    public Fare findByFareObject(Fare fare) throws SQLException {
        Statement st=conn.createStatement();

        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateStr = dmyFormat.format(fare.getDepartureTime());

        String query = "SELECT * FROM fares where fare_date = '" + formattedDateStr + "' and fare_distance_miles = " + fare.getDistanceMi() + " and fare_seat_row = " + fare.getSeatRow() + " limit 1";
        ResultSet rs=st.executeQuery(query);
        if(rs.next()){
            return fare;
        }
        else{
            return null;
        }
    }

    public List<Fare> findAll() throws SQLException {
        List<Fare> fareList = new ArrayList<Fare>();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("SELECT * FROM fares");
        while(rs.next())
        {
            Fare tempFare = new Fare(rs.getDate("fare_date"), rs.getDouble("fare_distance_miles"), rs.getInt("fare_seat_row"), rs.getDouble("fare_amount"));
            fareList.add(tempFare);
        }
        conn.close();
        return  fareList;
    }

    public void save(Fare fare) throws SQLException {
        Statement st=conn.createStatement();
        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateStr = dmyFormat.format(fare.getDepartureTime());
        String query =
                "INSERT INTO fares ("
                        + " fare_date,"
                        + " fare_distance_miles,"
                        + " fare_seat_row,"
                        + " fare_amount) "
                        + "VALUES ("
                        + "'" + formattedDateStr + "', "
                        + fare.getDistanceMi() + ", "
                        + fare.getSeatRow() + ", "
                        + fare.getCost() + ")";
        ResultSet rs=st.executeQuery(query);
        conn.close();
    }
}
