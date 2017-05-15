package de.belmega.stocksim.trade;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class OrderTest {

    @Test
    public void testThatOrderDoesNotExpireByDefault() throws Exception {
        //arrange
        Order order = Bid.create(null, null);

        //act
    
        //assert
        assertThat(order.getExpiryDate().isPresent(), is(equalTo(false)));
    }

    @Test
    public void testThatOrderMayExpire() throws Exception {
        //arrange
        Order order = Bid.create(null, null);
        LocalDateTime expiryDate = LocalDateTime.now();

        //act
        order.setExpiryDate(expiryDate);

        //assert
        assertThat(order.getExpiryDate().get(), is(equalTo(expiryDate)));
    }
}
