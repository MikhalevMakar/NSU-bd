package ru.nsu.ccfit.mikhalev.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.mikhalev.dao.entity.Student;
import ru.nsu.ccfit.mikhalev.dao.repository.StudentRepository;
import ru.nsu.ccfit.mikhalev.dto.StudentDto;
import ru.nsu.ccfit.mikhalev.exception.NoSuchGroupException;
import ru.nsu.ccfit.mikhalev.exception.UserExistsException;
import ru.nsu.ccfit.mikhalev.mapper.StudentMapper;
import ru.nsu.ccfit.mikhalev.service.GroupService;
import ru.nsu.ccfit.mikhalev.service.StudentService;

@Slf4j
@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    @Qualifier("groupServiceImpl")
    private final GroupService groupService;


    @Override
    public void save(StudentDto studentDto) throws UserExistsException,
                                                   NoSuchGroupException {
        log.info("save new student");
        checkIfStudentExists(studentDto);
        Student student = studentMapper.mapToEntity(studentDto);
        student.setGroup(groupService.findGroupByNumber(studentDto.numberGroup()));
        studentRepository.save(student);
    }

    private void checkIfStudentExists(StudentDto studentDto) throws UserExistsException {
        if (studentRepository.existsStudentByNames(studentDto.firstName(),
                                                   studentDto.middleName(),
                                                   studentDto.lastName()))
            throw new UserExistsException();
    }
}
