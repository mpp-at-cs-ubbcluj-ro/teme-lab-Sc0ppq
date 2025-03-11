import ro.mpp2024.model.ComputerRepairRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComputerRepairRequestTest {

@Test
    void testConstructorAndGetters() {
    ComputerRepairRequest request = new ComputerRepairRequest(1,"Pop Andrei","Strada 1","12345678","Dell","04/03/2025","Display");
    assertEquals(1,request.getID());
    assertEquals("Pop Andrei", request.getOwnerName());
    assertEquals("Strada 1", request.getOwnerAddress());
    assertEquals("12345678", request.getPhoneNumber());
    assertEquals("Dell", request.getModel());
    assertEquals("04/03/2025",request.getDate());
    assertEquals("Display", request.getProblemDescription());
}

@Test
    void testSettersAndString() {
    ComputerRepairRequest request = new ComputerRepairRequest();
    request.setID(1);
    request.setOwnerName("Pop Andrei");
    request.setOwnerAddress("Strada 1");
    request.setPhoneNumber("12345678");
    request.setModel("Dell");
    request.setDate("04/03/2025");
    request.setProblemDescription("Display");

    String expected = "ID=1, ownerName='Pop Andrei', model='Dell', date='04/03/2025', problemDescription='Display'";
    assertEquals(expected, request.toString());
}

}

