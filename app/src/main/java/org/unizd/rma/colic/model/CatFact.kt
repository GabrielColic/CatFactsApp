package org.unizd.rma.colic.model

data class CatFact(
    val fact: String,
    val length: Int
)

data class CatFactResponse(
    val data: List<CatFact>
)
