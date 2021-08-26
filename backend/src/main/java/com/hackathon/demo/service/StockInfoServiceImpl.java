package com.hackathon.demo.service;


import com.hackathon.demo.entity.HistoricalItem;
import com.hackathon.demo.entity.HistoricalPrices;
import com.hackathon.demo.entity.TradeType;
import com.hackathon.demo.entity.User;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.property.access.internal.PropertyAccessStrategyIndexBackRefImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Configuration
public class StockInfoServiceImpl implements StockInfoService{

    private String summaryUrl;
    private JSONObject responseJson;
    private String APIHost;
    private String APIKey;
    private String historicalUrl;



    public StockInfoServiceImpl(@Value("${YahooCompanySummaryUrl}") String summaryUrl,
                                @Value("${RapidAPIHost}") String APIHost,
                                @Value("${RapidAPIKey}") String APIKey,
                                @Value("${YahooHistoricalDataUrl}") String historicalUrl) {
        this.APIHost = APIHost;
        this.APIKey = APIKey;
        this.summaryUrl = summaryUrl;
        this.historicalUrl=historicalUrl;
    }


    public void getResponseBody(String stock) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url =  summaryUrl + stock;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", APIHost)
                .addHeader("x-rapidapi-key", APIKey)
                .build();

        Response response = client.newCall(request).execute();
        responseJson = new JSONObject(response.body().string());
    }

    public List<HistoricalItem> getHistoricalData(String stock) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url =  historicalUrl + stock;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", APIHost)
                .addHeader("x-rapidapi-key", APIKey)
                .build();

        Response response = client.newCall(request).execute();
        responseJson = new JSONObject(response.body().string());
        log.info(responseJson.toString());
        return getPastYearPrices(responseJson);
    }

    public List<HistoricalItem> getPastYearPrices(JSONObject json){
        List<HistoricalItem> items = new ArrayList<HistoricalItem>();
        JSONArray prices = new JSONArray(responseJson.getJSONArray("prices").toString());
        for (int i = 0; i < 260; i++) {
            try {
                JSONObject thisDay = prices.getJSONObject(i);
                long timestamp = thisDay.getLong("date");
                Date date = new Date(timestamp * 1000);
                HistoricalPrices thisPrices = new HistoricalPrices(thisDay.getDouble("open"),
                        thisDay.getDouble("high"),
                        thisDay.getDouble("low"),
                        thisDay.getDouble("close"));
                HistoricalItem thisItem = new HistoricalItem(date, thisPrices);
                items.add(thisItem);
            }catch (JSONException e){
                log.info(String.format("JSON Field not found for index %d",i));
            }
        }
        return items;
    }

    @Override
    public String getName() {
        JSONObject quoteType = new JSONObject(responseJson.getJSONObject("quoteType").toString());
        return quoteType.getString("longName");
    }

    @Override
    public Double getPrice() {
        JSONObject summaryDetail = new JSONObject(responseJson.getJSONObject("summaryDetail").toString());
        JSONObject regularMarketOpen = new JSONObject(summaryDetail.getJSONObject("regularMarketOpen").toString());
        return regularMarketOpen.getDouble("raw");
    }

    @Override
    public String getDescription() {
        JSONObject summaryProfile = new JSONObject(responseJson.getJSONObject("summaryProfile").toString());
        String summary = new String(summaryProfile.getString("longBusinessSummary"));
        return summary;
    }



}
