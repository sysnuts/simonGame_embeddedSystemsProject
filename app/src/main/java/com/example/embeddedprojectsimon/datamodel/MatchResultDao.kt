package com.example.embeddedprojectsimon.datamodel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchResultDao {
    @Insert
    suspend fun insertMatchResult(matchResult: MatchResultEntity)

    // Usiamo un Flow per poter osservare i cambiamenti in tempo reale, cosi da aggiornare la UI automaticamente quando i dati cambiano
    // non serve usare suspend per le query che restituiscono un Flow, poiché Room gestisce automaticamente l'esecuzione su thread separati
    @Query("SELECT * FROM match_results")
    fun getAllMatchResults(): Flow<List<MatchResultEntity>>
}

