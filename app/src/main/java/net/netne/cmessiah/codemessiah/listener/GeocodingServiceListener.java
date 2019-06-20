package net.netne.cmessiah.codemessiah.listener;

import net.netne.cmessiah.codemessiah.data.LocationResult;

public interface GeocodingServiceListener {
    void geocodeSuccess(LocationResult location);

    void geocodeFailure(Exception exception);
}
