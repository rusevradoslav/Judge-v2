package api.demo_web_api.services;


import api.demo_web_api.models.service.CommentServiceModel;
import api.demo_web_api.models.service.UserServiceModel;
import api.demo_web_api.models.view.UserProfileViewModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

public interface CommentService {

    void addComment(CommentServiceModel commentServiceModel);

}
