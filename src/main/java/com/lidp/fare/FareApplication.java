package com.lidp.fare;

import com.lidp.fare.dao.FareRepository;
import com.lidp.fare.domain.Fare;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lidp.fare.service.FareService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
@SpringBootApplication
@RestController
public class FareApplication {

	public static void main(String[] args) {
		SpringApplication.run(FareApplication.class, args);
	}

	@GetMapping("/fare")
	public String fare(@RequestParam(value = "date", defaultValue = "01/02/2023") String date,
					   @RequestParam(value = "miles", defaultValue = "1") double miles,
					   @RequestParam(value = "row", defaultValue = "1") int row) throws SQLException, ClassNotFoundException {
		FareService fs = new FareService(new FareRepository());

		Date dt = new Date(date);

		double fareAmount = fs.getFare(dt, miles, row);
		return String.valueOf(fareAmount);
	}

	@GetMapping("/fares")
	public String fares() throws SQLException, ClassNotFoundException {
		FareService fs = new FareService(new FareRepository());
		List<Fare> fareList = fs.getFares();
		return fareList.toString();
	}
}
