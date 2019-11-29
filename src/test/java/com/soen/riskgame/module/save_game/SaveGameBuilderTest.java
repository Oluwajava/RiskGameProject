package com.soen.riskgame.module.save_game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.soen.riskgame.module.core.model.Country;

import junit.framework.Assert;
/**
 * Test class to test functionality of SaveGameBuilderTest
 * @author hitan
 *
 */
public class SaveGameBuilderTest {

    /**
     * Object of SaveGameBuilderTest
     */
    private SaveGameBuilder saveGameBuilderTest;

    /**
     * Before method to initialize the object and to call the setters
     */
    @Before
    public void before() {
        saveGameBuilderTest=new SaveGameBuilder();
        Country attackFromCountry=new Country();
        saveGameBuilderTest.setAttackFromCountry(attackFromCountry);
        saveGameBuilderTest.setAttackLog("sdfjsdgjfsj");
        Country attackToCountry=new Country();
        saveGameBuilderTest.setAttackToCountry(attackToCountry);
        saveGameBuilderTest.setAttackNumOfDice(3);
        saveGameBuilderTest.setFileName("name");
    }

    /**
     * Test method to test getAttackFromCountry
     */
    @Test
    public void getAttackFromCountrytest() {
        Assert.assertNotNull(saveGameBuilderTest.getAttackFromCountry());
    }
    /**
     * Method to test getFileName
     */
    @Test
    public void getFileNameTest() {
        Assert.assertNotNull(saveGameBuilderTest.getFileName());
    }
    /**
     * Method to test getAttackNumOfDice
     */
    @Test
    public void getNumberOfDice()
    {
        Assert.assertNotNull(saveGameBuilderTest.getAttackNumOfDice());
    }
    /**
     * Method to test getAttackToCountry
     */
    @Test
    public void getAttachToCountry() {
        Assert.assertNotNull(saveGameBuilderTest.getAttackToCountry());

    }
    /**
     * Method to test getAttackLog
     */
    @Test
    public void getAttackLogTest() {
        assertNotNull(saveGameBuilderTest.getAttackLog());
    }
}