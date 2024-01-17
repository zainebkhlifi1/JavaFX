package com.esprit.espritevent.Models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Local {
    private  long idLocal;
    private String localName;
    private long localCapacity;
    private Date localAvailableFrom  ;
    private Date localAvailableUntil ;
    private boolean isBooked;

    private List<Event> events;

    public Local(long idLocal, String localName, long localCapacity, Date localAvailableFrom, Date localAvailableUntil, boolean isBooked) {
        this.idLocal = idLocal;
        this.localName = localName;
        this.localCapacity = localCapacity;
        this.localAvailableFrom = localAvailableFrom;
        this.localAvailableUntil = localAvailableUntil;
        this.isBooked = isBooked;
    }

    public Local() {
    }

    public long getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(long idLocal) {
        this.idLocal = idLocal;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public long getLocalCapacity() {
        return localCapacity;
    }

    public void setLocalCapacity(long localCapacity) {
        this.localCapacity = localCapacity;
    }

    public Date getLocalAvailableFrom() {
        return localAvailableFrom;
    }

    public void setLocalAvailableFrom(Date localAvailableFrom) {
        this.localAvailableFrom = localAvailableFrom;
    }

    public Date getLocalAvailableUntil() {
        return localAvailableUntil;
    }

    public void setLocalAvailableUntil(Date localAvailableUntil) {
        this.localAvailableUntil = localAvailableUntil;
    }

    public boolean getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(boolean booked) {
        isBooked = booked;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Local{" +
                "idLocal=" + idLocal +
                ", localName='" + localName + '\'' +
                ", localCapacity=" + localCapacity +
                ", localAvailableFrom=" + localAvailableFrom +
                ", localAvailableUntil=" + localAvailableUntil +
                ", isBooked=" + isBooked +
                ", events=" + events +
                '}';
    }
}
