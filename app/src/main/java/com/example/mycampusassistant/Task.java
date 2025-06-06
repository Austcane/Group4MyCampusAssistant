package com.example.mycampusassistant;

public class Task {
    String title;
    String dueDate;

    public Task(String title, String dueDate) {
        this.title = title;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDueDate() {
        return dueDate;
    }
}
