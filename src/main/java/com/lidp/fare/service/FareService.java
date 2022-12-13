package com.lidp.fare.service;

import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.lidp.fare.domain.Fare;
import com.lidp.fare.dao.FareRepository;

public class FareService
{
    private static final Logger logger = Logger.getLogger(FareService.class.getName());

    private final FareRepository fareRepository;

    public FareService(FareRepository fareRepository)
    {
        this.fareRepository = fareRepository;
    }

    public List<Fare> getFares() throws SQLException {
        return fareRepository.findAll();
    }

    public double getFare(Date departureTime, double distanceMi, int seatRow) throws SQLException {
        Fare existingFare = fareRepository.findByFareObject(new Fare(departureTime, distanceMi, seatRow, 0.0));

        if(existingFare == null){

            double cost = calculateFare(departureTime, distanceMi, seatRow);

            Fare fare = new Fare();
            fare.setDepartureTime(departureTime);
            fare.setDistanceMi(distanceMi);
            fare.setSeatRow(seatRow);
            fare.setCost(cost);

            fareRepository.save(fare);

            //logger.info("Saving new fare: " + fare);

            return cost;
        }
        else{
            //logger.info("Returning existing fare: " + fare);
            return calculateFare(departureTime, distanceMi, seatRow);
        }
    }

    private double calculateFare(Date departureTime, double distanceMi, int seatRow)
    {
        // the higher the service level (based on the seat row), the higher the base rate
        double baseRate = getBaseRate(seatRow);

        // the greater the distance, the higher the fare
        double distanceFee = distanceMi * 0.1;

        Date currentDate = new Date();

        long time_difference = departureTime.getTime() - currentDate.getTime();

        // Calucalte time difference in days using TimeUnit class
        long days_difference = TimeUnit.MILLISECONDS.toDays(time_difference) % 365;

        // the closer to the departure date, the higher the fare
        double departureTimeFee = (0.0088 * days_difference * days_difference) - (1.3869 * days_difference) + 100;

        return baseRate + distanceFee + departureTimeFee;
    }

    private double getBaseRate(int seatPosition)
    {
        if (seatPosition < 4)
        {
            // first class base rate
            return 300;
        }
        else if (seatPosition < 8)
        {
            // comfort base rate
            return 150;
        }
        else
        {
            // economy base rate
            return 50;
        }
    }
}
