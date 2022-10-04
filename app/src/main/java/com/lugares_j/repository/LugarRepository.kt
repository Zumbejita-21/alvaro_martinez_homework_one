package com.lugares_j.repository

import androidx.lifecycle.LiveData
import com.lugares_j.data.LugarDao
import com.lugares_j.model.Lugar

class LugarRepository(private val lugarDao: LugarDao) {

    suspend fun saveLugar(lugar: Lugar) {
        if (lugar.id == 0) {
            lugarDao.addLugar(lugar) // genera un lugar nuevo

        } else {
            lugarDao.updateLugar(lugar)
        }
    }

    suspend fun deleteLugar(lugar: Lugar) {

        if (lugar.id != 0) {
            lugarDao.deleteLugar(lugar) // lo elimina si existe

        }
    }

    val getLugares : LiveData<List<Lugar>> = lugarDao.getLugares() // obtiene todos los lugares
}