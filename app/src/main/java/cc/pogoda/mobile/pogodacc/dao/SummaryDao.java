package cc.pogoda.mobile.pogodacc.dao;

import java.io.IOException;

import cc.pogoda.mobile.pogodacc.type.web.Summary;
import cc.pogoda.mobile.pogodacc.web.RestClientConfig;
import cc.pogoda.mobile.pogodacc.web.SummaryConsumer;
import retrofit2.Response;

public class SummaryDao {

    RestClientConfig restClient;

    Response<Summary> response = null;

    String station;

    class Worker implements Runnable {

        @Override
        public void run() {
            restClient = new RestClientConfig();

            SummaryConsumer consumer = restClient.getWeatherStationClient().create(SummaryConsumer.class);

            try {
                response = consumer.getSummaryForStation(station).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Summary getStationSummary(String station) {

        Summary out = null;

        this.station = station;

        Thread worker = new Thread(new Worker());

        worker.start();

        try {
            worker.join();

            if (response != null) {
                out = response.body();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return out;

    }
}