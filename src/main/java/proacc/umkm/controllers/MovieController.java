package proacc.umkm.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/movies")
public class MovieController {

    @GetMapping("")
    public ResponseEntity<String> index() throws JSONException {
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Content-Type", "application/json");

        JSONObject response = new JSONObject();
        response.put("id", 1);
        response.put("name", "The Avengers");
        return ResponseEntity.ok().headers(responseHeader).body(response.toString());
    }

    @PostMapping("")
    public ResponseEntity<String> store(@RequestBody String req) throws JSONException {
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Content-Type", "application/json");

        JSONObject request = new JSONObject(req);
        JSONObject response = new JSONObject();
        response.put("name", request.get("name"));
        return ResponseEntity.ok().headers(responseHeader).body(response.toString());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> store(@RequestBody String req, @PathVariable String id) throws JSONException {
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("Content-Type", "application/json");

        JSONObject request = new JSONObject(req);
        JSONObject response = new JSONObject();
        response.put("id", id);
        response.put("name", request.get("name"));
        return ResponseEntity.ok().headers(responseHeader).body(response.toString());
    }
}
