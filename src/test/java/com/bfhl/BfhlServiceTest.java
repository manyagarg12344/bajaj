package com.bfhl;

import com.bfhl.dto.BfhlRequest;
import com.bfhl.dto.BfhlResponse;
import com.bfhl.service.impl.BfhlServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BfhlServiceTest {

    private final BfhlServiceImpl service = new BfhlServiceImpl();

    @Test
    void testProcessData() {
        BfhlRequest request = new BfhlRequest();
        request.setData(List.of("a", "1", "334", "4", "R", "$"));

        BfhlResponse response = service.processData(request);

        assertTrue(response.isSuccess());

        assertEquals(List.of("1"), response.getOddNumbers());
        assertEquals(List.of("334", "4"), response.getEvenNumbers());
        assertEquals("339", response.getSum());

        assertEquals(List.of("A", "R"), response.getAlphabets());
        assertEquals(List.of("$"), response.getSpecialCharacters());

        // reversed alphabets = [R, A] -> alternating caps -> "Ra"
        assertEquals("Ra", response.getConcatString());
    }
}
