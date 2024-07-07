package it.epicode.DiVino.TravelPackages;

import it.epicode.DiVino.Wineries.Winery;

import java.util.Date;

public interface TravelPackageResponsePrj {
    Winery getWineryId();
    String getTravelPackageName();
    Date getStartDate();
    Date getEndDate();
    Double getPrice();



}
