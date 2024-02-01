package com.sayphn.springrest.todos;

public record TodoResponse(int userId, int id, String title, boolean completed) {
}
