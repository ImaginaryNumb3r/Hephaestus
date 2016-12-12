package core.util.contracts;

import core.util.contracts.exceptions.ContractException;
import org.junit.Test;

import java.util.function.BooleanSupplier;

/**
 * Creator: Patrick
 * Created: 11.12.2016
 * Purpose:
 */
public class ResultContractTest {

    @Test
    public void testContract() throws Exception {
        final Object mayNotBeNull = null;

        BooleanSupplier booleanSupplier = () -> mayNotBeNull == null;

        try (Contract x = new Contract(booleanSupplier)){

        } catch (ContractException ex){

        }


    }
}