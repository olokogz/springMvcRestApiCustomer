
import main.model.FilmRental;
import main.model.entity.Address;


import main.model.entity.Film;
import main.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class UtilsTest {


    @Test
    public void findUpdatedFieldsTest()
    {
        Address address = new Address("test",null,null,null,null, Timestamp.valueOf(LocalDateTime.now()));
         Assertions.assertEquals(2, Utils.findUpdatedFields(address).size());

    }

    @Test
    public void prepareUpdateObjectTest() throws IllegalAccessException, IntrospectionException, InvocationTargetException, NoSuchFieldException {
        Address oldAddress = new Address("test","test","test","test","test", Timestamp.valueOf(LocalDateTime.now()));
        Address newAddress = new Address("newTest",null,null,null,null, Timestamp.valueOf(LocalDateTime.now()));

        Assertions.assertEquals("newTest", ((Address)Utils.prepareUpdateObject(Utils.findUpdatedFields(newAddress),newAddress,oldAddress)).getAddress());

        Film oldFilm = new Film(1,"test","test",0,0,0,0.0,Timestamp.valueOf(LocalDateTime.now()),0,FilmRental.RENTED,0,null,null,null);
        Film newFilm = new Film(1,"test","test",0,0,0,0.0,Timestamp.valueOf(LocalDateTime.now()),0,FilmRental.RENTED,1,new int[]{1,2},null,null);

        Film preparedFilm = ((Film)Utils.prepareUpdateObject(Utils.findUpdatedFields(newFilm),newFilm,oldFilm));
        Assertions.assertEquals(1,((Film)Utils.prepareUpdateObject(Utils.findUpdatedFields(newFilm),newFilm,oldFilm)).getLanguage_id());

        Assertions.assertEquals(2,preparedFilm.getActor_id().length);

        Assertions.assertEquals(1,preparedFilm.getActor_id()[0]);

    }


}
