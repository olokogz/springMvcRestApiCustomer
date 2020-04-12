
import main.model.entity.Address;


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

    }


}
