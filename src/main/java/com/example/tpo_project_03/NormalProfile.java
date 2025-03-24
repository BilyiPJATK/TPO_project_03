package com.example.tpo_project_03;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.List;

@Component
@Profile("Normal")
public class NormalProfile implements DisplayProfile {
    public void display(Entry entry) {
        System.out.println(entry);
    }
}

