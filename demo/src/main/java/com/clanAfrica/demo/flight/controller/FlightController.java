package com.clanAfrica.demo.flight.controller;

import com.clanAfrica.demo.Exception.NotFoundException;
import com.clanAfrica.demo.flight.data.model.Flight;
import com.clanAfrica.demo.flight.dto.RegisterFlightResponse;
import com.clanAfrica.demo.flight.dto.request.RegisterFlightRequest;
import com.clanAfrica.demo.flight.dto.response.BaseResponse;
import com.clanAfrica.demo.flight.dto.response.FightListResponse;
import com.clanAfrica.demo.flight.service.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flight")
@AllArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> register(@RequestBody RegisterFlightRequest registerFlightRequest){
        Flight response = flightService.registerFlight(registerFlightRequest);
        return new ResponseEntity<>(
                new RegisterFlightResponse(true, HttpStatus.CREATED.value(), "successful",
                                           LocalDateTime.now(), response)
                                          ,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> findFlightById(@PathVariable Long id){

        try {
            Flight foundFlight = flightService.findFlightById(id);

            return new ResponseEntity<>(
                    new RegisterFlightResponse(true, HttpStatus.OK.value(), "successful",
                            LocalDateTime.now(), foundFlight)
                    ,HttpStatus.CREATED);
        }
        catch (NotFoundException e){
            return new ResponseEntity<>(
                    new BaseResponse(false, HttpStatus.BAD_REQUEST.value(), "Fail",
                            LocalDateTime.now())
                    ,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<FightListResponse> findAllFlight(){
        {
            List<Flight> flights = flightService.findAllFlight();
            return new ResponseEntity<>(new FightListResponse(true,HttpStatus.FOUND.value(),
                    "succesful",LocalDateTime.now(),flights),HttpStatus.FOUND);
        }
    }



}
