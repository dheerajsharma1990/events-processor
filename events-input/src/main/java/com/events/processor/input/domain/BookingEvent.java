package com.events.processor.input.domain;

import java.io.Serializable;
import java.util.Date;

public class BookingEvent implements Serializable {

    private String eventId;

    private Date checkIn;

    private Date checkOut;

    private String hotelId;

}
