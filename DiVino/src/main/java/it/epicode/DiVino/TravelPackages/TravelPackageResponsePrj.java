package it.epicode.DiVino.TravelPackages;

import it.epicode.DiVino.Wineries.Winery;

import java.util.Date;

public interface TravelPackageResponsePrj {
    Long getWineryId();
    String getTravelPackageName();
    String getImageUrl();
    Date getStartDate();
    Date getEndDate();
    Double getPrice();



}
