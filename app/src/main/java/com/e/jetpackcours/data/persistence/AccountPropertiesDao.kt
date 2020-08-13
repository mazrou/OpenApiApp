package com.e.jetpackcours.data.persistence


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.e.jetpackcours.data.model.AccountProperties

@Dao
interface AccountPropertiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAndReplace( accountProperties: AccountProperties) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnore(accountProperties: AccountProperties) : Long

    @Query("SELECT * FROM account_properties WHERE pk = :pk")
    fun searchByPk(pk : Int) : AccountProperties?

    @Query("SELECT * FROM account_properties WHERE pk = :email")
    fun searchByEmail(email : String) : AccountProperties?
}