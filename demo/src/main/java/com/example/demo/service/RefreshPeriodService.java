package com.example.demo.service;

import com.example.demo.model.RefreshConfig;
import com.example.demo.repository.RefreshPeriodRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class RefreshPeriodService {
    private final RefreshPeriodRepository refreshPeriodRepository;
    private  final UserService userService;


    public RefreshPeriodService(RefreshPeriodRepository refreshPeriodRepository, UserService userService) {
        this.refreshPeriodRepository = refreshPeriodRepository;
        this.userService = userService;
    }

    public RefreshConfig findByUserId(Long userId){
        return refreshPeriodRepository.findByUserId(userId);
    }

    public void save(RefreshConfig refreshConfig){refreshPeriodRepository.save(refreshConfig);
    }

    public int getRefreshPeriod(){
        return refreshPeriodRepository.findByUserId(userService.getCurrentUser().getId()).getRefreshPeriod();
    }

    @Async("threadPoolTaskExecutor") // Specify the executor bean name
    public CompletableFuture<Integer> findRefreshPeriodAsync(Long userId) {
        int refreshPeriod = refreshPeriodRepository.findByUserId(userId).getRefreshPeriod();
        return CompletableFuture.completedFuture(refreshPeriod);
    }
}
