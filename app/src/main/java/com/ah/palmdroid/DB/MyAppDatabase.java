package com.ah.palmdroid.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {NilaiW.class,CenterRandom.class,MinMax.class,ThresholdSpread.class},version = 1)
public abstract class MyAppDatabase extends RoomDatabase

{
    public abstract MyDao myDao();

}
