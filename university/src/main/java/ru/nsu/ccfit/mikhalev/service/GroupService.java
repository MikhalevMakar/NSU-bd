package ru.nsu.ccfit.mikhalev.service;

import ru.nsu.ccfit.mikhalev.dao.entity.Group;
import ru.nsu.ccfit.mikhalev.exception.NoSuchGroupException;

public interface GroupService {

    Group findGroupByNumber(Long number) throws NoSuchGroupException;
}
