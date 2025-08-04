package org.unizd.rma.colic.network

import org.unizd.rma.colic.model.CatFact
import org.unizd.rma.colic.model.CatFactResponse
import retrofit2.http.GET

interface CatFactService {
    @GET("facts")
    suspend fun getCatFacts(): CatFactResponse

    @GET("fact")
    suspend fun getRandomFact(): CatFact

}
