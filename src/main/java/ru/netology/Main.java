package ru.netology;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static final String REMOTE_SERVICE_URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("Hi cat")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build()) {

            HttpGet request = new HttpGet(REMOTE_SERVICE_URI);
            request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

            try (CloseableHttpResponse response = httpClient.execute(request)) {

                Arrays.stream(response.getAllHeaders()).forEach(System.out::println);


                List<CatsInfo> catsInfoList = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
                });
                
               List<CatsInfo> catsInfoList1 = catsInfoList.stream()
                        .filter(value -> value.getUpvotes() > 0)
                        .collect(Collectors.toList());

                System.out.println(catsInfoList1);

            }
        }
    }
}
