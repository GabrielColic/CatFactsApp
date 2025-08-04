package org.unizd.rma.colic.repository

import org.unizd.rma.colic.model.CatFact
import org.unizd.rma.colic.model.CatFactResponse
import org.unizd.rma.colic.network.RetrofitInstance

class CatFactRepository {
    suspend fun getRandomFact(): CatFact {
        return RetrofitInstance.api.getRandomFact()
    }

    suspend fun getCatFacts(): CatFactResponse {
        return RetrofitInstance.api.getCatFacts()
    }
}
