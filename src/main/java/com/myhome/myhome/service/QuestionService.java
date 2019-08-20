package com.myhome.myhome.service;

import com.myhome.myhome.dto.PaginationDTO;
import com.myhome.myhome.dto.QuestionDTO;
import com.myhome.myhome.exception.CustomizeErrorCode;
import com.myhome.myhome.exception.CustomizeException;
import com.myhome.myhome.mapper.QuestionMapper;
import com.myhome.myhome.mapper.UserMapper;
import com.myhome.myhome.model.Question;
import com.myhome.myhome.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO listQuestion(Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        Integer totalCount = questionMapper.count();
        Integer totalPage;

        if (totalCount%size==0){
            totalPage  = totalCount / size;
        }else {
            totalPage  = totalCount / size + 1;
        }
        if (page<1){
            page=1;
        }
        if (totalPage==0){
            totalPage=1;
        }
        if (page>totalPage){
            page=totalPage;
        }


        paginationDTO.setPagination(totalPage,page);
        //分页
        Integer offset=size*(page-1);

        List<Question> questions =questionMapper.listQuestion(offset,size);
        List<QuestionDTO> questionDTOList =new ArrayList<>();
        for(Question question: questions){
            User user =userMapper.findById(question.getCreater());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        //将问题属性加入Page的数据
        paginationDTO.setData(questionDTOList);



        return paginationDTO;
    }

    public PaginationDTO listQuestion(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        Integer totalCount = questionMapper.countByUserId(userId);
        Integer totalPage;

        if (totalCount%size==0){
            totalPage  = totalCount / size;
        }else{
            totalPage  = totalCount / size + 1;
        }
        if (totalPage==0){
            totalPage=1;
        }
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        //分页
        Integer offset=size*(page-1);

        List<Question> questions =questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList =new ArrayList<>();
        for(Question question: questions){
            User user =userMapper.findById(question.getCreater());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        //将问题属性加入Page的数据
        paginationDTO.setData(questionDTOList);



        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {

        Question question =questionMapper.getById(id);
        if (question == null){
            throw new CustomizeException("问题不存在or问题已被删除");
        }

        QuestionDTO questionDTO =new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.findById(questionDTO.getCreater());
        questionDTO.setUser(user);
        return questionDTO;

    }

    public void createOrUpdate(Question question) {
        if (question.getId()==null){
            questionMapper.create(question);
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
        }else{
            question.setGmtModified(question.getGmtCreate());
            if (questionMapper.getById(question.getId())!=null)
            questionMapper.update(question);
            else
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);

        }
    }

    public void inView(Integer count,Integer id) {
        Question question=questionMapper.getById(id);
        questionMapper.updateViewCount(count,id);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO questionDTO) {
        if (questionDTO.getTag()==null||questionDTO.getTag()==""){
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(questionDTO.getTag(),",");
        String regexTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        List<Question> questions = questionMapper.selectTag(questionDTO.getId(), regexTag);
        List<QuestionDTO> questionDTOS = questions.stream().map(q->{
            QuestionDTO questionDTO1 =new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO1);
            return questionDTO1;
        }).collect(Collectors.toList());

        return questionDTOS;
    }

//    public void updateQuestionCommentNum(Integer size,Integer id) {
//        questionMapper.updateCommentCount(size, id);
//    }
}
