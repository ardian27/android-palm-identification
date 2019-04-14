package com.ah.palmdroid.DB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDao {



    @Insert
    public void addNilaiW(NilaiW nilaiW);


    @Insert
    public void addCenterRandom(CenterRandom centerRandom);

    @Insert
    public void addMinMax(MinMax minMax);

    @Insert
    public void addThresholdSpread(ThresholdSpread thresholdSpread);


    @Query("select * from nilai_w")
    public List<NilaiW> getnilaiW();


    @Query("select * from center_random")
    public List<CenterRandom> getcenterRandom();

    @Query("select * from min_max")
    public List<MinMax> getminMax();

    @Query("select * from threshold_spread")
    public List<ThresholdSpread> getthresholdSpread();


    @Query("DELETE FROM nilai_w")
    public void deletenilaiW();


    @Query("DELETE FROM center_random")
    public void deletecenterRandom();


    @Query("DELETE FROM min_max")
    public void deleteminMax();


    @Query("DELETE FROM threshold_spread")
    public void deletethresholdSpread();

}
