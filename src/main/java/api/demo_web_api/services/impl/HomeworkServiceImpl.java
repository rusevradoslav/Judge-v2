package api.demo_web_api.services.impl;

import api.demo_web_api.models.entities.Homework;
import api.demo_web_api.models.service.HomeworkServiceModel;
import api.demo_web_api.repositories.HomeworkRepository;
import api.demo_web_api.services.HomeworkService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final ModelMapper modelMapper;


    @Override
    public HomeworkServiceModel addHomework(HomeworkServiceModel homeworkServiceModel) {

        return this.modelMapper
                .map(this.homeworkRepository.saveAndFlush(this.modelMapper.map(homeworkServiceModel, Homework.class))
                        , HomeworkServiceModel.class);
    }

    @Override
    public HomeworkServiceModel findFirstMinComments(String id) {
        System.out.println();
      /*  List<HomeworkServiceModel> list = this.homeworkRepository
                .findAll()
                .stream()
                .filter(homework -> !homework.getAuthor().getId().equals(id))
               .min(Comparator.comparingInt(a->a.getComments().size()))*//*
                .map(homework -> this.modelMapper.map(homework, HomeworkServiceModel.class)).collect(Collectors.toList());*/

       HomeworkServiceModel homeworkServiceModel = this.homeworkRepository
                .findAll()
                .stream()
                .filter(homework -> !homework.getAuthor().getId().equals(id))
                .min(Comparator.comparingInt(a->a.getComments().size()))
                .map(homework -> this.modelMapper.map(homework, HomeworkServiceModel.class)).orElse(null);

        return homeworkServiceModel;
    }

    @Override
    public HomeworkServiceModel findById(String homeworkId) {
        return this.homeworkRepository.findById(homeworkId).map(homework -> modelMapper.map(homework, HomeworkServiceModel.class)).orElse(null);
    }

    @Override
    public String findAllHomeWorksByUserId(String id) {

        return this.homeworkRepository
                .findAll()
                .stream()
                .filter(homework -> homework.getAuthor().getId().equals(id))
                .map(homework -> this.modelMapper.map(homework,HomeworkServiceModel.class))
                .map(homeworkServiceModel -> {
                    String chars = homeworkServiceModel.getGitAddress();
                    int begin = chars.lastIndexOf("/") + 1;
                    int end = chars.length();
                    return String.format("%s%n",chars.substring(begin, end));
                }).collect(Collectors.joining());

    }
}
