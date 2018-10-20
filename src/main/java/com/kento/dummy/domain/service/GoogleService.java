package com.kento.dummy.domain.service;

import com.kento.dummy.domain.model.GoogleCalender;
import com.kento.dummy.domain.model.GoogleTask;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class GoogleService {

    @Autowired
    GoogleTask googleTask;

    public List<GoogleTask> findAll() {
        return new ArrayList<>();
    }
}
