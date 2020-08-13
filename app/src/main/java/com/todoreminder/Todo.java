package com.todoreminder;

public class Todo {
    String Title,Description;

    public Todo() {
    }

    public Todo(String title, String description) {
        Title = title;
        Description = description;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }
}
