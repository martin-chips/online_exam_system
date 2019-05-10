package com.dimple.service.impl;

import com.dimple.dao.ExamQuestionDao;
import com.dimple.dao.ExamRecordDao;
import com.dimple.dao.ExamStudentDao;
import com.dimple.dao.QuestionDao;
import com.dimple.dao.SysUserDao;
import com.dimple.entity.Exam;
import com.dimple.dao.ExamDao;
import com.dimple.entity.ExamQuestion;
import com.dimple.entity.ExamRecord;
import com.dimple.entity.ExamStudent;
import com.dimple.entity.Question;
import com.dimple.entity.StudentExamDetail;
import com.dimple.service.ExamService;
import com.dimple.utils.Convert;
import com.sun.tools.internal.xjc.model.nav.EagerNClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 试卷表(Exam)表服务实现类
 *
 * @author makejava
 * @since 2019-05-01 11:39:00
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamDao examDao;
    @Autowired
    private ExamQuestionDao examQuestionDao;
    @Autowired
    ExamStudentDao examStudentDao;
    @Autowired
    QuestionDao questionDao;
    @Autowired
    ExamRecordDao examRecordDao;
    @Autowired
    SysUserDao sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param examId 主键
     * @return 实例对象
     */
    @Override
    public Exam queryById(Integer examId) {
        Exam exam = this.examDao.queryById(examId);
        if (exam == null) {
            return null;
        }
        //设置其日期的格式
        List<ExamQuestion> examQuestions = examQuestionDao.selectExamQuestionListByExamId(examId);
        Integer ids[] = new Integer[examQuestions.size()];
        String idsStr = "";
        for (int i = 0; i < examQuestions.size(); i++) {
            ids[i] = examQuestions.get(i).getQuestionId();
            idsStr += examQuestions.get(i).getQuestionId() + ",";
        }
        exam.setIds(ids);
        exam.setIdsStr(idsStr);
        //查询出需要参加考试的学生的信息
        ExamStudent examStudent = new ExamStudent();
        examStudent.setExamId(examId);
        List<ExamStudent> examStudents = examStudentDao.queryAll(examStudent);
        Integer[] studentIds = new Integer[examStudents.size()];
        for (int i = 0; i < examStudents.size(); i++) {
            studentIds[i] = examStudents.get(i).getStudentId();
        }
        exam.setStudentIds(studentIds);
        return exam;
    }


    /**
     * 新增数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public int insert(Exam exam) {
        Integer[] ids = exam.getIds();
        int insert = this.examDao.insert(exam);
        double score = 0;
        if (ids != null) {
            //设置exam和question的关联
            for (Integer id : ids) {
                ExamQuestion examQuestion = new ExamQuestion();
                examQuestion.setExamId(exam.getExamId());
                examQuestion.setQuestionId(id);
                examQuestionDao.insert(examQuestion);
                //设置试卷的总分
                Question question = questionDao.queryById(id);
                score += question.getScore();
            }
        }
        //更新其总分
        examDao.updateScoreById(score, exam.getExamId());
        exam.setScore(score);
        //获取参加考试的学生的信息
        Integer[] studentIds = exam.getStudentIds();
        if (studentIds != null) {
            for (Integer studentId : studentIds) {
                ExamStudent examStudent = new ExamStudent();
                examStudent.setExamId(exam.getExamId());
                examStudent.setStudentId(studentId);
                examStudentDao.insert(examStudent);
            }
        }
        return insert;
    }

    /**
     * 修改数据
     *
     * @param exam 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public int update(Exam exam) {
        //删除关联
        Integer[] ids = exam.getIds();
        //删除关联，根据examid
        examQuestionDao.deleteByExamId(exam.getExamId());
        //删除和学生的关联
        examStudentDao.deleteByExamId(exam.getExamId());
        double score = 0;
        //重新设置关联
        if (ids != null) {
            for (Integer id : ids) {
                ExamQuestion examQuestion = new ExamQuestion();
                examQuestion.setExamId(exam.getExamId());
                examQuestion.setQuestionId(id);
                examQuestionDao.insert(examQuestion);
                score += questionDao.queryById(id).getScore();
            }
        }
        exam.setScore(score);
        //重置和学生的关联
        if (exam.getStudentIds() != null) {
            for (Integer studentId : exam.getStudentIds()) {
                ExamStudent examStudent = new ExamStudent();
                examStudent.setExamId(exam.getExamId());
                examStudent.setStudentId(studentId);
                examStudentDao.insert(examStudent);
            }
        }
        return this.examDao.update(exam);
    }

    /**
     * 通过主键删除数据
     *
     * @param examId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer examId) {
        return this.examDao.deleteById(examId) > 0;
    }

    @Override
    @Transactional
    public int deleteByIds(String ids) {
        Integer[] examIds = Convert.toIntArray(ids);
        return examDao.deleteByIds(examIds);
    }

    @Override
    public List<Exam> findExamList(Exam exam) {
        return examDao.queryAll(exam);
    }

    @Override
    public StudentExamDetail getExamForStudentByExamId(Integer examId, Integer userId) {

        List<ExamQuestion> examQuestions = examQuestionDao.selectExamQuestionListByExamId(examId);

        Exam exam = examDao.queryById(examId);
        //新建5中类型的List集合
        List<Question> radioQuestion = new ArrayList<>();
        List<Question> checkboxQuestion = new ArrayList<>();
        List<Question> blackQuestion = new ArrayList<>();
        List<Question> judgeQuestion = new ArrayList<>();
        List<Question> shortQuestion = new ArrayList<>();

        StudentExamDetail studentExamDetail = new StudentExamDetail();

        //设置试卷信息
        studentExamDetail.setExamName(exam.getExamName());
        studentExamDetail.setLastTime(exam.getExamLastTime());
        studentExamDetail.setStartDate(exam.getExamStartDate());
        studentExamDetail.setExamId(examId);

        //试卷分数
        double score = 0;
        for (ExamQuestion examQuestion : examQuestions) {
            Question question = questionDao.queryById(examQuestion.getQuestionId());
            //查询出已经保存的有的数据，方便页面回显
            ExamRecord examRecord = examRecordDao.selectRecordByExamIdAndQuestionIdAndStuId(examId, question.getId(), userId);
            String answer = "";
            if (examRecord == null) {
                question.setFinalScore(0D);
            } else {
                question.setFinalScore(examRecord.getFinalScore());
                answer = examRecord.getAnswer();
            }
            score += question.getScore();

            setAnswer(answer, question);

            if ("1".equals(question.getType())) {
                //获取单选
                radioQuestion.add(question);
            } else if ("2".equals(question.getType())) {
                //获取多选
                checkboxQuestion.add(question);
            } else if ("3".equals(question.getType())) {
                //获取填空
                blackQuestion.add(question);
            } else if ("4".equals(question.getType())) {
                //获取判断
                judgeQuestion.add(question);
            } else if ("5".equals(question.getType())) {
                //获取简答
                shortQuestion.add(question);
            }
        }

        studentExamDetail.setCheckboxQuestion(checkboxQuestion);
        studentExamDetail.setRadioQuestion(radioQuestion);
        studentExamDetail.setShortQuestion(shortQuestion);
        studentExamDetail.setJudgeQuestion(judgeQuestion);
        studentExamDetail.setBalckQuestion(blackQuestion);

        studentExamDetail.setScore(score);

        return studentExamDetail;
    }

    @Override
    public List<Exam> findExamListForStu(Exam exam, Integer id) {
        List<Exam> examList = findExamList(exam);
        List<Exam> returnExams = new ArrayList<>();
        for (Exam temp : examList) {
            ExamStudent examStudent = examStudentDao.selectByExamIdAndStuId(temp.getExamId(), id);
            //说明没有指定该学生可以考试
            if (examStudent == null) {
                //删除此条记录
                continue;
            } else if ("0".equals(examStudent.getStatus())) {
                //说明还没有做，可以做
                temp.setAccessed(false);
            } else if ("1".equals(examStudent.getStatus())) {
                //说明已经做过了，只能显示不能做
                temp.setAccessed(true);
            }
            temp.setTotalScore(examStudent.getTotalScore());
            returnExams.add(temp);
        }
        return returnExams;
    }

    @Override
    public List<Exam> findExamListToReview(Exam exam) {
        List<Exam> examList = findExamList(exam);
        List<Exam> examsReturn = new ArrayList<>();
        for (Exam temp : examList) {
            Integer examId = temp.getExamId();
            List<ExamStudent> examStudents = examStudentDao.selectByExamId(examId);
            for (ExamStudent examStudent : examStudents) {
                //过滤掉还未参加考试的学生
                if ("0".equals(examStudent.getStatus())) {
                    continue;
                }
                Exam returnExam = new Exam();
                returnExam.setExamId(examId);
                returnExam.setReviewerId(temp.getReviewerId());
                returnExam.setStudentId(examStudent.getStudentId());
                returnExam.setExamName(temp.getExamName());
                returnExam.setStudentName(sysUserDao.selectUserById(examStudent.getStudentId()).getLoginName());
                returnExam.setReading(examStudent.getReading());
                returnExam.setExamStartDate(temp.getExamStartDate());
                examsReturn.add(returnExam);
            }
        }
        return examsReturn;
    }

    @Override
    public StudentExamDetail findExamDetailToReview(Integer examId, Integer stuId) {

        List<ExamQuestion> examQuestions = examQuestionDao.selectExamQuestionListByExamId(examId);
        Exam exam = examDao.queryById(examId);
        List<Question> radioQuestion = new ArrayList<>();
        List<Question> checkboxQuestion = new ArrayList<>();
        List<Question> blackQuestion = new ArrayList<>();
        List<Question> judgeQuestion = new ArrayList<>();
        List<Question> shortQuestion = new ArrayList<>();

        StudentExamDetail studentExamDetail = new StudentExamDetail();
        studentExamDetail.setExamName(exam.getExamName());
        studentExamDetail.setLastTime(exam.getExamLastTime());
        studentExamDetail.setStartDate(exam.getExamStartDate());
        studentExamDetail.setExamId(examId);


        //获取学生总成绩
        ExamStudent examStudent = examStudentDao.selectByExamIdAndStuId(examId, stuId);
        //设置学生总成绩
        studentExamDetail.setTotalScore(examStudent.getTotalScore());

        double score = 0;

        for (ExamQuestion examQuestion : examQuestions) {
            Question question = questionDao.queryById(examQuestion.getQuestionId());
            //查询出已经保存的有的数据，方便页面回显
            ExamRecord examRecord = examRecordDao.selectRecordByExamIdAndQuestionIdAndStuId(examId, question.getId(), stuId);
            String answer = "";
            if (examRecord == null) {
                continue;
            }
            question.setFinalScore(examRecord.getFinalScore());
            answer = examRecord.getAnswer();

            setAnswer(answer, question);

            score += question.getScore();
            if ("1".equals(question.getType())) {
                //获取单选
                radioQuestion.add(question);
            } else if ("2".equals(question.getType())) {
                //获取单选
                checkboxQuestion.add(question);
            } else if ("3".equals(question.getType())) {
                //获取填空
                blackQuestion.add(question);
            } else if ("4".equals(question.getType())) {
                //获取判断
                judgeQuestion.add(question);
            } else if ("5".equals(question.getType())) {
                //获取简答
                shortQuestion.add(question);
            }
        }

        studentExamDetail.setCheckboxQuestion(checkboxQuestion);
        studentExamDetail.setRadioQuestion(radioQuestion);
        studentExamDetail.setShortQuestion(shortQuestion);
        studentExamDetail.setJudgeQuestion(judgeQuestion);
        studentExamDetail.setBalckQuestion(blackQuestion);

        studentExamDetail.setScore(score);
        return studentExamDetail;
    }

    /**
     * 设置答案
     *
     * @param answer
     * @param question
     */
    private void setAnswer(String answer, Question question) {
        //单选 多选ABC
        switch (question.getType()) {
            case "1":
            case "2":
                String[] split = answer.split(",");
                for (String s : split) {
                    if ("A".equals(s)) {
                        question.setOptionACheckedStu("A");
                    } else if ("B".equals(s)) {
                        question.setOptionBCheckedStu("B");
                    } else if ("C".equals(s)) {
                        question.setOptionCCheckedStu("C");
                    } else if ("D".equals(s)) {
                        question.setOptionDCheckedStu("D");
                    }
                }
                break;
            //判断
            case "4":
                if ("1".equals(answer)) {
                    question.setJudgeAnswer1Stu("1");
                } else if ("0".equals(answer)) {
                    question.setJudgeAnswer0Stu("0");
                }
                break;
            case "3":
            case "5":
                question.setTextAnswerStu(answer);
                break;
            default:
                break;
        }
    }
}