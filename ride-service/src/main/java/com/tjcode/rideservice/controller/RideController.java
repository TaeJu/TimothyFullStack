package com.tjcode.rideservice.controller;

import com.tjcode.rideservice.entity.ImageModel;
import com.tjcode.rideservice.entity.Rider;
import com.tjcode.rideservice.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ride-service")
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/test")
    public String test() {
        return "test get call";
    }

    @PostMapping(value = "/registerRider", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Rider> registerRider(@RequestPart("rider") Rider rider,
                                               @RequestPart("imageRider") MultipartFile file) {

        try {
            ImageModel imageModel = uploadImage(file);
            rider.setImageModel(imageModel);
            rideService.registerRider(rider);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(rider);
    }

    @GetMapping("/getRiderDetailsById/{riderId}")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<Rider> getRiderDetailsById(@PathVariable("riderId") Integer riderId) {
        return ResponseEntity.ok(rideService.getRiderDetailsById(riderId));
    }

    @GetMapping("/getAllRider")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity<List<Rider>> getAllRider() {
        return ResponseEntity.ok(rideService.getAllRider());
    }

    public ImageModel uploadImage(MultipartFile multipartFile) throws IOException {
        ImageModel imageModel = new ImageModel(
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                multipartFile.getBytes()
        );

        return imageModel;
    }
}
