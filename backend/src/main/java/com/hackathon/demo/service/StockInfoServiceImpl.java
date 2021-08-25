package com.hackathon.demo.service;


import com.hackathon.demo.entity.TradeType;
import com.hackathon.demo.entity.User;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

@Slf4j
@Service
@Configuration
public class StockInfoServiceImpl implements StockInfoService{

    private String summaryUrl;
    private JSONObject responseJson;
    private String APIHost;
    private String APIKey;



    public StockInfoServiceImpl(@Value("${YahooCompanySummaryUrl}") String summaryUrl,
                                @Value("${RapidAPIHost}") String APIHost,
                                @Value("${RapidAPIKey}") String APIKey) {
        this.APIHost = APIHost;
        this.APIKey = APIKey;
        this.summaryUrl = summaryUrl;
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
