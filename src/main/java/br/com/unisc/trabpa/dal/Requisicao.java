package br.com.unisc.trabpa.dal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.Map;

public class Requisicao {

    public static Iterator<Map.Entry<String, JsonNode>> Request(String dataInicio, String dataFim) throws IOException, InterruptedException {

        String url = "https://api.nasa.gov/neo/rest/v1/feed?start_date=" + dataInicio + "&end_date=" + dataFim + "&api_key=MAnTYyY6fOQEl5cOoVJjJY1dhJQDrXxqWb20K7Jj";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requisicao = (HttpRequest) HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> retornoApi = client.send(requisicao, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode raiz = mapper.readTree(retornoApi.body());
        JsonNode objetosProximos = raiz.get("near_earth_objects");

        Iterator<Map.Entry<String, JsonNode>> dadosApi = objetosProximos.fields();

        return dadosApi;
    }
    
}
