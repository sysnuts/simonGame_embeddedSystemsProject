package com.example.embeddedprojectsimon.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_results")
data class MatchResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "sequence_length") val sequenceLength: Int, // La lunghezza della sequenza corretta
    @ColumnInfo(name = "game_sequence") val gameSequence: String // Tutta la sequenza di gioco, compreso l'errore
)