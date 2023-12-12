package com.esprit.espritevent.Models;

import java.time.LocalDate;
import java.util.List;

public class Event {
    private  long idEvent ;
    private String eventName;
    private String eventDescription ;
    private LocalDate eventDate;
    private LocalDate eventStartTime ;
    private LocalDate eventEndTime ;
    private EventState eventState;
    private Club club;
    private Local eventLocal;
    private List<User> participants;

}
