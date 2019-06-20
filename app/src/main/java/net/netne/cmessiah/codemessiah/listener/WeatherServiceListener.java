package net.netne.cmessiah.codemessiah.listener;

import net.netne.cmessiah.codemessiah.data.Channel;

public interface WeatherServiceListener {
    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);
}