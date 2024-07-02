/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Gustavo Motta
 */
public class Requisicao {

    private String url = "https://api.nasa.gov/neo/rest/v1/feed?start_date=2024-06-27&end_date=2024-07-01&api_key=MAnTYyY6fOQEl5cOoVJjJY1dhJQDrXxqWb20K7Jj";

    public Iterator<Map.Entry<String, JsonNode>> Requisicao() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder().uri(URI.create(this.url)).build();
        HttpResponse<String> resposta = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode raiz = mapper.readTree(resposta.body());
        JsonNode objetosProximos = raiz.get("near_earth_objects");

        Iterator<Map.Entry<String, JsonNode>> campos = objetosProximos.fields();

        return campos;

    }
}
