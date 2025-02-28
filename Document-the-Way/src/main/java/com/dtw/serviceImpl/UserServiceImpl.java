package com.dtw.serviceImpl;


import com.dtw.dtos.requestDtos.UserRequestDto;
import com.dtw.dtos.responseDtos.UserResponseDto;
import com.dtw.entity.RepostedDocument;
import com.dtw.entity.User;
import com.dtw.exception.ResourceNotFoundException;
import com.dtw.exception.UsernameTakenException;
import com.dtw.mapper.UserMapper;
import com.dtw.repo.RepostedDocumentRepo;
import com.dtw.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@Transactional
public class UserServiceImpl {


    @Autowired
    private RepostedDocumentRepo repostedDocumentRepo;
    @Autowired
    private final  UserRepo userRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    UserServiceImpl ( UserRepo repo ){
          this.userRepo = repo;
    }


    public UserResponseDto createUser (UserRequestDto userDto ){


        Optional<User> existingUser = this.userRepo.findByUsername(userDto.getUsername());

        if(existingUser.isPresent()){
            throw new UsernameTakenException("Username already taken");
        }

        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User userEntity = UserMapper.MAPPER.mapToUser(userDto);
        User newUser = this.userRepo.save(userEntity);
        return UserMapper.MAPPER.mapToUserResponseDto(newUser);

    }


    public List<UserResponseDto> getAllUsers(){
        List<User> users = this.userRepo.findAll();
       return  users.stream().map(UserMapper.MAPPER::mapToUserResponseDto)
                .collect(Collectors.toList());
    };

    public UserResponseDto updateUser(
            UserRequestDto userDto,
            Long id
    ){
        User foundUser = this.userRepo.findById(id)
                .orElseThrow( ( ) -> new ResourceNotFoundException("Not found " , "User" , id ));


        userDto.setPassword(encoder.encode(userDto.getPassword()));
        foundUser.setPassword(userDto.getPassword()); // hash before saving password
        foundUser.setUsername(userDto.getUsername());
        foundUser.setFullName(userDto.getFullName());

        this.userRepo.save(foundUser);
        return UserMapper.MAPPER.mapToUserResponseDto(foundUser);

    }

    public UserResponseDto getSingleUser ( Long id ){

          User foundUser = this.userRepo.findById(id)
                  .orElseThrow( ( ) -> new ResourceNotFoundException("Not found " , "User" , id ));


                  return UserMapper.MAPPER.mapToUserResponseDto(foundUser);

    }

    // get users who has same reposts

    public List<UserResponseDto> findUsersByRepost( Long id ){
        RepostedDocument repost = repostedDocumentRepo.findById(id)
                .orElseThrow(( ) -> new ResourceNotFoundException("Repost" , "Repost not found ", id));
        Stream<User> users = userRepo.findByReposts(repost);
        return users.map(UserMapper.MAPPER::mapToUserResponseDto)
                .collect(Collectors.toList());
    }



}

