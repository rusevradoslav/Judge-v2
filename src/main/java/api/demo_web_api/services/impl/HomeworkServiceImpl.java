package api.demo_web_api.services.impl;

import api.demo_web_api.models.entities.Homework;
import api.demo_web_api.models.service.HomeworkServiceModel;
import api.demo_web_api.repositories.HomeworkRepository;
import api.demo_web_api.services.HomeworkService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final ModelMapper modelMapper;



    @Override
    public HomeworkServiceModel addHomework(HomeworkServiceModel homeworkServiceModel) {

        return this.modelMapper
                .map(this.homeworkRepository.saveAndFlush(this.modelMapper.map(homeworkServiceModel, Homework.class))
                        ,HomeworkServiceModel.class);
    }
}
