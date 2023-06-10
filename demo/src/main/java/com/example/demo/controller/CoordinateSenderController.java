package com.example.demo.controller;

import com.example.demo.model.RefreshConfig;
import com.example.demo.service.RefreshPeriodService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/coordinates")
public class CoordinateSenderController {

    private  final RefreshPeriodService refreshPeriodService;
    private  final UserService userService;
    public CoordinateSenderController(RefreshPeriodService refreshPeriodService, UserService userService) {

        this.refreshPeriodService = refreshPeriodService;
        this.userService = userService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_CENTER', 'ROLE_ADMIN_SISTEM')")
    @PostMapping("/set_refresh_period")
    public void sendSampleCoordinate(@RequestBody int speedInMSeconds) throws JsonProcessingException {
        RefreshConfig refreshConfig = refreshPeriodService.findByUserId(userService.getCurrentUser().getId());
        System.out.println(refreshConfig.getRefreshPeriod());
        if(refreshConfig == null){
            RefreshConfig newRefreshConfig = new RefreshConfig(userService.getCurrentUser().getId(), (speedInMSeconds/1000));
            refreshPeriodService.save(newRefreshConfig);
        }else {
            refreshConfig.setRefreshPeriod((speedInMSeconds/1000));
            refreshPeriodService.save(refreshConfig);
        }

    }
}
