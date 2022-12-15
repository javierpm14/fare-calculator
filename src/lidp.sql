CREATE TABLE fares (
  fare_id             BigInt(20) NOT NULL AUTO_INCREMENT,
  fare_date           Date NOT NULL,
  fare_distance_miles Double NOT NULL,
  fare_seat_row       Integer(11) NOT NULL,
  fare_amount         Double NOT NULL, 
  PRIMARY KEY (
      fare_id
  )
) ENGINE=InnoDB AUTO_INCREMENT=12 ROW_FORMAT=DYNAMIC DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
ALTER TABLE fares COMMENT = '';

