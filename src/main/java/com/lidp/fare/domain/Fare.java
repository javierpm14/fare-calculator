package com.lidp.fare.domain;

import java.time.Instant;
import java.util.Date;

public class Fare
{
    private Date departureTime;
    private double distanceMi;
    private int seatRow;
    private double cost;

    public Fare(){}

    public Fare(Date departureTime, double distanceMi, int seatRow, double cost)
    {
        this.departureTime = departureTime;
        this.distanceMi = distanceMi;
        this.seatRow = seatRow;
        this.cost = cost;
    }

    public Date getDepartureTime()
    {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime)
    {
        this.departureTime = departureTime;
    }

    public double getDistanceMi()
    {
        return distanceMi;
    }

    public void setDistanceMi(double distanceMi)
    {
        this.distanceMi = distanceMi;
    }

    public int getSeatRow()
    {
        return seatRow;
    }

    public void setSeatRow(int seatRow)
    {
        this.seatRow = seatRow;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    @Override
    public String toString()
    {
        return "Fare{" + "departureTime=" + departureTime + ", distanceMi=" + distanceMi + ", seatRow=" + seatRow + ", cost=" + cost + '}';
    }
}
