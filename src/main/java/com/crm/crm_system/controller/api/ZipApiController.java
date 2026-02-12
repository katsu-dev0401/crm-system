package com.crm.crm_system.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.crm.crm_system.dto.AddressDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ZipApiController {

    private static final String ZIPCLOUD_URL = "https://zipcloud.ibsnet.co.jp/api/search?zipcode={zipcode}";

    private final ObjectMapper objectMapper;

    @GetMapping("/api/address")
    public ResponseEntity<AddressDto> getAddress(@RequestParam("zipcode") String zipcode) {
        if (zipcode == null || zipcode.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(ZIPCLOUD_URL, String.class, zipcode);

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            return ResponseEntity.ok(null);
        }

        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode results = root.get("results");
            if (results == null || !results.isArray() || results.size() == 0) {
                return ResponseEntity.ok(null);
            }

            JsonNode first = results.get(0);
            AddressDto dto = new AddressDto();
            dto.setPostalCode(text(first.get("zipcode")));
            dto.setPrefecture(text(first.get("address1"))); // 都道府県
            dto.setCity(text(first.get("address2")));       // 市区町村
            dto.setAddress1(text(first.get("address3")));   // 町域

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
    }

    private String text(JsonNode node) {
        return node == null ? "" : node.asText("");
    }
}
