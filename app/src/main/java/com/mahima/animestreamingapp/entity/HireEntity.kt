package com.mahima.animestreamingapp.entity

data class HireEntity (
    val userId : String ?= null,
    val petcaretaker : List<PetCareTakerHire> ?= null
)