package api.demo_web_api.services.impl;

import api.demo_web_api.models.entities.Comment;
import api.demo_web_api.models.entities.Homework;
import api.demo_web_api.models.service.CommentServiceModel;
import api.demo_web_api.models.service.HomeworkServiceModel;
import api.demo_web_api.repositories.CommentRepository;
import api.demo_web_api.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addComment(CommentServiceModel commentServiceModel) {
        Homework homework = this.modelMapper.map(commentServiceModel.getHomework(),Homework.class);
        Comment comment = this.commentRepository.saveAndFlush(this.modelMapper.map(commentServiceModel, Comment.class));
        homework.getComments().add(comment);
    }
}
