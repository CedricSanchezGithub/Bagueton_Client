package com.c3dev.bagueton.ui.model.beans

data class Commit(
    val sha: String,
    val commit: CommitDetails
)

data class CommitDetails(
    val message: String,
    val author: CommitAuthor
)

data class CommitAuthor(
    val name: String,
    val date: String
)
