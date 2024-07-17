package com.erickfelix.contactaggregatorservice.client;

import com.erickfelix.contactaggregatorservice.external.KenectLabsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kenectLabsClient",
        url = "https://k-messages-api.herokuapp.com/api/v1",
        configuration = FeignClientConfig.class)
public interface KenectLabsClient {

    @GetMapping("/contacts")
    ResponseEntity<KenectLabsResponse> getContacts(@RequestParam("page") int page);
}
