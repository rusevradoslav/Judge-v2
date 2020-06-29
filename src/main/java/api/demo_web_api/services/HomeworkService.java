package api.demo_web_api.services;

import api.demo_web_api.models.service.HomeworkServiceModel;

import java.util.List;

public interface HomeworkService {
    HomeworkServiceModel addHomework(HomeworkServiceModel homeworkServiceModel);

    HomeworkServiceModel findFirstMinComments(String id);

    HomeworkServiceModel findById(String homeworkId);

    String findAllHomeWorksByUserId(String id);
}
