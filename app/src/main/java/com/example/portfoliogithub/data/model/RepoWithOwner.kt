package com.example.portfoliogithub.data.model

data class RepoWithOwner (
    var owner: User,
    var listRepo: List<Repo>
)