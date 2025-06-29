package com.evg.todo_list.domain.model

data class TestModel(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)