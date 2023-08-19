package proacc.umkm.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proacc.umkm.utils.GlobalResponseInterceptor;

@RestController
@RequestMapping(value = "/api/v1/movies")
public class MovieController {

    @GetMapping("")
    public ResponseEntity<String> index() throws JSONException {
        JSONObject response = new JSONObject();
        response.put("id", 1);
        response.put("name", "The Avengers");
        return ResponseEntity.ok().body(response.toString());
    }

    @PostMapping("")
    public ResponseEntity<String> store(@RequestBody String req) throws JSONException {
        JSONObject request = new JSONObject(req);
        JSONObject response = new JSONObject();
        response.put("name", request.get("name"));
        return ResponseEntity.ok().body(response.toString());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> store(@RequestBody String req, @PathVariable String id) throws JSONException {
        JSONObject request = new JSONObject(req);
        JSONObject response = new JSONObject();
        response.put("id", id);
        response.put("name", request.get("name"));
        return ResponseEntity.ok().body(response.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroy(@RequestBody String req, @PathVariable String id) throws JSONException {
        JSONObject request = new JSONObject(req);
        JSONObject response = new JSONObject();
        response.put("id", id);
        response.put("name", request.get("name"));
        return ResponseEntity.ok().body(response.toString());
    }
}
