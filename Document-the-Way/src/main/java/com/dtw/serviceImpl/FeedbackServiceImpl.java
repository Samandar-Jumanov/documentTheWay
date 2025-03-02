package com.dtw.serviceImpl;

import com.dtw.dtos.requestDtos.FeedbackRequestDto;
import com.dtw.dtos.responseDtos.FeedbackResponseDto;
import com.dtw.entity.Feedback;
import com.dtw.entity.User;
import com.dtw.mapper.FeedBackMapper;
import com.dtw.repo.FeedbackRepo;
import com.dtw.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class FeedbackServiceImpl {

    @Autowired
    private FeedbackRepo  feedbackRepo;

    @Autowired
    private UserRepo userRepo;

    public FeedbackResponseDto create(FeedbackRequestDto feedbackDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User currentUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));



        Feedback feedbackEntity = FeedBackMapper.MAPPER.mapToFeedback(feedbackDto);
        feedbackEntity.setUser(currentUser);
        System.out.println(feedbackEntity.getFeedbackValue());
        System.out.println(feedbackEntity.getUser().getFullName());
        System.out.println(feedbackEntity.getSolution());
        return  FeedBackMapper.MAPPER.mapToFeedbackDto( feedbackRepo.save(feedbackEntity));


    }

    public List<FeedbackResponseDto> findNotNull() {
        return feedbackRepo.findBySolutionIsNotNull().map(FeedBackMapper.MAPPER::mapToFeedbackDto)
                .collect(Collectors.toList());
    }


    public List<FeedbackResponseDto> getAll(){
        return  feedbackRepo.findAll().stream()
                .map(FeedBackMapper.MAPPER::mapToFeedbackDto)
                .collect(Collectors.toList());
    }


}
